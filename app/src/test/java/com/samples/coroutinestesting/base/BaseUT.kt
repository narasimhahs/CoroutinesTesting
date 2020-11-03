package com.samples.coroutinestesting.base

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File


abstract class BaseUT : KoinTest {

    /**
     * For getting data
     */

    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp(){
        configureMockServer()
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    /**
     * Reads input file and converts to json
     */
    fun getJson(path : String) : String {
        val uri = javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    /**
     * MOCK SERVER ---
     */
    abstract fun isMockServerEnabled(): Boolean

    private fun configureMockServer(){
        if (isMockServerEnabled()){
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    private fun stopMockServer() {
        if (isMockServerEnabled()){
            mockServer.shutdown()
        }
    }

    fun getMockUrl() = mockServer.url("/").toString()

    @After
    open fun tearDown(){
        stopMockServer()
        stopKoin()
    }

}