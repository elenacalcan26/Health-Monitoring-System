import mysql.connector as mysql
from mysql.connector import Error
import os

mysql_auth_client = mysql.connect(
        host="mysql-auth",
        port=3307,
        database="AuthDB",
        user="admin",
        password="admin"
    )

auth_cursor = mysql_auth_client.cursor()

def check_user(username):
    query = f"SELECT * FROM Users WHERE username = '{username}';"
    auth_cursor.execute(query)
    result = auth_cursor.fetchone()
    return result

def check_user_passwd(username, password):
    query = f"SELECT password FROM Users WHERE username = '{username}';"
    auth_cursor.execute(query)
    result = auth_cursor.fetchone()[0]
    print(result, flush=True)
    return password == result
