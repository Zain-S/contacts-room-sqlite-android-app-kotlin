package com.example.contacts_room_sqlite_android_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactDetails : AppCompatActivity() {
    lateinit var tvSerialNumber: TextView
    lateinit var tvName: TextView
    lateinit var tvPhoneNumber: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        tvSerialNumber = findViewById(R.id.tv_serial_number)
        tvName = findViewById(R.id.et_name)
        tvPhoneNumber = findViewById(R.id.et_phone_number)

        tvSerialNumber.append(intent.getStringExtra("serialNumber").toString())
        tvName.text =  intent.getStringExtra("name").toString()
        tvPhoneNumber.text =  getIntent().getStringExtra("phoneNumber").toString()
    }
}