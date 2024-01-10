package com.example.madcamp_week2.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class memberViewModel: ViewModel() {

    private val _member_id = MutableLiveData<String>()
    val member_id: LiveData<String> = _member_id

    fun updateMember_id(newId: String){
        _member_id.value = newId
    }

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    fun updateId(newId: String){
        _id.value = newId
    }

    private val _group_id = MutableLiveData<String>()
    val group_id: LiveData<String> = _group_id

    fun updateGroup_id(newId: String){
        _group_id.value = newId
    }

}