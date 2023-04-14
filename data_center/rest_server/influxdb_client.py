from influxdb import InfluxDBClient

INFLUX_DATABASE = "patients_measurements"

influxClient = InfluxDBClient("influxdb", 8086)

influxClient.switch_database(INFLUX_DATABASE)

def get_all_measurements(device_name, start_measurement_date):
    pass
