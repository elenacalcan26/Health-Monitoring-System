package com.example.healthmonitoringsystem

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.healthmonitoringsystem.entities.MonitoredMeasurements
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class MeasurementsChartActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurements_chart)

        lineChart = findViewById(R.id.lineChart)

        val measurementsList =
            intent.getParcelableArrayListExtra<MonitoredMeasurements>("measurementsList")
        Log.d("MeasurementsChartActivity", measurementsList.toString())

        val bpmEntries = measurementsList?.let { extractBPMEntries(it) }

        bpmEntries?.let { (bpmValues, timestamps) ->
            displayLineChart(bpmValues, timestamps)
        }
    }

    private fun displayLineChart(bpmValues: List<Float>, timestamps: List<String>) {
        val entries = mutableListOf<Entry>()

        for (i in bpmValues.indices) {
            val bpm = bpmValues[i]
            val entry = Entry(i.toFloat(), bpm)
            entries.add(entry)
        }

        val dataSet = LineDataSet(entries, "BPM")
        dataSet.color = Color.BLUE
        dataSet.setCircleColor(Color.BLUE)

        val lineData = LineData(dataSet)

        lineChart.data = lineData
        lineChart.invalidate()

        // Axis config
        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(timestamps)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = 45f

        // Description config
        val description = Description()
        description.text = "Measurement Chart"
        lineChart.description = description
    }

    private fun extractBPMEntries(measurementsList: List<MonitoredMeasurements>): Pair<List<Float>, List<String>> {
        val bpmValues = mutableListOf<Float>()
        val timestamps = mutableListOf<String>()

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        for (measurement in measurementsList) {
            val timestampString = measurement.timestamp.toString()
            if (timestampString.isNotEmpty()) {
                try {
                    val timestamp = format.parse(timestampString)
                    val formattedTimestamp = format.format(timestamp)
                    val bpmMeasurement = measurement.values.find { it.measurementType == "BPM" }
                    val bpmValue = bpmMeasurement?.value?.toFloat() ?: 0f

                    timestamps.add(formattedTimestamp)
                    bpmValues.add(bpmValue)
                } catch (e: Exception) {
                    // Capturing the exception and logging the details
                    Log.e("ExtractBPMEntries", "Error parsing timestamp: $timestampString", e)
                }
            }
        }

        return Pair(bpmValues, timestamps)
    }

}
