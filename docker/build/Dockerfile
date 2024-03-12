FROM openjdk:17
MAINTAINER zeriter zhang <791825185@qq.com>
RUN mkdir -p /data
WORKDIR /data
EXPOSE 8080
RUN cd /data
ADD application.yml /data/application.yml
ADD workstation.jar /data/app.jar
CMD ["java","-jar","/data/app.jar","--spring.config.location=/data/application.yml"]