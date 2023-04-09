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

mysql_db_cursor = mysql_client.cursor()

def get_doc_info(username):
    query = f"SELECT * FROM Doctors WHERE username = '{username}';"
    print(f"Execute query = {query}", flush=True)
    mysql_db_cursor.execute(query)
    result = mysql_db_cursor.fetchone()
    print(f"Selected doc is: {result}", flush=True)
    return result

