package com.example.contacts_room_sqlite_android_app_kotlin

import android.content.Intent

interface onIntentReceived {
    fun onIntent(intent: Intent, resultCode: Int)
}