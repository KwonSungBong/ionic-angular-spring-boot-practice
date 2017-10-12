FROM ubuntu
RUN apt-get update && apt-get install -y nginx
RUN rm -rf /etc/nginx/sites-enabled/default
COPY config/mysite.com /etc/nginx/sites-enabled/mysite.com
EXPOSE 80 443
CMD ["nginx", "-g", "daemon off;"]