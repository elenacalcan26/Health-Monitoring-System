from flask import Flask, request, jsonify
from influxdb_utils import *
import json

influx_client_app = Flask(__name__)

@influx_client_app.route("/influx/measurements/", methods=["GET"])
def get_measurements_by_device():
    # patient_device = get_patient_device(patientId)
    # start_date = get_patient_start_measurement_date(patientId)
    # # TODO check not empty
    # result = get_all_measurements(patient_device, start_date)
    device_id = request.args.get("device")
    start_date = request.args.get("start_date")
    print(f"device = {device_id}", flush=True)

    result = get_all_measurements(device_id, start_date)

    return json.dumps(result), 200

if __name__ == '__main__':
    influx_client_app.run('0.0.0.0', port=7000, debug=True)
