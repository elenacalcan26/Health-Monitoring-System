import paho.mqtt.client as mqtt
import time
import logging
import json
from influxdb import InfluxDBClient

MQTT_BROKER = "broker.hivemq.com"
TOPIC = "monitoring/devices/#"
INFLUX_DATABASE = "patients_measurements"

influxClient = InfluxDBClient("influxdb", 8086)

# The callback for when the client receives a CONNACK response from the server.
def on_connect(client, userdata, flags, rc):
    logging.info(f"Connected with result code {rc}")

    # Subscribing in on_connect() means that if we lose the connection and
    # reconnect then subscriptions will be renewed.
    client.subscribe(TOPIC)

# The callback for when a PUBLISH message is received from the server.
def on_message(client, userdata, msg):

    json_measurements = json.loads(msg.payload)
    device = msg.topic.rsplit("/", 1)[-1]

    logging.info(
        f'Received measurements from {device} : SPO2 = {json_measurements["spo2"]} and BPM = {json_measurements["bpm"]}')

    # insert received data in db

    for key in json_measurements:
        data = [
            {
                "measurement": f"patient.{key}",
                "tags": {
                    "device_id": device
                },
                "fields": {
                    "value": int(json_measurements[key])
                }
            }
        ]

        influxClient.write_points(data)
        logging.info("Inserted into db : \n" + json.dumps(data, indent=2))


def main():
    global client
    logging.basicConfig(level=logging.INFO)

    time.sleep(5)

    influxClient.switch_database(INFLUX_DATABASE)
    logging.info("Connected to InfluxDB")

    client = mqtt.Client()
    client.on_connect = on_connect
    client.on_message = on_message

    client.connect(MQTT_BROKER, 1883)
    client.loop_forever()

if __name__ == '__main__':
    main()
