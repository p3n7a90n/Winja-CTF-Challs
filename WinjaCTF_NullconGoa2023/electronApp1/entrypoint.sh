#!/bin/bash
echo "Starting VNC server"

mkdir $HOME/.vnc/
bash -c "echo $PASSWD | vncpasswd -f > $HOME/.vnc/passwd && chmod 600 $HOME/.vnc/passwd"
echo 'winja-ctf' > "$HOME/.vnc/xstartup" && chmod +x $HOME/.vnc/xstartup

cd /opt/app
node server.js &
vncserver :10
sleep 30m;