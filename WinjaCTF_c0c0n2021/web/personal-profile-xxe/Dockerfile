FROM openjdk:11

WORKDIR /usr/local/webapp

COPY build/libs/personal-profile-0.0.1-SNAPSHOT.jar app.jar

COPY flag /flag

ENTRYPOINT ["java","-jar","app.jar"]


