### 1. Logback이란	
    - 자바 오픈소스 로깅 프레임워크 SLF4J의 구현체
	-  스프링 부트의 기본으로 설정되어 있어서 사용 시 별도로 라이브러리를 추가하지 않아도 된다.
	- log4j, log4j2 등과 성능을 비교했을 때 logback이 더 좋은 성능을 보여준다.
	- Spring-boot-starter-web 안에 Spring-boot-starter-loggin에 구현체가 있다.
	- Logback을 이용하여 로깅을 수행하기 위해서 필요한 주요 설정요소로는 Logger, Appender, Encoder 3가지가 dlTek.
		
    logback 매뉴얼 : 
        > http://logback.qos.ch/manual/index.html
        
    spring boot log 참고 :  
        > https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-logging


### 2. Spring boot logback 참조 순서
    - Classpath(resources 디렉토리 밑)에 logback-spring.xml 파일이 있으면 설정 파일을 읽어간다.
	- logbakc-spring.xml 파일이 없다면 properties 파일 또는 yml 파일의 설정을 읽어 간다.
	- logbakc-spring.xml 과 properties 파일 또는 yml 이 동시에 있다면, properties 파일 또는 yml 설정 파일의 내용을 먼저 적용 후
	  logbakc-spring.xml 파일이 적용된다

	위에서 이야기한 대로 스프링부트에서는 logback-spring.xml을 사용해서 스프링이 logback을 구동할 수 있도록 지우너해 준다.
	이를 이용하여 profile을 설정하면 배포 경에 따라 설정된 값을 읽어 올 수 있다.
	
	Ex) spring.profiles.active=local
	
	logback-local.properties => console-loggin
	logback-dev.properties => file-loggin
	logback-real.properties => file-loggin, remote-logging
	
### 3. 로그 레벨
    1) ERROR : 요청을 처리하는 중 오류가 발생한 경우 표시한다.
	2) WARN : 처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메시지를 나타낸다.
	3) INFO : 상태변경과 같은 정보성 로그를 표시한다.
	4) DEBUG : 프로그램을 디버깅하기 위한 정보를 표시한다.
	5) TRACE : 추적 레벨은 Debug 보다 휠씬 상세한 정보를 나타낸다.

	- 로그 레벨 순서 : TRACE < DEBUG < INFO < WARN < ERROR
	- 출력 레벨의 설정에 따라 설정 레벨 이상의 로그를 출력한다.
		
### 4. Spring Boot에서의 로그 레벨 설정 
    - properties 파일 또는 yml 파일의 설정파일에서 설정이 가능하다.
	- 루트 로깅 레벨 지정 방법
	    Ø logging.level.root
    - 패키지별 로깅 레벨 지정 방법
	    Ø loggin.level.com.kyu.test=info
		Ø loggin.level.com.kyu.test.controller=debug

### 5. logback-spring.xml 설정
    - 대소문자를 구별하지 않는다.
		Ex) <logger> == <LOGGER> 
	- name attribute를 반드시 지정해야 한다.
	- logback-spring.xml은 크개 appender와 logger 2개로 구분된다.
	- Dynamic Reloading 기능을 제공한다.
		Ex) 60초 주기마다 로그 파일을(logback-spring.xml)이 변경되었는지 확인하여 프로그램을 갱신한다.
			
### 6. logback-spring.xml 의  appender
    - Log의 형태를 설정, 로그 메시지가 출력될 대상을 결정하는 요소
	    (콘솔에 출력할지, 파일로 출력할지 등에 대한 설정)
    - appender의 class의 종류
		Ø ch.qos.logback.core.ConsoleAppender : 콘솔에 로그를 찍는다. 로그를 OutputStream에 작성하여 콘솔에 출력되도록 한다.
		Ø ch.qos.logback.core.FileAppender : 파일에 로그를 찍는다. 최대 보관일 수 파일 크기 등을 지정할 수 있다.
		Ø ch.qos.logback.core.rolling.RoollingFileAppender : 여러개의 파일을 롤링. 순회하면서 로그를 찍는다.
	              										     FileAppender를 상속받기 때문에 지정용량 설정이 가능하다.
        Ø ch.qos.logback.classic.net.SMTPAppender : 로그를 메일에 찍어 보낸다.
		Ø ch.qos.logback.classic.db.DBAppender : 데이터베이스에 로그를 찍는다.
	
	    자세한 내용은 http://logback.qos.ch/manual/appenders.html 참조
		
