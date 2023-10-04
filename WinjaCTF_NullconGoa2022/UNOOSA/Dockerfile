FROM python:3.8

#RUN apk add --no-cache gcc libc-dev

COPY ./requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip3 install -r requirements.txt

COPY static  /app/static

COPY templates /app/templates

COPY app.py main.py flag.txt /app/

RUN useradd -u 1000 -m pythonServer

USER pythonServer

ENTRYPOINT [ "python3" ]

CMD ["main.py" ]

