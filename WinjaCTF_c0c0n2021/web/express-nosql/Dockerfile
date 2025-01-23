FROM node:14

WORKDIR /usr/src/app

COPY package.json ./

RUN npm install

COPY public ./public
COPY static ./static
COPY dbconfig.js schema.js server.js ./

EXPOSE 8080

CMD [ "node", "server.js" ]
