package com.samples.coroutinestesting.usecases

import com.samples.coroutinestesting.base.BaseUT
import com.samples.coroutinestesting.di.configureTestAppComponent
import com.samples.coroutinestesting.repo.MainActivityRepo
import com.samples.digisky.models.login.AllPeople
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

class UseCaseTest : BaseUT() {

    lateinit var mUseCase: UseCases

    //Inject required dependency for KOIN. Or else test will fail to startKoin
    val repo: MainActivityRepo by inject()
    val mockWebServer : MockWebServer by inject()

    // Override and start/stop mockserver ---
    override fun isMockServerEnabled() = true

    @Before
    fun start(){
        super.setUp()
        MockKAnnotations.init(this)
        startKoin{ modules(configureTestAppComponent(getMockUrl()))}
    }

    @Test
    fun test_positive_data_flow()= runBlocking{
        mockHttpResponse("success_resp_list.json", HttpURLConnection.HTTP_OK)

        mUseCase = UseCases()
        val dataReceived = mUseCase.fetchData("1")
        Assert.assertNotNull(dataReceived)
        assert(dataReceived is AllPeople)
        Assert.assertEquals(dataReceived.count, 87)
        Assert.assertEquals(dataReceived.next, "https://swapi.co/api/people/?page=2")
    }
}