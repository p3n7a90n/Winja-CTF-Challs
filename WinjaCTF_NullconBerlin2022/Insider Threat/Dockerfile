FROM openjdk:11

WORKDIR /usr/local/webapp

COPY build/libs/winja-0.0.1-SNAPSHOT.jar app.jar

COPY ./entryPoint.sh entryPoint.sh

RUN chmod +x ./entryPoint.sh

RUN mkdir -p /var/www/

COPY ./flag.jpg /var/www/flag.jpg

#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

ENTRYPOINT ["./entryPoint.sh" ]