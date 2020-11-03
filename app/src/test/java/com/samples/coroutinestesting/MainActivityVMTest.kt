package com.samples.coroutinestesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.coroutinestesting.base.BaseUT
import com.samples.coroutinestesting.di.configureTestAppComponent
import com.samples.coroutinestesting.networking.LiveDataResult
import com.samples.coroutinestesting.usecases.UseCases
import com.samples.digisky.models.login.AllPeople
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class MainActivityVMTest: BaseUT() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Override and start/stop mockserver ---
    override fun isMockServerEnabled() = true

    val dispatcher = Dispatchers.Unconfined

    lateinit var mainActivityVM: MainActivityVM

    @MockK
    lateinit var useCase: UseCases

    @Before
    fun start(){
        super.setUp()
        MockKAnnotations.init(this)
        startKoin{ modules(configureTestAppComponent(getMockUrl()))}
    }

    @Test
    fun test_positive_data_flow(){

        mainActivityVM = MainActivityVM(dispatcher,dispatcher,useCase)

        val sampleResponse = getJson("success_resp_list.json")
        var jsonObj = Gson().fromJson(sampleResponse,AllPeople::class.java)
        coEvery { useCase.fetchData(any()) } returns jsonObj

        mainActivityVM.mAllPeopleResponse.observeForever {}

        mainActivityVM.startFetchData("1")

        assert(mainActivityVM.mAllPeopleResponse.value != null)
        assert(mainActivityVM.mAllPeopleResponse.value!!.status == LiveDataResult.STATUS.SUCCESS)

        var testResult = mainActivityVM.mAllPeopleResponse.value as  LiveDataResult<AllPeople>
        assertEquals(testResult.data!!.next,"https://swapi.co/api/people/?page=2")
    }

}