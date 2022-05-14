package com.jomibusa.challengemeli.interfaces

import androidx.recyclerview.widget.RecyclerView
import com.jomibusa.challengemeli.data.model.Attributes

interface IDetailItemCT {

    interface Presenter {

        fun start(recyclerView: RecyclerView, listAttributes: List<Attributes>)

    }

}