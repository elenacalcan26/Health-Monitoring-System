#include <PubSubClient.h>
#include <ArduinoJson.h>

const char* mqtt_broker = "broker.hivemq.com";
const int mqtt_port = 1883;

#define TOPIC "monitoring/devices/device-01"

WiFiClient espClient;
PubSubClient client(espClient);

void reconnect() {
  while(!client.connected()) {
    if (client.connect("ESP32Client")) {
      Serial.println("Reconnected");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void configure_mqtt_connection() {
  Serial.println(F("Connect to MQTT broker"));

  client.setServer(mqtt_broker, mqtt_port);
  while (!client.connected()) {
    Serial.print("Connecting to MQTT...");
    
    String clientName = "ESP8266Client-";
    clientName += String(random(0xffff), HEX);

    WiFi.mode(WIFI_STA);

    delay(5000);
    
    if (client.connect(clientName.c_str())) {
      Serial.println("connected");      
    } else {
      Serial.print("failed with state ");
      Serial.println(client.state());
      delay(2000);
    }
  }

    client.loop();

  Serial.print(client.state());
  Serial.println(F(" - Connected"));
}

/*
 * https://techtutorialsx.com/2017/04/29/esp32-sending-json-messages-over-mqtt/
 * https://arduinojson.org/v6/api/jsonobject/
 */
void publish_values(int32_t spo2, int32_t heartRate) {
    DynamicJsonDocument doc(300);
    doc["spo2"] = spo2;
    doc["bpm"] = heartRate;

    String json;
    serializeJson(doc, json);

    Serial.println("Sending message to MQTT topic...");
    Serial.println(json);

    if (!client.connected()) {
      Serial.print("Client is not connected: state = ");
      Serial.println(client.state());
      reconnect();
    }

    client.loop();

    if (client.publish(TOPIC, json.c_str()) == true) {
        Serial.println("Success sending message!");
    } else {
        Serial.println("Error sending message");
    }
}
