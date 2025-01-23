FROM openjdk:11

WORKDIR /usr/local/webapp

COPY build/libs/winja-0.0.1-SNAPSHOT.jar app.jar

RUN rm /usr/share/xml/fontconfig/fonts.dtd

COPY ./svg10.dtd /usr/share/xml/svg/svg10.dtd

COPY ./flag /flag

ENTRYPOINT ["java","-jar","app.jar" ]

