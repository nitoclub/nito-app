import UIKit
import SwiftUI
import KmpContainer
import NitoCombined

public struct ComposeSettingsScreen: UIViewControllerRepresentable {
    @StateObject var stateMachine: SettingsStateMachine = .init()

    public init() {}

    public func makeUIViewController(context: Context) -> UIViewController {
        return SettingsScreen_iosKt.SettingsScreenUIViewController(
            stateMachine: SettingsScreenStateMachine(
                observeAuthStatus: Container.shared.get(type: ObserveAuthStatusUseCase.self),
                modifyPassword: Container.shared.get(type: ModifyPasswordUseCase.self),
                logout: Container.shared.get(type: LogoutUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self)
            ),
            onLoggedOut: {}
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
