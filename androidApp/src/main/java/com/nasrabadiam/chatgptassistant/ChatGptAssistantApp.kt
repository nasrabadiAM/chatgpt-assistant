package com.nasrabadiam.chatgptassistant

import android.app.Application
import com.nasrabadiam.shared.initializeLogging

class ChatGptAssistantApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLogging()
    }
}