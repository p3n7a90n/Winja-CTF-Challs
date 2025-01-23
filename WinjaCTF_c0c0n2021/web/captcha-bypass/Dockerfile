FROM openjdk:11

WORKDIR /usr/local/webapp

COPY build/libs/Captcha-0.0.1-SNAPSHOT.jar app.jar

COPY ./top100usernames.txt .

ENTRYPOINT ["java","-jar","app.jar"]