### 7. Root, logger 
	- 설정한 appender를 참조하여 package와 level을 설정한다.
	- Root 
		Ø 전역 설정
		Ø 지역적으로 선언된 logger 설정이 있다면 해당 logger 설정이 default로 적용된다.
			
	- Logger
		Ø 지역 설정이라고 볼 수 있다.
		Ø Addititvity 값은 root 설정 상속 유무 설정.

### 8. Property
	- 설정 파일에서 사용될 변수 값 선언

### 9. Layout, encoder
	- Layout : 로그의 출력 포맷을 지정한다. Log4j에서 설정할때 많이 사용한다.
	     		(http://logback.qos.ch/manual/layouts.html)
    - Encoder : Appender에 포함되어 사용자가 지정한 형식으로 표현 될 로그메시지를 변환하는 역활을 담당하는 요소
	
    Ø Encoder는 바이트를 소유하고 있는 appender가 관리하는 OutputStream에 쓸 시간과 내용을 제어할 수 있다.
	  FileAppender와 하위 클래스는 endcoder를 필요로 하고 더 이상 layout은 사용하지 않는다.
	  즉 이제 layout 보다는 encoder를 사용하면된다.
		
### 10. Patter에 사용되는 요소
|구분|설명|
|--------------|-------------------------------------------------------------------|
|%Logger{length}|	Logger name을 축약할 수 있다. {length}는 최대 자리 수, ex)logger{35}|
|%-5level|	로그 레벨, -5는 출력의 고정폭 값(5글자)|
|%msg|	- 로그 메시지 (=%message)|
|${PID:-}|	프로세스 아이디|
|%d|	로그 기록시간|
|%p|	로깅 레벨|
|%F|	로깅이 발생한 프로그램 파일명|
|%M|	로깅일 발생한 메소드의 명|
|%l|	로깅이 발생한 호출지의 정보|
|%L|	로깅이 발생한 호출지의 라인 수|
|%thread|	현재 Thread 명|
|%t|	로깅이 발생한 Thread 명|
|%c|	로깅이 발생한 카테고리|
|%C|	로깅이 발생한 클래스 명|
|%m|	로그 메시지|
|%n|	줄바꿈(new line)|
|%%|	%를 출력|
|%r|	애플리케이션 시작 이후부터 로깅이 발생한 시점까지의 시간(ms)|
	
	
### 11. 기타
|구분|설명|
|--------------|-------------------------------------------------------------------|
|\<file>|	기록할 파일명과 경로를 설정한다.
|\<rollingPolicy class>|	ch.qos.logback.core.rolling.TimeBasedRollingPolicy => 일자별 적용|
|	              |  ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP => 일자별 + 크기별 적용|
|\<fileNamePattern>|	파일 쓰기가 종료된 log 파일명의 패턴을 지정한다.|
|	              | .gz나 .zip으로 자동으로 압축할 수도 있다.|
|\<maxFileSize>|	한 파일당 최대 파일 용량을 지정한다.|
|             | log 내용의 크기도 IO성능에 영향을 미치기 때문에 되도록이면 너무 크지 않은 사이즈로 지정하는 것이 좋다.|
|		      | (최대 10MB 내외 권장)|
|		      | 용량의 단위는 KB, MB, GB 3가지를 지정할 수 있다.|
|		      | RollingFile 이름 패턴에 .gz 이나 .zip을 입력할 경우 로그파일을 자동으로 압축해주는 기능도 있다.|
|\<maxHistory> |	최대 파일 생성 갯수|
|		      | 예를들어 maxHistory가 30이고 Rolling정책을 일 단위로 하면 30일동안만 저장되고, 월 단위로 하면 30개월간 저장된다.|
|\<Filter>| 해당 패키지에 무조건 로그를 찍는 것말고도 필터링이 필요한 경우에 사용하는 기능이다.|
|		 | 하단의 예시 설정 파일에서 사용방법을 확인 하도록 하자. (레벨 필터를 추가해서 level이 error인 것만 찍게 만들었다.)|
				
				 
