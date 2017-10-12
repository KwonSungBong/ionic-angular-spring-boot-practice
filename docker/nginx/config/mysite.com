server {
  listen 80 default;
  server_name localhost;

  location / {
    proxy_pass http://spring-boot;
  }
}

upstream spring-boot {
  server spring-boot-1;
  server spring-boot-2;
}
