package pl.edu.agh.eaiib.io.kotlindemo

import android.content.Context
import java.util.*

fun getServerBaseUrl(context: Context): String {
    val enableServer = getConfigProperty(ENABLE_SENDING_DATA_TO_SERVER_KEY, context)
    if (!enableServer.toBoolean()) {
        return ""
    }

    return getConfigProperty(DATA_SERVER_URL_KEY, context)
}

fun getConfigProperty(key: String, context: Context): String {
    val properties = Properties()
    val assetManager = context.assets
    val inputStream = assetManager.open("config.properties")
    properties.load(inputStream)
    return properties.getProperty(key)
}

const val ENABLE_SENDING_DATA_TO_SERVER_KEY = "enable.server"
const val DATA_SERVER_URL_KEY = "data.server"