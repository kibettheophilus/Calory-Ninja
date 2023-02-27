package com.theophiluskibet.caloryninja.data.datasource

import com.google.common.truth.Truth.assertThat
import com.theophiluskibet.caloryninja.data.local.CaloryDao
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.data.mappers.toEntity
import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi
import com.theophiluskibet.caloryninja.utils.calories
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CaloryRepositoryImplTest {

    // subject under test
    private lateinit var caloryRepositoryImpl: CaloryRepositoryImpl

    // helpers
    private lateinit var caloryDao: CaloryDao
    private lateinit var caloryApi: CaloryApi
    private val caloryEntity = mockk<CaloryEntity>(relaxed = true)

    @Before
    fun setup() {
        caloryDao = mockk()
        caloryApi = mockk()
        caloryRepositoryImpl = CaloryRepositoryImpl(caloryDao = caloryDao, caloryApi = caloryApi)
    }

    @Test
    fun `get calory returns a single calory from local`() = runTest {
        // given
        coEvery { caloryDao.getCalory("rice") } returns caloryEntity

        // when
        val result = caloryRepositoryImpl.getCalory("rice")

        // then
        assertThat(result).isEqualTo(caloryEntity)
    }

    @Test
    fun `get saved calories returns list of calories from local`() = runTest {
        // given
        coEvery { caloryDao.getCalories() } returns calories.map { it.toEntity() }

        // when
        val result = caloryRepositoryImpl.getSavedCalories()

        // then
        assertThat(result).isEqualTo(calories.map { it.toEntity() })
    }

//    @Test
//    fun `get calories returns list of calories`() = runTest {
//        coEvery { caloryApi.getCalories("rice") } returns Response<Cal>
//    }
}
