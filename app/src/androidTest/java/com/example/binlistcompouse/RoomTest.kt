package com.example.binlistcompouse

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.data.cache.BankCardInfoDao
import com.example.binlistcompouse.data.cache.BankCardInfoDataBase
import com.example.binlistcompouse.data.cache.entity.BankCardInfoEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws


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
        assertEquals(emptyList<BankCardInfoEntity>(), dao.getAllBankCardInfo().first())

        dao.saveBankCardInfoDao(bankCardInfoEntity = cache)
        assertEquals(listOf(cache), dao.getAllBankCardInfo().first())

        assertEquals(cache, dao.getById(ID))
        assertEquals(cache, dao.getNumber(SEPARATOR))
    }

    private val cache = BankCardInfoEntity(
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