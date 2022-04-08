package com.example.contacts_room_sqlite_android_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactDetails : AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var tvPhoneNumber: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        tvName = findViewById(R.id.tv_name)
        tvPhoneNumber = findViewById(R.id.tv_phone_number)

        tvName.text =  intent.getStringExtra("name").toString()
        tvPhoneNumber.text =  getIntent().getStringExtra("phoneNumber").toString()
    }
}