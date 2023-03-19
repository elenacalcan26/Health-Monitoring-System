#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

#include "MAX30105.h"
#include "heartRate.h"
#include "bpm_sensor.h"

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

  display.display();
  display.clearDisplay();

  display.setTextSize(1);    
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(20, 30);
  display.println(F("Place your index finger on the sensor with steady pressure."));
  display.display();
  display.setTextSize(2);
  display.clearDisplay();

  // Configure sensor with default settings
  particleSensor.setup();
  particleSensor.setPulseAmplitudeRed(0x0A);
  particleSensor.setPulseAmplitudeGreen(0);
  
}

void print_bpm() {
  display.clearDisplay();
  display.setCursor(0, 50);
  display.print(F("BPM: "));

  if (beatAvg != 0) {
    display.print(beatAvg);
  }
  
  display.display();
}

void loop() {
  // put your main code here, to run repeatedly

  print_bpm();

  long irValue = particleSensor.getIR();

  process_bpm(irValue);

//  if (irValue < 50000)
//    Serial.println(" No finger?");
}
