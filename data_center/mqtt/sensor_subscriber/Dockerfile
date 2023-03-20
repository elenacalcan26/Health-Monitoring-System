FROM python:3.9

COPY requirements.txt /tmp
RUN pip3 install --upgrade pip
RUN pip3 install -r /tmp/requirements.txt

COPY src/*.py /src/

EXPOSE 5000

CMD ["python3", "/src/subscriber.py"]