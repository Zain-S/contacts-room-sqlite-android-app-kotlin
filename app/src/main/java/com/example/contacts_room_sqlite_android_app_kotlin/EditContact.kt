package com.example.contacts_room_sqlite_android_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditContact : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var btSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        etName = findViewById(R.id.et_name)
        etPhoneNumber = findViewById(R.id.et_phone_number)
        btSave = findViewById(R.id.bt_save)

        etName.setText(intent.getStringExtra("name"))
        etPhoneNumber.setText(intent.getStringExtra("phoneNumber"))
        btSave.setOnClickListener{

        }
    }
}