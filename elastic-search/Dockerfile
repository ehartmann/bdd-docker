FROM bdddocker_sonarqube
MAINTAINER linda.martin@sonarsource.com

RUN sed -i "s@#sonar\.jdbc\.url=jdbc:postgresql://localhost/sonar@sonar\.jdbc\.url=jdbc:postgresql://\${env:POSTGRES_1_PORT_5432_TCP_ADDR}/sonar@" /opt/sonarqube/conf/sonar.properties

ADD start /start
RUN chmod +x /start
CMD ["/start"]
