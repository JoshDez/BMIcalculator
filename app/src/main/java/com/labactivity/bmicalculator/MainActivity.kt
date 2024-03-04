package com.labactivity.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.labactivity.bmicalculator.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recordDao: RecordDao
    private var name:String = ""
    private var firstName:String = ""
    private var lastName:String = ""
    private var mi:String = ""
    private var courtesy:String = ""
    private var height:Float = 0f
    private var weight:Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmBtn.setOnClickListener(){
            //Initializing variables
            firstName = binding.firstNameEdtTxt.text.toString()
            mi = "${binding.miEdtTxt.text}."
            lastName = binding.lastNameEdtTxt.text.toString()
            name = "$firstName $mi $lastName"
            if (binding.heightEdtTxt.text.toString() != ""){
                height = binding.heightEdtTxt.text.toString().toFloat() / 100
            }
            if (binding.weightEdtTxt.text.toString() != ""){
                weight = binding.weightEdtTxt.text.toString().toFloat()

            }

            //Verification
            if(firstName == "" || lastName == ""){
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if(!binding.maleRB.isChecked && !binding.femaleRB.isChecked){
                Toast.makeText(this, "Please select your gender", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if(height == 0f && weight == 0f){
                Toast.makeText(this, "Please enter your height/weight", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            //Calculating BMI
            var def = DecimalFormat("#,###,###.00")
            var bmi = weight / (height * height)
            var temp = def.format(bmi)
            bmi = temp.toFloat()


            height = 0f
            weight = 0f


            //add to database
            lifecycleScope.launch {
                val record = Record(name = name, courtesy = courtesy, bmi = bmi)

                val database = DatabaseProvider.getDatabase(this@MainActivity)

                recordDao = database.recordDao()

                recordDao.insertRecord(record)


            }

            //Intent to Result activity
            val intent = Intent(this, Result::class.java)
            intent.putExtra("name", name)
            intent.putExtra("courtesy", courtesy)
            intent.putExtra("bmi", bmi)
            intent.putExtra("toMainMenu", true)
            startActivity(intent)


        }

        binding.femaleRB.setOnClickListener(){
            binding.maleRB.isChecked = false
            courtesy = "Ms."

        }

        binding.maleRB.setOnClickListener(){
            binding.femaleRB.isChecked = false
            courtesy = "Mr."
        }


    }
}