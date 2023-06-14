import requests

AUTH_SERVICE_URL = "http://auth-server:5000"
INFLUX_CLIENT_SERVICE_URL = "http://influx-client-server:7000"

def validate_req(request):
    print("Validation in process..", flush=True)
    token = request.headers["Authorization"]
    res = requests.post(
        f"{AUTH_SERVICE_URL}/validate",
        headers={"Authorization": token},
    )

    return res


def get_patient_measurements(device_id, start_monitoring_date):
    params = {
        "device": str(device_id),
        "start_date": start_monitoring_date
    }
    response = requests.get(
            f"{INFLUX_CLIENT_SERVICE_URL}/influx/measurements",
            params=params
        )
    print(f" -> {response.content} <- ", flush=True)
    return response.content.decode("utf-8")
