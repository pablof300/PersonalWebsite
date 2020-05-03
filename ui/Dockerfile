FROM nginx:stable-alpine
RUN mkdir -p /etc/nginx/

COPY /ssl /etc/nginx/ssl
COPY ./build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
EXPOSE 443
CMD ["nginx", "-g", "daemon off;"]
