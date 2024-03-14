package com.example.binlist.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.binlist.database.entity.BankCardDBO
import com.example.core.Const.SEPARATOR
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoomTest {


    private lateinit var db: BankCardInfoDataBase
    private lateinit var dao: BankCardInfoDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, BankCardInfoDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getBankCardInfoDao()
    }

    @After
    @Throws
    fun clear(){
        db.close()
    }

    @Test
    fun test_add() = runBlocking{
        assertEquals(emptyList<BankCardDBO>(), dao.getAllBankCardInfo().first())

        dao.saveBankCardInfoDao(bankCardDBO = cache)
        assertEquals(listOf(cache), dao.getAllBankCardInfo().first())

        assertEquals(cache, dao.getById(ID))
        assertEquals(cache, dao.getNumber(SEPARATOR))
    }

    private val cache = BankCardDBO(
        ID,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR ,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR
    )

    private companion object{
        const val ID = 1L
    }

}