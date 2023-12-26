package com.example.dt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dt.db.MyDBManager
import java.text.ParseException
import java.time.LocalDate
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {
    val myDBManager = MyDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateEditText = findViewById<EditText>(R.id.pt)
        val subtractButton = findViewById<Button>(R.id.but)
        val lab = findViewById<TextView>(R.id.tl)


        fun isValidDate(date: String): Boolean {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            dateFormat.isLenient = false

            try {
                dateFormat.parse(date)
                return true
            } catch (e: ParseException) {
                return false
            }
        }

        subtractButton.setOnClickListener {
            val inputDate = dateEditText.text.toString()
            if (isValidDate(inputDate)) {
                if (inputDate.isNotEmpty()) {
                    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    val currentDate = dateFormat.format(Date())

                    val inputDateTime = dateFormat.parse(inputDate)?.time ?: 0
                    val currentDateTime = dateFormat.parse(currentDate)?.time ?: 0
                    val difference = currentDateTime - inputDateTime

                    val days = (difference / (1000 * 60 * 60 * 24)).toInt()
                    lab.text = days.toString()
                }else
                {
                    Toast.makeText(this, "Некорректное значение", Toast.LENGTH_SHORT).show()
                }
                }
            }

        }

    override fun onResume() {
        myDBManager.openDb()
        val tv = findViewById<TextView>(R.id.tvTest)
        super.onResume()
        val dataList = myDBManager.readDbData()
        for(item in dataList){
            tv.append(item)
            tv.append("\n")
        }
    }

    fun OnClickSave(view: View)
    {
        val title = findViewById<EditText>(R.id.edTitle)
        val content = findViewById<EditText>(R.id.edContent)
        val tv = findViewById<TextView>(R.id.tvTest)
        tv.text = ""

        myDBManager.insertToDb(title.text.toString(),content.text.toString())

    }

    override fun onDestroy() {
        super.onDestroy()
       myDBManager.closeDb()

    }
}

