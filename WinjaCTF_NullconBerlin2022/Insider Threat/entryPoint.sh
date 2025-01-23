#!/bin/bash

python3 -m http.server  --directory /var/www/ 5000 &

java -jar /usr/local/webapp/app.jar

