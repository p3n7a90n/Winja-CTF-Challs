#!/bin/bash
echo "Starting VNC server"

mkdir $HOME/.vnc/
bash -c "echo $PASSWD | vncpasswd -f > $HOME/.vnc/passwd && chmod 600 $HOME/.vnc/passwd"
echo 'winja-ctf' > "$HOME/.vnc/xstartup" && chmod +x $HOME/.vnc/xstartup

vncserver :10
# while ! pgrep -x "winja-ctf" >/dev/null; do sleep 5; done
sleep 30m;