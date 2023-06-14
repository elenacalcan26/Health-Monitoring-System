from flask import Flask, request, jsonify
from db_client import *
from utils import *
import json

app = Flask(__name__)

@app.route("/patients/<int:patientId>/status", methods=["GET"])
def get_patient_medical_status(patientId):
    validated_response = validate_req(request=request)

    if validated_response.status_code != 200:
        return validated_response.content, validated_response.status_code

    patient_status = {}
    patient_status["diagnose"] = get_patient_diagnose(patientId)
    patient_status["treatment"] = get_patient_treatment(patientId)
    patient_status["allergies"] =  get_patient_allergies(patientId)
    return {"status": patient_status}, 200

@app.route("/patients/<int:patientId>/measurements", methods=["GET"])
def get_patient_all_measurements(patientId):
    validated_response = validate_req(request=request)

    if validated_response.status_code != 200:
        return validated_response.content, validated_response.status_code

    patient_device = get_patient_device(patientId)
    start_date = get_patient_start_measurement_date(patientId)

    measurements = get_patient_measurements(patient_device, start_date)
    print(f"in measurements url -> {measurements}", flush=True)

    return measurements, 200, {"Content-Type": "application/json"}

@app.route("/patients/<int:patientId>", methods=["GET"])
def get_patient_profile(patientId):
    # TODO: plz check the response
    validated_response = validate_req(request=request)

    if validated_response.status_code != 200:
        return validated_response.content, validated_response.status_code

    patient_profile = get_patient_general_info(patient_id=patientId)
    return (patient_profile, 200)

@app.route("/patients", methods = ["GET"])
def get_all_doctor_patients():
    validated_response = validate_req(request=request)

    if validated_response.status_code != 200:
        return validated_response.content, validated_response.status_code

    response_content = json.loads(validated_response.content)
    current_user = response_content["username"]
    patients_list = get_doc_patients(current_user)

    return (patients_list, 200)

@app.route("/profile", methods=["GET"])
def get_doctor_profile():
    validated_response = validate_req(request=request)

    if validated_response.status_code != 200:
        return validated_response.content, validated_response.status_code

    print(validated_response.content, flush=True)
    response_content = json.loads(validated_response.content)
    current_user = response_content["username"]
    doc_info = get_doc_info(current_user)

    return doc_info, 200

@app.route('/')
def test():
    return 'Start'

if __name__ == '__main__':
    app.run('0.0.0.0', port=6000, debug=True)
