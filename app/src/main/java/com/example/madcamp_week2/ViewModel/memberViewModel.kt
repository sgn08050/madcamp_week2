package com.example.madcamp_week2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class memberViewModel: ViewModel() {

    private val _member_id = MutableLiveData<String>()
    val member_id: LiveData<String> = _member_id

    fun updateMember_id(newId: String){
        _member_id.value = newId
    }

}