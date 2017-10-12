# ionic-angular-spring-boot-practice

####################################################################################################

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

####################################################################################################

docker image 생성 : ./gradlew build buildDocker

docker 실행 : docker run -p 8888:8888 -t spring-boot:0.0.1-SNAPSHOT

docker 실행 시 프로파일 설정 : docker run -e "SPRING_PROFILES_ACTIVE=local" -p 8888:8888 -t spring-boot:0.0.1-SNAPSHOT

spring boot docker 참조 : https://github.com/spring-guides/gs-spring-boot-docker

####################################################################################################

ionic start ionic-angular blank

proxy : https://ionicframework.com/docs/cli/configuring.html
