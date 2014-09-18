FROM debian:7.6
MAINTAINER linda.martin@sonarsource.com

ENV DEBIAN_FRONTEND noninteractive
ENV SQ_VERSION 5.0-SNAPSHOT

RUN apt-get update && apt-get install -y procps curl unzip openjdk-7-jre-headless 

# Install SonarQube Server
RUN curl -sLo sonarqube-${SQ_VERSION}.zip http://192.168.1.40:8080/sonarqube-${SQ_VERSION}.zip && \
    unzip sonarqube-${SQ_VERSION}.zip -d /tmp && \
    mv /tmp/sonarqube-${SQ_VERSION} /opt/sonarqube

#RUN curl -sLo sonarqube-${SQ_VERSION}.zip http://dist.sonar.codehaus.org/sonarqube-${SQ_VERSION}.zip && \
#   unzip sonarqube-${SQ_VERSION}.zip -d /tmp && \
#   mv /tmp/sonarqube-${SQ_VERSION} /opt/sonarqube

RUN sed -i "s@#sonar\.jdbc\.url=jdbc:postgresql://localhost/sonar@sonar\.jdbc\.url=jdbc:postgresql://\${env:POSTGRES_1_PORT_5432_TCP_ADDR}/sonar@" /opt/sonarqube/conf/sonar.properties
EXPOSE 9000
EXPOSE 9001

ADD start /start
RUN chmod +x /start
CMD ["/start"]
