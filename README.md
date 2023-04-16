# Chat GPT Assistant (Kotlin Multiplatform App)

This is a Kotlin multiplatform app that uses Jetpack Compose and Hilt to interact with the Chat GPT API.
With this app, you can send and receive messages using the Chat GPT API by providing your user API key.

## Requirements

- JDK 11 or higher
- Android Studio 4.2 or higher

## Getting Started

1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on your device or emulator.

## Features

- Send and receive messages using the Chat GPT API.
- Supports multiple platforms, including Android and iOS. (iOS is not yet supported)
- Built with Kotlin and Jetpack Compose.

## Obtaining an API Key

To use this app, you'll need to obtain an API key from OpenAI. Here's how you can get an API key:

1. Go to the [OpenAI website](https://openai.com/).
2. Sign up for an account or log in if you already have one.
3. Navigate to the [API Keys page](https://platform.openai.com/account/api-keys).
4. Click on the "Create new secret key" button to generate a new API key.
5. Copy the API key and keep it in a safe place.

## Usage

To use this app, you'll need to provide your user API key for the Chat GPT API. Once you have your API key, you can add it to the app by modifying the `local.properties` file in the project root directory:

```
chatGptApiKey=<your-api-key-here>
```

After adding your API key, you can build and run the app to start sending and receiving messages.

## Roadmap

Here are some features that are planned for future releases of this app:

 - [ ] Project tools like Detekt, gradle versioning tool and ...
 - [ ] Basic assistant feature to send and receive messages
 - [ ] Chat history: Add a chat history feature that allows users to view their previous conversations and search for specific messages.
 - [ ] Settings page: We plan to add a settings page where users can configure their chat preferences, such as the chatbot's name or language.
 - [ ] Assistant feature: Add ability to set this app as an android assistant app.
 - [ ] TTS feature: Add a text-to-speech (TTS) feature that can read out messages for users who prefer to listen rather than read.
 - [ ] STT feature: Add a speech-to-speech (STT) feature that can write in voice commands for users who prefer to speak rather than write.

## Contributing

If you'd like to contribute to this project, please feel free to submit a pull request or open an issue. We welcome any feedback, suggestions, or contributions!

## License

This project is licensed under the Apache License(version 2) - see the [LICENSE](LICENSE) file for details.