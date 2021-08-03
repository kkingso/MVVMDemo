package com.kkw.mymvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kkw.mymvvm.databinding.ActivityMainBinding
import com.kkw.mymvvm.logic.network.appObserve
import com.kkw.mymvvm.ui.group.GroupListActivity
import com.kkw.mymvvm.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

//            model.getGroupInfo("C6D60A2539B74924Yf7af7EL",0)?.appObserve(this,{
//                binding.tv.text = "${it.groups[0].groupName}"
//            })

            GroupListActivity.startActivity(this)

        }
    }
}