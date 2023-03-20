#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

#include "MAX30105.h"
#include "pulse_oximeter_sensor.h"

#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64

#define OLED_MOSI   23
#define OLED_CLK    18
#define OLED_DC     16
#define OLED_RESET  17
#define OLED_CS     5 // Nope.

Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT,
                         OLED_MOSI, OLED_CLK, OLED_DC, OLED_RESET, OLED_CS);

MAX30105 particleSensor;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Serial.println(F("Initializing..."));

 if (!display.begin(SSD1306_SWITCHCAPVCC)) {
    Serial.println(F("SSD1306 allocation failed"));
    for (;;);
  }

  // initialize sensor
  if (!particleSensor.begin(Wire, I2C_SPEED_FAST)) {
     Serial.println(F("MAX30102 was not found. Please check wiring/power. "));
  }

  configure_display();
  configure_max3010x_sensor();
  configure_spo2_measuring_variables();
}

void loop() {
  print_on_display();

  long irValue = particleSensor.getIR();

  if (validSPO2 == 0 || heartRate == 0) {
    process_spo2(particleSensor);    
  }

//  if (irValue < 50000)
//    Serial.println(" No finger?");
}

void configure_max3010x_sensor() {
  // Configure sensor with default settings
  particleSensor.setup();
  particleSensor.setPulseAmplitudeRed(0x0A);
  particleSensor.setPulseAmplitudeGreen(0);
}

void configure_display() {
  display.display();
  display.clearDisplay();

  display.setTextSize(1);    
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(20, 30);
  display.println(F("Place your index finger on the sensor with steady pressure."));
  display.display();
  display.setTextSize(2);
  display.clearDisplay();
}

void configure_spo2_measuring_variables() {
  byte ledBrightness = 60; //Options: 0=Off to 255=50mA
  byte sampleAverage = 4; //Options: 1, 2, 4, 8, 16, 32
  byte ledMode = 2; //Options: 1 = Red only, 2 = Red + IR, 3 = Red + IR + Green
  byte sampleRate = 100; //Options: 50, 100, 200, 400, 800, 1000, 1600, 3200
  int pulseWidth = 411; //Options: 69, 118, 215, 411
  int adcRange = 4096; //Options: 2048, 4096, 8192, 16384

  //Configure sensor with these settings
  particleSensor.setup(ledBrightness, sampleAverage, ledMode, sampleRate, pulseWidth, adcRange);
}

void print_spo2() {
  display.setCursor(0, 10);
  display.print(F("%SPO2: "));

  if (validSPO2 == 1) {
    display.print(spo2);
  }
}

void print_bpm() {
  display.setCursor(0, 50);
  display.print(F("BPM: "));

  if (validHeartRate == 1) {
    display.print(heartRate);
  }
  
}

void print_on_display() {
  display.clearDisplay();

  print_spo2();
  print_bpm();

  display.display();
}
