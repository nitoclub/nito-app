import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeSettingsScreen: UIViewControllerRepresentable {
    @StateObject var stateMachine: SettingsStateMachine = .init()

    public init() {}

    public func makeUIViewController(context: Context) -> UIViewController {
        return SettingsScreen_iosKt.SettingsScreenUIViewController(
            stateMachine: SettingsScreenStateMachine(
                authStatusStream: Container.shared.get(type: AuthStatusStreamUseCase.self),
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
