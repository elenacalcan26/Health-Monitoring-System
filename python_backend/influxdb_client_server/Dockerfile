FROM python:3.8

COPY requirements.txt /tmp
RUN pip3 install --upgrade pip
RUN pip3 install -r /tmp/requirements.txt

COPY *.py /src/

EXPOSE 7000

CMD ["python3", "-u", "/src/influxdb_client_server.py"]