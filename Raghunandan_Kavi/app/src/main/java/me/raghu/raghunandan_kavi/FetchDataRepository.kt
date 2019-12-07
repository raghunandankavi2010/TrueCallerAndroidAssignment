package me.raghu.raghunandan_kavi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class FetchDataRepository {


    @ExperimentalCoroutinesApi
    fun fetchEveryTenthChar(): Flow<String> = flow {
        emit(fetchEvery10thCharacter())

    }.flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    fun fetchTenthChar(): Flow<String> = flow {
        emit(fetch10thCharacter())

    }.flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    fun fetchCountWords(): Flow<String> = flow {
        emit(fetchAll())

    }.flowOn(Dispatchers.IO)

    fun fetch10thCharacter(): String {

        var output = ""
         try {
            var inputStream: InputStream? = null
            try {
                val url = URL(URL)
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.readTimeout = 10000
                conn.connectTimeout = 15000
                conn.requestMethod = "GET"
                conn.doInput = true
                conn.connect()
                val responseCode: Int = conn.responseCode
                if (responseCode == 200) {
                    inputStream = conn.inputStream
                    inputStream?.let {
                        val reader: Reader = InputStreamReader(inputStream, "UTF-8")
                        var ch = 0
                        for (i in 0..9) {
                            ch = reader.read()
                        }
                        output = "Tenth Character is $ch"

                    }
                }else{
                    output = "Something went wrong!$responseCode"
                }
            } finally {
                inputStream?.close()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
             output = e.message.toString()

        } catch (e:IOException) {
            e.printStackTrace()
             output = e.message.toString()
        }
        return output
    }

     fun fetchEvery10thCharacter(): String {
        var output = ""
        try {
            var inputstream: InputStream? = null
            try {
                val url = URL(URL)
                val conn =
                    url.openConnection() as HttpURLConnection
                conn.readTimeout = 10000
                conn.connectTimeout = 15000
                conn.requestMethod = "GET"
                conn.doInput = true
                conn.connect()
                val responseCode = conn.responseCode
                if (responseCode == 200) {
                    inputstream = conn.inputStream
                    inputstream?.let {
                        val reader: Reader = InputStreamReader(inputstream, "UTF-8")
                        val sb = StringBuilder()
                        sb.append("Every 10th character in the web page text is '")
                        var i = 0
                        var j = 0
                        var ch: Int
                        while (reader.read().also { ch = it } != -1) {
                            if (i > 0 && i % 10 == 0) {
                                sb.append(ch.toChar())
                                if (j > 0 && j % 10 == 0) sb.append(' ')
                                j++
                            }
                            i++
                        }
                        sb.append("'")
                        output = sb.toString()
                    }
                }else{
                    output = "Something went wrong!$responseCode"
                }
            } finally {
                inputstream?.close()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            output = e.message.toString()

        } catch (e:IOException) {
            e.printStackTrace()
            output = e.message.toString()
        }
       return output
    }

     fun fetchAll(): String {
        var output = ""
         try {
            var inputstream: InputStream? = null
            try {
                val url = URL(URL)
                val conn =
                    url.openConnection() as HttpURLConnection
                conn.readTimeout = 10000
                conn.connectTimeout = 15000
                conn.requestMethod = "GET"
                conn.doInput = true
                conn.connect()
                val responseCode = conn.responseCode
                if (responseCode == 200) {
                    inputstream = conn.inputStream
                    inputstream?.let {
                        val reader: Reader = InputStreamReader(inputstream, "UTF-8")
                        val br = BufferedReader(reader)
                        val wc: MutableMap<String, Int?> = TreeMap()
                        var line: String? = null
                        while (br.readLine().also { line = it } != null) {
                            val tokens =
                                line!!.split("[\\s\\d~`!@#\\$%\\^&\\*\\(\\)\\-\\+\\[\\]\\{\\}\'\"\\\\|/\\?,\\.;:]+")
                                    .toTypedArray()

                            for (element in tokens) {
                                var token = element
                                if (token == "") continue
                                token = token.toLowerCase()
                                var n = if (wc.containsKey(token)) wc[token]!! else 0
                                n++
                                wc[token] = n
                            }
                        }
                        val sb = java.lang.StringBuilder()
                        sb.append("result:\n")
                        for ((key, value) in wc) {
                            sb.append(key)
                                .append(" : ")
                                .append(value)
                                .append("\n")
                        }
                        output = sb.toString()
                    }
                }else{
                    output = "Something went wrong!$responseCode"
                }
            } finally {
                inputstream?.close()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
             output = e.message.toString()

        } catch (e:IOException) {
            e.printStackTrace()
             output = e.message.toString()
        }
        return output
    }
}