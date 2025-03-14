FROM alpine

RUN apk add --no-cache nodejs npm

RUN addgroup -g 1000 app \
&& adduser -G app -u 1000 -D app

WORKDIR /home/app

USER app