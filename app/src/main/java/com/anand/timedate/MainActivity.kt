package com.anand.timedate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anand.timedate.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding? = null
   private var simpleDateFormat = SimpleDateFormat("dd MMM,yyyy", Locale.US)
    private var simpleTimeFormat = SimpleDateFormat("hh:mm:ss",Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.btndate?.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->

                val selecteddate = Calendar.getInstance()
                selecteddate.set(Calendar.YEAR, year)
                selecteddate.set(Calendar.MONTH, month)
                selecteddate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            },

            now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            val calendar=Calendar.getInstance()
            calendar.add(Calendar.DATE,-10)
            datePicker.datePicker.minDate = calendar.timeInMillis
            calendar.add(Calendar.DATE,20)
            datePicker.datePicker.maxDate = calendar.timeInMillis
            datePicker.show()

        }
        binding?.btntime?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                var hour = selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                var minute = selectedTime.set(Calendar.MINUTE,minute)
                var calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY,9)
                calendar.set(Calendar.HOUR_OF_DAY,6)
                binding?.btntime?.setText(simpleTimeFormat.format(selectedTime))
            },
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)

            timePicker.show()
        }

    }

}