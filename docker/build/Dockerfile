FROM openjdk:17
MAINTAINER zeriter zhang <791825185@qq.com>
RUN mkdir -p /data
WORKDIR /data
EXPOSE 8080
RUN cd /data
ADD ../../workstation-admin/target/workstation-admin-0.0.1.jar /data/app.jar
ADD ./application.yml /data/application.yml
CMD ["java","-jar","/data/app.jar","--spring.config.location=/data/application.yml"]