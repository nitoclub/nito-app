import Common
import Dependencies
import Foundation
import KmpContainer
import NitoKmp

@MainActor
final class SettingsStateMachine: ObservableObject {
    @Dependency(\.logoutUseCase) var logoutUseCase

    func logout() {
        Task {
            try await logoutUseCase.execute()
        }
    }
}
