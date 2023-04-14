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

