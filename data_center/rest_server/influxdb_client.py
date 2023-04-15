from influxdb import InfluxDBClient
from datetime import datetime, date

INFLUX_DATABASE = "patients_measurements"

influxClient = InfluxDBClient("influxdb", 8086)

influxClient.switch_database(INFLUX_DATABASE)

def get_all_measurements(device_name, start_measurement_date):
    start_measurement_date = datetime.combine(start_measurement_date, datetime.min.time())
    date_time_iso = start_measurement_date.isoformat() + 'Z'
    query = f"""SELECT DISTINCT("value") AS SPO2
                FROM "patient.spo2"
                WHERE time >= '{date_time_iso}' and time < NOW() and device_id = '{device_name}'
                GROUP BY time(1h);"""
    result = influxClient.query(query)
    print(f"Measurements: \n{result}", flush=True)
    print(type(result), flush=True)
    return convert_result_set(result)

def convert_result_set(result_set):
    values = []
    for row in result_set.get_points():
        measuring_time = row["time"]
        time_type = type(measuring_time)
        print(f"time type = {time_type}", flush=True)
        measuring_time = datetime.strptime(measuring_time, "%Y-%m-%dT%H:%M:%SZ")
        measuring_time = measuring_time.strftime("%Y-%m-%d %H:%M:%S")
        value = row["SPO2"]
        values.append({
            "time": measuring_time,
            "value": value
        })
    return {
        "SPO2": values
    }