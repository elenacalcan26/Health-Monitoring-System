import requests
import json

AUTH_SERVICE_URL = "http://auth-server:5000"

def validate_req(request):
    print("Validation in process..", flush=True)
    token = request.headers["Authorization"]
    res = requests.post(
        f"{AUTH_SERVICE_URL}/validate",
        headers={"Authorization": token},
    )

    if res.status_code != 200:
        return None

    response_content = json.loads(res.content)

    return response_content
