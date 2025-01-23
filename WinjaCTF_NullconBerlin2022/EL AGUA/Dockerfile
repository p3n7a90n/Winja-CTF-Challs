FROM openjdk:11

COPY ./flag /flag

WORKDIR /usr/local/webapp

RUN mkdir -p /var/www/

COPY ./pythonHTTPServer.py pythonHTTPServer.py

COPY ./DeleteFilesEvery30Seconds.py DeleteFilesEvery30Seconds.py

COPY ./entryPoint.sh entryPoint.sh

RUN chmod +x ./entryPoint.sh

RUN useradd -u 1000 -m RunJar

RUn chown -R RunJar /var/www/

USER RunJar

COPY build/libs/winja-0.0.1-SNAPSHOT.jar /home/RunJar/app.jar


ENTRYPOINT ["./entryPoint.sh" ]


