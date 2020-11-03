package com.samples.coroutinestesting.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.coroutinestesting.base.BaseUT
import com.samples.coroutinestesting.di.configureTestAppComponent
import com.samples.coroutinestesting.networking.LoginAPIService
import com.samples.digisky.models.login.AllPeople
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MainActivityRepoTest : BaseUT(){

    //Target
    private lateinit var mRepo: MainActivityRepo

    // Override and start/stop mockserver ---
    override fun isMockServerEnabled() = true

    //Inject required dependency for KOIN. Or else test will fail to startKoin
    val mAPIService : LoginAPIService by inject()
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()
        MockKAnnotations.init(this)
        startKoin{ modules(configureTestAppComponent(getMockUrl()))}
    }

    @Test
    fun testRepoFetchDataAction() =  runBlocking<Unit>{

        mockHttpResponse("success_resp_list.json", HttpURLConnection.HTTP_OK)

        mRepo = MainActivityRepo()

        val dataReceived = mRepo.fetchData("1")

        assertNotNull(dataReceived)
        assert(dataReceived is AllPeople)
        assertEquals(dataReceived.count, 87)
        assertEquals(dataReceived.next, "https://swapi.co/api/people/?page=2")
    }
}