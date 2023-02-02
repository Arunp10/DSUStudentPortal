package com.example.testing.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.R
import com.example.testing.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModal : ViewModel() {

    private var ArticleslIST: MutableLiveData<List<Articles>> = MutableLiveData()

    fun getAllnEWSRecords(): LiveData<List<Articles>>{
        viewModelScope.launch(Dispatchers.IO ){
            val records = arrayListOf<Articles>()
            for(i in 0..10){
                records.add(
                    Articles(
                        author = toString(),
                        title = toString(),
                        
                        ))

                favRecordList.postValue(records)
            }
        }
        return favRecordList;
    }
}