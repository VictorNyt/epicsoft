FROM openjdk:21

RUN groupadd -g 1000 app \
&& useradd -g 1000 -u 1000 -d /home/app app

WORKDIR /home/app

USER app