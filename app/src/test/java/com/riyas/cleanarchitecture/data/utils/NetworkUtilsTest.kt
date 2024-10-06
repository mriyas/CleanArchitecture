package com.riyas.cleanarchitecture.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUtilsTest {

    private val context: Context = mock()
    private val connectivityManager: ConnectivityManager = mock()
    private val networkCapabilities: NetworkCapabilities = mock()

    private lateinit var networkUtils: NetworkUtils

    @Before
    fun setup() {
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        networkUtils = NetworkUtils(context)
    }

    @Test
    fun `When network transport is WiFi, Then network check should return true`() {
        `when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(networkCapabilities)
        `when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).thenReturn(true)

        val result = networkUtils.isOnline()
        assertTrue(result)
    }

    @Test
    fun `When network transport is NONE, Then network check should return true`() {
        `when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(null)

        val result = networkUtils.isOnline()
        assertFalse(result)
    }
}
