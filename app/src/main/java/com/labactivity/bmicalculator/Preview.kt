package com.labactivity.bmicalculator

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.labactivity.bmicalculator.databinding.ActivityPreviewBinding
import com.labactivity.bmicalculator.databinding.ActivityResultBinding
import kotlinx.coroutines.launch

class Preview : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding
    private lateinit var recordDao: RecordDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val courtesy = intent.getStringExtra("courtesy")
        val bmi = intent.getFloatExtra("bmi", 0f)
        val id = intent.getLongExtra("id",0)

        binding.courtesyTxt.setText(courtesy)
        binding.nameTxt.setText(name)
        binding.bmiTxt.setText("$bmi")
        assignCategory(bmi)


        binding.backBtn.setOnClickListener(){
            this.finish()
        }


        binding.confirmBtn.setOnClickListener(){
            this.finish()
        }

        binding.deleteBtn.setOnClickListener {

            lifecycleScope.launch{
                val database = DatabaseProvider.getDatabase(this@Preview)

                recordDao = database.recordDao()

                recordDao.deleteRecord(id)
            }

            val intent = Intent(this, ViewRecords::class.java)
            startActivity(intent)
            this.finish()

        }
    }

    private fun assignCategory(bmi:Float?){
        val red = Color.parseColor("#B12121")
        val green = Color.parseColor("#8BFF4D")
        if (bmi != null) {
            if (bmi > 30){
                binding.categoryTxt.setText("OBESE")
                binding.categoryTxt.setTextColor(red)
                binding.obeseTxt.setBackgroundColor(red)
            } else if(bmi >= 25 && bmi < 30){
                binding.categoryTxt.setText("OVERWEIGHT")
                binding.categoryTxt.setTextColor(red)
                binding.overTxt.setBackgroundColor(red)
            } else if (bmi >= 18.5 && bmi < 25){
                binding.categoryTxt.setText("HEALTHY WEIGHT")
                binding.categoryTxt.setTextColor(green)
                binding.healthyTxt.setBackgroundColor(green)
            } else if (bmi < 18.5){
                binding.categoryTxt.setText("UNDERWEIGHT")
                binding.categoryTxt.setTextColor(red)
                binding.underTxt.setBackgroundColor(red)
            }
        }
    }
}