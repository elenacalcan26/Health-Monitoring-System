from flask import Flask, request, Response
from db_client import *
import time

app = Flask(__name__)

@app.route("/patients/<int:patientId>", methods=["GET"])
def get_patient_data(username, patientId):
    pass

@app.route("/patients", methods = ["GET"])
def get_all_doctor_patients():
    pass

@app.route("/profile/<string:username>", methods=["GET"])
def get_doctor_profile(username):
    result = get_doc_info(username)
    return f"doc = {result}"

@app.route('/')
def test():
    return 'Start'

if __name__ == '__main__':
    app.run('0.0.0.0', port=6000, debug=True)
