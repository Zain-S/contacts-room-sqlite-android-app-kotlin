package com.example.contacts_room_sqlite_android_app_kotlin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Context, dataSet: ArrayList<Contact>, private val _onClickListener: OnClickListeners):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    lateinit var tvSNo: TextView
    lateinit var tvName: TextView
    lateinit var tvPhoneNumber: TextView
    lateinit var clView: ConstraintLayout
    private var contacts = dataSet
    private var onClickListener = _onClickListener

    interface OnClickListeners{
        fun onClick(contact: Contact, context: Context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_contact, parent, false)
        tvName = view.findViewById(R.id.tv_layout_name)
        tvPhoneNumber = view.findViewById(R.id.tv_layout_phone_number)
        clView = view. findViewById(R.id.cl_view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Contacts",contacts.toString())
        tvName.text = (contacts[position].name)
        tvPhoneNumber.text = (contacts[position].phoneNumber)
        clView.setOnClickListener {
            onClickListener.onClick(contacts[holder.adapterPosition], context)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun updateList(contactList: ArrayList<Contact>){
        contacts = contactList
        notifyDataSetChanged()
    }
    fun addContact(contact: Contact){
        contacts.add(contact)
    }

    fun addContactList(contactList: List<Contact>){
        contacts.addAll(contactList)
        notifyDataSetChanged()
    }

    fun fetchData(contactList: List<Contact>){
        contacts.addAll(contactList)
    }

    fun deleteData(position: Int){
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }
}