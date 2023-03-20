// https://www.upesy.com/blogs/tutorials/how-to-connect-wifi-acces-point-with-esp32
#include <WiFi.h>

// replace with your network & its credentials
const char* ssid = "";
const char* password = "";

void configure_network() {
    WiFi.begin(ssid, password);
    Serial.println(F("Connecting..."));

    while(WiFi.status() != WL_CONNECTED){
        Serial.print(".");
        delay(100);
    }
    Serial.println("\nConnected to the WiFi network");
    Serial.print("Local ESP32 IP: ");
    Serial.println(WiFi.localIP());
}
