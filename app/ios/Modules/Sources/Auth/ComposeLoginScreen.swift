import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeLoginScreen: UIViewControllerRepresentable {
    private let onLoginSuccess: () -> Void

    public init(
        onLoginSuccess: @escaping () -> Void
    ) {
        self.onLoginSuccess = onLoginSuccess
    }

    public func makeUIViewController(context: Context) -> UIViewController {
        return LoginScreen_iosKt.LoginRouteViewController(
            viewModel: LoginScreenStateMachine(
                observeAuthStatusUseCase: Container.shared.get(type: ObserveAuthStatusUseCase.self),
                login: Container.shared.get(type: LoginUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self)
            ),
            onLoggedIn: onLoginSuccess,
            onRegisterClick: {}
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
