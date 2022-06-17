package com.example.crud_oparation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_oparation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var list = arrayListOf<ModalData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var db = DBHelper(this)

        binding.submitBtn.setOnClickListener {

            db.insertData(
                binding.amountEdt.text.toString(),
                binding.detailsEdt.text.toString(),
                binding.dateEdt.text.toString()
            )
            var list = db.ReadData()
            setUpRV(list)
        }
    }

    fun setUpRV(l1: ArrayList<ModalData>) {
        var adapter = Home_Adaptor(this, l1)
        var lm = LinearLayoutManager(this)
        binding.recyclerHome.layoutManager = lm
        binding.recyclerHome.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        list = DBHelper(this).ReadData()
        setUpRV(list)
    }
}