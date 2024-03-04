package com.labactivity.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.labactivity.bmicalculator.databinding.ActivityViewRecordsBinding
import kotlinx.coroutines.launch

class ViewRecords : AppCompatActivity() {
    private lateinit var binding:ActivityViewRecordsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recordDao: RecordDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.myList
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        lifecycleScope.launch{
            val database = DatabaseProvider.getDatabase(this@ViewRecords)

            recordDao = database.recordDao()

            val records = recordDao.getAllRecords()
            val listsOfRecords:ArrayList<Item> = ArrayList()


            for (list in records){
                val item = Item(id = list.id, name = list.name, courtesy = list.courtesy, bmi = list.bmi)
                listsOfRecords.add(item)
            }

            recyclerView.adapter = ItemAdapter(this@ViewRecords, listsOfRecords)
        }

        binding.backBtn.setOnClickListener(){
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            this.finish()
        }



    }
}