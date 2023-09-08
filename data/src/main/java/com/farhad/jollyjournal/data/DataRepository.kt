package com.farhad.jollyjournal.data

import com.farhad.jollyjournal.domain.MyClass
import javax.inject.Inject

class DataRepository @Inject constructor(val myClass: MyClass) {
    fun act() {
        myClass.print()
    }
}