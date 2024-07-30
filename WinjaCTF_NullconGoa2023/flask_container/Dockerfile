FROM python:alpine

WORKDIR /opt/flask_provision
RUN pip install -U Flask docker gunicorn
COPY . .

CMD gunicorn -w 4 -b 0.0.0.0:8000 'app:app'