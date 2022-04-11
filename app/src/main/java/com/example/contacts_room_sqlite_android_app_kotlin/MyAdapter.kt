package com.example.contacts_room_sqlite_android_app_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(_onClickListener: OnClickListeners): RecyclerView.Adapter<MyAdapter.ViewHolder>(), onIntentReceived{

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    private lateinit var tvName: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var clView: ConstraintLayout
    private var contactsList: ArrayList<Contact> = ArrayList()
    private lateinit var context: Context
    private var onClickListener = _onClickListener


    interface OnClickListeners{
        fun onClick(contact: Contact, context: Context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //initialize
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_contact, parent, false)
        tvName = view.findViewById(R.id.tv_layout_name)
        tvPhoneNumber = view.findViewById(R.id.tv_layout_phone_number)
        clView = view. findViewById(R.id.cl_view)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tvName.text = (contactsList[position].name)
        tvPhoneNumber.text = (contactsList[position].phoneNumber)
        clView.setOnClickListener {
            (context as Activity)
                .startActivityForResult(Intent((context), ContactDetails::class.java)
                .putExtra("name", tvName.text.toString())
                .putExtra("phoneNumber", tvPhoneNumber.text.toString())
                , Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    fun updateList(contactList: ArrayList<Contact>){
        contactsList = contactList
        notifyDataSetChanged()
    }
    fun addContact(contact: Contact){
        contactsList.add(contact)
    }

    fun addContactList(contactList: List<Contact>){
        contactsList.addAll(contactList)
        notifyDataSetChanged()
    }

    fun fetchData(contactList: List<Contact>){
        contactsList.addAll(contactList)
    }

    fun deleteData(position: Int): Contact{
        val contact: Contact = contactsList[position]
        contactsList.removeAt(position)
        notifyItemRemoved(position)
        return contact
    }

    override fun onIntent(intent: Intent, resultCode: Int) {
        updateList(Contact(intent.getStringExtra("name").toString(), intent.getStringExtra("phoneNumber").toString()) as ArrayList<Contact>)
    }
}