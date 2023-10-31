import Dependencies
import Foundation
import KmpContainer
import NitoCombined

@MainActor
final class SettingsViewModel: ObservableObject {
    @Dependency(\.signOutUseCase) var signOutUseCase

    func signOut() async throws {
        try await signOutUseCase.execute()
    }
}
