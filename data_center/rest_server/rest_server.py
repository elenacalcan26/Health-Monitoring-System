from flask import Flask, request, jsonify
from db_client import *
from utils import *

app = Flask(__name__)

@app.route("/patients/<int:patientId>", methods=["GET"])
def get_patient_data(patientId):
    pass

@app.route("/patients", methods = ["GET"])
def get_all_doctor_patients():
    pass

@app.route("/profile", methods=["GET"])
def get_doctor_profile():
    validated_response = validate_req(request=request)

    current_user = validated_response["username"]
    print(f"User = {current_user}", flush=True)

    return "Authorized !"

@app.route('/')
def test():
    return 'Start'

if __name__ == '__main__':
    app.run('0.0.0.0', port=6000, debug=True)
