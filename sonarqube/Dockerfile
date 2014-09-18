FROM debian:7.6
MAINTAINER linda.martin@sonarsource.com

ENV DEBIAN_FRONTEND noninteractive
ENV SQ_VERSION 4.5-RC2

RUN apt-get update && apt-get install -y procps curl unzip openjdk-7-jre-headless 

# Install SonarQube Server
RUN curl -sLo sonarqube-${SQ_VERSION}.zip http://dist.sonar.codehaus.org/sonarqube-${SQ_VERSION}.zip && \
    unzip sonarqube-${SQ_VERSION}.zip -d /tmp && \
    mv /tmp/sonarqube-${SQ_VERSION} /opt/sonarqube

EXPOSE 9000

ADD start /start
RUN chmod +x /start
CMD ["/start"]
