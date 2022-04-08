package com.example.contacts_room_sqlite_android_app_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_room_sqlite_android_app_kotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private lateinit var contacts: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contacts = ArrayList()
        val o1 = Contact(1, "1", "Zain")
        val o2 = Contact(2, "2", "Harry")
        val o3 = Contact(3, "3", "Saud")
        val o4 = Contact(4, "4", "Waleed")
        val o5 = Contact(5, "5", "Hamza")
        val o6 = Contact(6, "6", "Harry")
        val o7 = Contact(7, "7", "Harry")
        val o8 = Contact(8, "8", "Harry")
        val o9 = Contact(9, "9", "Harry")
        contacts.addAll(arrayListOf(o1, o2, o3, o4, o5, o6, o7, o8, o9))
        Log.d("Contacts", contacts.toString())

        //binding root
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        val onClickListener = object: MyAdapter.OnClickListeners{
            override fun onClick(contact: Contact, context: Context) {
                context.startActivity(Intent(context, ContactDetails::class.java)
                    .putExtra("serialNumber", contact.getSno())
                    .putExtra("name", contact.getName())
                    .putExtra("phoneNumber", contact.getPhoneNumber())
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }

        // adapter
        adapter = MyAdapter(this.applicationContext, contacts, onClickListener)

        // swipe functionality
        val swipeGesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT -> {
                        adapter.deleteData(viewHolder.absoluteAdapterPosition)
                    }
                }
            }
        }
        val techHelper = ItemTouchHelper(swipeGesture)
        techHelper.attachToRecyclerView(binding.rvContacts)

        binding.rvContacts.adapter = adapter

        //get Data from AddContact class
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    adapter.addData(Contact(contacts.size+1, data.getStringExtra("phoneNumber").toString(), data.getStringExtra("name").toString()))
                }
                adapter = MyAdapter(this.applicationContext, contacts, onClickListener)
                binding.rvContacts.adapter = adapter
            }
        }
        binding.btFloat.setOnClickListener{
            resultLauncher.launch(Intent(this, AddContact::class.java))
        }
    }
}