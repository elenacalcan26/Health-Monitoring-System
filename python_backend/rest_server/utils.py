import requests

AUTH_SERVICE_URL = "http://auth-server:5000"

def validate_req(request):
    print("Validation in process..", flush=True)
    token = request.headers["Authorization"]
    res = requests.post(
        f"{AUTH_SERVICE_URL}/validate",
        headers={"Authorization": token},
    )

    return res
