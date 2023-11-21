import Common
import Dependencies
import Foundation
import KmpContainer
import NitoCombined

@MainActor
final class SettingsStateMachine: ObservableObject {
    @Dependency(\.signOutUseCase) var signOutUseCase

    func signOut() {
        Task {
            try await signOutUseCase.execute()
        }
    }
}
