FROM ubuntu:latest

RUN apt update && DEBIAN_FRONTEND=noninteractive apt install -y tightvncserver nodejs npm
COPY ./out/make/deb/x64/winja-ctf_1.0.0_amd64.deb /tmp/electron1.deb
RUN DEBIAN_FRONTEND=noninteractive apt install -y /tmp/electron1.deb

WORKDIR /opt/app

COPY ./server/package.json ./
RUN npm install
COPY ./server/static ./static
COPY ./server/views ./views
COPY  ./server/server.js ./

RUN useradd -s /bin/bash -m ctf
COPY ./flag.txt /home/ctf/flag.txt
RUN chown root:ctf /home/ctf/flag.txt
RUN chmod 640 /home/ctf/flag.txt

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

USER ctf
ENV USER=ctf
ENV HOME=/home/ctf

CMD /entrypoint.sh