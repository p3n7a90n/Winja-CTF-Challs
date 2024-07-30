#COPY ./out/make/deb/x64/winja-ctf_1.0.0_amd64.deb /tmp/electron2.deb

apt update && DEBIAN_FRONTEND=noninteractive apt install -y curl tigervnc-standalone-server tigervnc-xorg-extension icewm
apt --fix-broken install
#RUN DEBIAN_FRONTEND=noninteractive apt install -y /tmp/electron2.deb

COPY ./flag.txt /flag.txt
RUN chmod 400 /flag.txt



RUN useradd -u 1000 -m ctf

USER ctf

ENV USER=ctf
ENV HOME=/home/ctf

RUN mkdir $HOME/.vnc/

RUN /bin/bash -c 'vncpasswd -f <<<"testing" >"$HOME/.vnc/passwd" && chmod 600 $HOME/.vnc/passwd'
RUN echo 'icewm&' > "$HOME/.vnc/xstartup" && chmod +x $HOME/.vnc/xstartup

RUN vncserver -kill :10

CMD /bin/sh -c 'vncserver -verbose -cleanstale -localhost no -passwd $HOME/.vnc/passwd -rfbport 5910 -name winja-ctf && DISPLAY=:10  winja-ctf'
