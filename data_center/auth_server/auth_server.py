from flask import Flask, request, Response, jsonify, make_response
from auth_db_client import *
import jwt

auth_app = Flask(__name__)
auth_app.config["SECRET_KEY"] = "super secret key"

@auth_app.route("/login", methods=["POST"])
def login():
    data = request.form
    username, password = data.get("username"), data.get("password")
    user_result = check_user(username)
    psswd_result = check_user_passwd(username, password)

    if user_result is not None and not psswd_result:
        return jsonify(
            {"message": "Invalid username or password!"}
        ), 401

    jwt_token = jwt.encode(data, auth_app.config["SECRET_KEY"], algorithm='HS256')

    return make_response(jsonify({"token": jwt_token}), 201)

if __name__ == '__main__':
    auth_app.run('0.0.0.0', port=5000, debug=True)