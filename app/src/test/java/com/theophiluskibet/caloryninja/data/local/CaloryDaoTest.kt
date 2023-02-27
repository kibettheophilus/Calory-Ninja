package com.theophiluskibet.caloryninja.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.theophiluskibet.caloryninja.data.mappers.toEntity
import com.theophiluskibet.caloryninja.data.utils.calories
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CaloryDaoTest {
    // subject under test
    private lateinit var caloryDao: CaloryDao

    // helpers
    private lateinit var caloryDatabase: CaloryDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        caloryDatabase = Room.inMemoryDatabaseBuilder(context, CaloryDatabase::class.java)
            .allowMainThreadQueries().build()
        caloryDao = caloryDatabase.caloryDao()
    }

    @After
    fun close() {
        caloryDatabase.close()
    }

    @Test
    fun `save calories to db and retreive`() = runTest {
        caloryDao.saveCalories(calories.map { it.toEntity() })

        val result = caloryDao.getCalories()

        assertThat(result).isNotEmpty()
    }

    @Test
    fun `get calory by name`() = runTest {
        caloryDao.saveCalories(calories.map { it.toEntity() })

        val result = caloryDao.getCalory("meat")

        assertThat(result.name).isEqualTo("meat")
    }

    @Test
    fun `get calories by list of names`() = runTest {
        caloryDao.saveCalories(calories.map { it.toEntity() })

        val result = caloryDao.getCaloriesByNames(listOf("rice", "meat"))

        assertThat(result.size).isEqualTo(2)
    }
}
