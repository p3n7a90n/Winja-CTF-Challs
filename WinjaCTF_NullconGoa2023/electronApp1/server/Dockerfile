FROM node:14

WORKDIR /usr/src/app

COPY package.json ./

RUN npm install

COPY static ./static
COPY views ./views
COPY  server.js ./

EXPOSE 8080

CMD [ "node", "server.js" ]