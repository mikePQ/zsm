package pl.edu.agh.eaiib.io.kotlindemo

import android.content.Context
import java.util.*

fun getConfigProperty(key: String, context: Context): String {
    val properties = Properties()
    val assetManager = context.assets
    val inputStream = assetManager.open("config.properties")
    properties.load(inputStream)
    return properties.getProperty(key)
}

const val DATA_SERVER_URL_KEY = "data.server"