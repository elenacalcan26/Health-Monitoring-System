import mysql.connector as mysql
from mysql.connector import Error
import os

mysql_client = mysql.connect(
        host="mysql",
        port=3306,
        database="HealthSystemDB",
        user="admin",
        password="admin"
    )

if mysql_client.is_connected():
    print("Connected to MySQL", flush=True)
else:
    print("Connection to MySQL failed!", flush=True)

mysql_db_cursor = mysql_client.cursor(dictionary=True)

def get_doc_info(username):
    query = f"""SELECT
                CONCAT(d.first_name, ' ', d.last_name) AS full_name,
                d.work_mail,
                d.phone,
                hd.name,
                d.work_mail,
                hd.name AS department_name,
                h.name AS hospital_name
            FROM Doctors d
            JOIN HospitalDepartments hd ON hd.id = d.department_id
            JOIN Hospitals h on h.id = hd.hospital_id
            WHERE username = '{username}';"""

    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    print(f"Selected doc is: {result}", flush=True)
    return result

def get_doc_patients(username):
    query = f"""SELECT
                    p.id AS patient_id,
                    CONCAT(person.first_name, ' ', person.last_name) AS full_name
                FROM Patients p
                JOIN People person on person.id = p.person_id
                WHERE p.doctor_id = '{username}';"""

    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchall()
    print(f"Selected patients: {result}", flush=True)
    return result

def get_patient_general_info(patient_id):
    query = f"""SELECT
                    CONCAT(person.first_name, ' ', person.last_name) AS full_name,
                    person.birth_date,
                    person.gender,
                    person.age,
                    patient.monitoring_start_date,
                    patient.device_id
                FROM People person
                JOIN Patients patient on patient.person_id = person.id AND patient.id = {patient_id};"""

    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    print(f"Patient profile = {result}", flush=True)
    return result

def get_patient_start_measurement_date(patient_id):
    query = f"""SELECT monitoring_start_date FROM Patients WHERE id = {patient_id}"""
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    start_date = result["monitoring_start_date"]
    print(f"Patient with ID = {patient_id} has started: {start_date}", flush=True)
    return start_date

def get_patient_device(patient_id):
    query = f"""SELECT device_id FROM Patients WHERE id =  {patient_id};"""
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    device = result["device_id"]
    print(f"Patient with ID = {patient_id} has device = {device}", flush=True)
    return str(device)

def get_patient_diagnose(patient_id):
    query = f"""SELECT
                    d.diagnosis,
                    d.diagnosis_date
                FROM Diagnoses d
                JOIN Patients p on p.diagnosis_id = d.id
                WHERE p.id = {patient_id};"""
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    print(f"Diagnose: {result}", flush=True)
    return result


def get_patient_treatment(patient_id):
    query = f"""SELECT
                    t.treatment AS treatment_name,
                    t.start_date,
                    m.name AS medication_name,
                    m.dosage,
                    m.frequency
                FROM Treatments t
                JOIN Patients p on p.treatment_id = t.id
                JOIN Medications m on m.treatment_id = t.id
                WHERE p.id = {patient_id};"""
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    print(f"Treatments: {result}", flush=True)
    return result


def get_patient_allergies(patient_id):
    query = f"""SELECT allergen from PatientsAllergies WHERE patient_id = {patient_id};"""
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchall()
    print(f"Allergies: {result}", flush=True)
    return result
