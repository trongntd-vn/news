package com.ntdtrong.news.helper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*
import javax.inject.Inject

private const val CACHE_DIR = "response_cached"

class FileHelper @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val cacheDir = File(context.cacheDir, CACHE_DIR)

    init {
        if (cacheDir.exists().not()) {
            cacheDir.mkdir()
        }
    }

    suspend fun getCache(key: String) = withContext(Dispatchers.IO) {
        val cachedFile = cacheDir.listFiles { _, name ->
            name == key.fileName
        }?.firstOrNull()
        if (cachedFile != null) {
            readFile(cachedFile)
        } else {
            ""
        }
    }

    suspend fun saveCache(key: String, content: String) = withContext(Dispatchers.IO) {
        val file = File(cacheDir, key.fileName)
        writeFile(file, content)
    }

    private val String.fileName: String
        get() = this.hashCode().toString()

    private fun readFile(file: File): String {
        val text = StringBuffer()
        var br: BufferedReader? = null
        try {
            br = BufferedReader(FileReader(file))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append("\n")
            }
        } catch (io: IOException) {
        } finally {
            br?.close()
        }
        return text.toString()
    }

    private fun writeFile(file: File, content: String) {
        val stream = FileOutputStream(file)
        stream.use {
            it.write(content.toByteArray())
        }
    }
}