package com.example.contacts_room_sqlite_android_app_kotlin

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    val allContacts: Flow<List<Contact>> = contactDao.getAlphabetizedContacts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }
    @WorkerThread
    suspend fun deleteAll() {
        contactDao.deleteAll()
    }
}