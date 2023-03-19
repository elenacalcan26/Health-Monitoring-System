const byte RATE_SIZE = 4;
byte rates[RATE_SIZE]; // array of heart rates
byte rateSpot = 0;
long lastBeat = 0; // time at which the last beat occured

float beatsPerMinute = 0;
int beatAvg = 0;

// debug purpose
void serial_print_processed_bpm() {
  if (beatAvg != 0 && beatsPerMinute != 0) {
    Serial.print("BPM=");
    Serial.print(beatsPerMinute);
    Serial.print("; Avg BPM=");
    Serial.print(beatAvg);
    Serial.println();

    delay(100);    
  }
}

void process_bpm(long irValue) {
    if (checkForBeat(irValue) == true) {
    // sensed a beat!

    Serial.println("Sensed a beat!");

    long delta = millis() - lastBeat;
    lastBeat = millis();

    beatsPerMinute = 60 / (delta / 1000.0);

    if (beatsPerMinute < 255 && beatsPerMinute > 20) {
      rates[rateSpot++] = (byte)beatsPerMinute; // stores this reading in the array
      rateSpot %= RATE_SIZE; // wrap variable

      // take average of readings
      beatAvg = 0;
      for (byte x  = 0; x < RATE_SIZE; x++)
        beatAvg += rates[x];
      beatAvg /= RATE_SIZE;
    }

    serial_print_processed_bpm();
    delay(1000);
  }
}
