from flask import Flask, request, Response, jsonify
from auth_db_client import *
import jwt

auth_app = Flask(__name__)

@auth_app.route("/login", methods=["POST"])
def login():
    data = request.form
    username, password = data.get("username"), data.get("password")
    print(f"user = {username}", flush=True)
    user_result = check_user(username)

    return f"E ok! {user_result}"

if __name__ == '__main__':
    auth_app.run('0.0.0.0', port=5000, debug=True)