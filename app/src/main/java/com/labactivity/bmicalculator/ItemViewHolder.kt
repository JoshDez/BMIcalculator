package com.labactivity.bmicalculator

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.labactivity.bmicalculator.databinding.ItemLayoutBinding

class ItemViewHolder(private val context: Context, private val binding: ItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    private var id:Long = 0
    private var name:String = ""
    private var courtesy:String = ""
    private var bmi:Float = 0f



    init {
        binding.root.setOnClickListener(this)
    }
    fun bind(item: Item){
        //initializing data members
        id = item.id
        name = item.name
        courtesy = item.courtesy
        bmi = item.bmi

        //binding data
        binding.bmiTxt.text = "BMI: $bmi"
        binding.nameTxt.text = name


    }

    override fun onClick(p0: View?) {
        val intent = Intent(context, Preview::class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("courtesy", courtesy)
        intent.putExtra("bmi", bmi)
        intent.putExtra("toMainMenu", false)
        context.startActivity(intent)
        
    }

}