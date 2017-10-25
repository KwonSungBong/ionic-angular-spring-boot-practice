# ionic-angular-spring-boot-practice

#####################################################################################################

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

#####################################################################################################

docker image 생성 : ./gradlew build buildDocker

docker 실행 : docker run -p 8888:8888 -t spring-boot:0.0.1-SNAPSHOT

docker 실행 시 프로파일 설정 : docker run -e "SPRING_PROFILES_ACTIVE=local" -p 8888:8888 -t spring-boot:0.0.1-SNAPSHOT

spring boot docker 참조 : https://github.com/spring-guides/gs-spring-boot-docker

#####################################################################################################

ionic start ionic-angular blank

proxy : https://ionicframework.com/docs/cli/configuring.html

#####################################################################################################

docker rmi custom/nginx

#####################################################################################################

1. 실행

docker-compose up -d

docker-compose -f docker-compose-blue.yml -p blue up -d

2. 무중단배포
nginx/config/mysite.com에서 upstream spring-boot 한개 주석하고 저장
docker-compose kill -s HUP nginx 하면 엔진엑스 설정 갱신됨
그렇게 docker image를 교체해서 다시 올린다.

docker-compose up -d spring-boot-1

docker-compose logs api-tomcat

docker-compose -f docker-compose-blue.yml -p blue up -d spring-boot-2

docker-compose -f docker-compose-blue.yml -p blue logs spring-boot-2

######################################################################################################

ionic cordova platform add browser
ionic build browser --prod

ionic cordova build browser --prod --release

######################################################################################################

https://developers.facebook.com/apps/285994385247600/settings/basic/

######################################################################################################

https://github.com/stomp-js/ng2-stompjs

"sockjs-client": "^1.1.4",
"stompjs": "^2.3.3",

######################################################################################################

아이디는 user, admin 입력.
비밀번호는 빈칸이거나 아무거나 입력.

######################################################################################################

https://angular.io/tutorial/toh-pt6

https://awaters1.github.io/Angular2-Stomp-WebSockets/

http://codegists.com/snippet/typescript/stomp-clientts_ccarrasc_typescript

http://codegists.com/snippet/typescript/stompclientts_martypitt_typescript