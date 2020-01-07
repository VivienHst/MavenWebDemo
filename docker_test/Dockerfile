FROM centos:7
MAINTAINER vivien

RUN yum install -y wget

RUN cd /

ADD jdk-8u202-linux-x64.tar.gz /
ADD apache-tomcat-8.5.50.tar.gz /

COPY mavenwebdemo.war /apache-tomcat-8.5.50/webapps/

ENV JAVA_HOME=/jdk1.8.0_202
ENV PATH=$PATH:/jdk1.8.0_202/bin

CMD ["/apache-tomcat-8.5.50/bin/catalina.sh", "run"]

