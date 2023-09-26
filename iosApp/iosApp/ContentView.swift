import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {

	let lifecyle: LifecycleRegistry = LifecycleRegistryKt.LifecycleRegistry()

	init() {
		LifecycleRegistryExtKt.create(lifecyle)
	}

	func makeUIViewController(context: Context) -> UIViewController {
		let dispatcherProvider = DefaultDispatcherProvider()
		let component = HomeComponent(
			componentContext: DefaultComponentContext(lifecycle: lifecyle),
			chatRepository: ChatRepositoryKt.getInstance(),
			dispatcherProvider: dispatcherProvider
		)

		let controller = MainViewKt.MainView(component: component)
		LifecycleRegistryExtKt.resume(lifecyle)
		return controller
	}

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}