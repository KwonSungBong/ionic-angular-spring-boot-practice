server {
  listen 80 default;
  server_name localhost;

  root   /var/www/web;
  index  index.html index.htm;

  location /api/ {
    proxy_pass http://spring-boot/;
  }
}

upstream spring-boot {
  server spring-boot-1:8888;
  server spring-boot-2:8888;
}
