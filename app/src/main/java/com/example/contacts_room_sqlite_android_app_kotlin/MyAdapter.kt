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
        tvSNo = view.findViewById(R.id.tv_sr_no)
        tvName = view.findViewById(R.id.et_name)
        tvPhoneNumber = view.findViewById(R.id.et_phone_number)
        clView = view. findViewById(R.id.cl_view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Contacts",contacts.toString())
        tvSNo.text = (contacts[position].getSno())
        tvName.text = (contacts[position].getName())
        tvPhoneNumber.text = (contacts[position].getPhoneNumber())
        clView.setOnClickListener {
            onClickListener.onClick(contacts[holder.adapterPosition], context)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun addData(contact: Contact){
        contacts.add(contact)
        Log.d("debugging",contacts.toString())
    }

    fun deleteData(position: Int){
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }
}