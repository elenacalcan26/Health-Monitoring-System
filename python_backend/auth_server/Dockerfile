FROM python:3.8

COPY requirements.txt /tmp
RUN pip3 install --upgrade pip
RUN pip3 install -r /tmp/requirements.txt

COPY *.py /src/

EXPOSE 5000

CMD [ "python3", "-u", "/src/auth_server.py" ]