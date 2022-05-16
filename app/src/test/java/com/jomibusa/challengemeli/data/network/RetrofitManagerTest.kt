package com.jomibusa.challengemeli.data.network

import com.jomibusa.challengemeli.interfaces.IListItemCT
import com.jomibusa.challengemeli.presenter.ListItemPT
import io.mockk.MockKAnnotations
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.times


class RetrofitManagerTest {

    private lateinit var presenter: IListItemCT.Presenter

    @Mock
    private val view: IListItemCT.View? = null


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        presenter = ListItemPT(view)
    }

    @Test
    fun doConsumeOfAWordFromApiAndReturnTheModel() {
        presenter.start("Motorola")
    }

}