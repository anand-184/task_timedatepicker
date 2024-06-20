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

private operator fun IntRange.invoke(value: () -> Unit) {

}



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
        binding?.date?.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selecteddate = Calendar.getInstance()
                selecteddate.set(Calendar.YEAR, year)
                selecteddate.set(Calendar.MONTH, month)
                selecteddate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val pickedDate = simpleDateFormat.format(selecteddate.time)
                var currentDate = (now.get(Calendar.YEAR);now.get(Calendar.MONTH);now.get(Calendar.DAY_OF_MONTH))
                if(pickedDate in ((currentDate.plus(10))..(currentDate.minus(10))){
                        binding?.time?.setText(pickedDate)
                } else{
                    Toast.makeText(this,"invalid date",Toast.LENGTH_LONG).show()

                }
            },

            now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()


        }
        binding?.time?.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                var hour = selectedTime.get(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.get(Calendar.MINUTE,minute)
                val pickedTime = simpleTimeFormat.format(selectedTime.time)
                if(hour in 9..6){
                    binding?.time?.text = pickedTime
                }else{
                    Toast.makeText(this,"hour Constraint is from 9 to 6",Toast.LENGTH_LONG).show()
                }


            },
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
            timePicker.show()
        }

    }

}