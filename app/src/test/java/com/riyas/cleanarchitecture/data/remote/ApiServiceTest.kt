package com.riyas.cleanarchitecture.data.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val client = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))  // Use the mock server URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `When server hits an api call, Then getRecipes should return success`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                    "recipes": [
                        { "id": 1, "name": "Recipe 1", "ingredients": ["ingredient1"] },
                        { "id": 2, "name": "Recipe 2", "ingredients": ["ingredient2"] }
                    ]
                }
            """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        val result = apiService.getRecipes()

        assertEquals(2, result.recipes.size)
        assertEquals("Recipe 1", result.recipes[0].name)
        assertEquals("ingredient1", result.recipes[0].ingredients[0])
    }

    @Test
    fun `When server unable to process the api call, Then getRecipes should return success`() {
        val mockResponse = MockResponse()
            .setResponseCode(500)  // Simulate a server error
            .setBody("Internal Server Error")

        mockWebServer.enqueue(mockResponse)

        // Act & Assert: Run inside a coroutine scope using runBlocking

        val exception = assertThrows(HttpException::class.java) {
            runBlocking {
                apiService.getRecipes()
            }// This is a suspend function
        }

        // Assert error code and message
        assertEquals(500, exception.code())  // Verify HTTP status code
        assertEquals("Server Error", exception.message())
    }
}

