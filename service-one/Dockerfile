FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="Siva Nagi Reddy Alla <siva.alla@outlook.com>"

# service-one

WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/service-one-1.0-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar