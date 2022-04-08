package com.example.contacts_room_sqlite_android_app_kotlin

class Contact(_sNo: Int, _phoneNumber: String, _name: String) {
    private var sNo = _sNo
    private var phoneNumber: String = _phoneNumber
    private var name:String = _name

    fun getSno(): String{
        return sNo.toString()
    }
    fun getPhoneNumber(): String{
        return phoneNumber
    }
    fun getName(): String{
        return name
    }

    override fun toString(): String {
        return getSno()+getName()+getPhoneNumber()
    }
}