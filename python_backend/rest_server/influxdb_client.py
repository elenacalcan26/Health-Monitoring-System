from influxdb import InfluxDBClient
from datetime import datetime, date

INFLUX_DATABASE = "patients_measurements"

influxClient = InfluxDBClient("influxdb", 8086)

influxClient.switch_database(INFLUX_DATABASE)

patient_measurements = {}

def query_measurement_from_influx(measurement, start_date, device):
    query = f"""SELECT DISTINCT("value") AS {measurement.upper()}
                FROM "patient.{measurement.lower()}"
                WHERE time >= '{start_date}' and time < NOW() and device_id = '{device}'
                GROUP BY time(10m);
            """
    return influxClient.query(query)

def get_all_measurements(device_name, start_measurement_date):
    start_measurement_date = datetime.combine(start_measurement_date, datetime.min.time())
    date_time_iso = start_measurement_date.isoformat() + 'Z'

    result = query_measurement_from_influx("spo2", date_time_iso, device_name)
    parse_result_set(result, "SPO2")

    result = query_measurement_from_influx("bpm", date_time_iso, device_name)
    parse_result_set(result, "BPM")

    print(f"Final -> {patient_measurements}", flush=True)
    return patient_measurements

def parse_result_set(result_set, measurement):
    for row in result_set.get_points():
        measuring_time = row["time"]
        measuring_time = datetime.strptime(measuring_time, "%Y-%m-%dT%H:%M:%SZ")
        measuring_time = measuring_time.strftime("%Y-%m-%d %H:%M:%S")
        measured_value = int(row[measurement])
        print(f"Add measurement = {measurement} with value={measured_value} in dictionary", flush=True)
        if measuring_time not in patient_measurements:
            patient_measurements[measuring_time] = {}
        patient_measurements[measuring_time][measurement] = measured_value
        print(f"Added measurement!", flush=True)
