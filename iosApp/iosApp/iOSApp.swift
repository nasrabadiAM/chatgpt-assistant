import SwiftUI

@main
struct iOSApp: App {

	init() {
		LoggingKt.initializeLogging()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}