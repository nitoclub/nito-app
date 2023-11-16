import Common
import Dependencies
import Foundation
import KmpContainer
import NitoCombined
import SwiftUI

struct SignInViewUIState: UIState {
    var email: String = ""
    var password: String = ""
    var authStatus: LoadingState<AuthStatusAuthenticated> = .initial
}

@MainActor
final class SignInStateMachine: ObservableObject {
    @Dependency(\.signInUseCase) var signInUseCase
    @Dependency(\.observeAuthStatusUseCase) var observeAuthStatusUseCase

    @Published var state: SignInViewUIState = .init()
    @Published var routing: [SignInRouting] = []

    private var cachedAuthStatus: AuthStatus? {
        didSet {
            applyAuthStatusToState()
        }
    }
    private var loadTask: Task<Void, Error>?

    deinit {
        loadTask?.cancel()
    }

    func load() async {
        state.authStatus = .loading

        loadTask = Task.detached { @MainActor in
            do {
                for try await authStatus in self.observeAuthStatusUseCase.execute() {
                    if case let status as FetchSingleResultSuccess<AuthStatus> = authStatus {
                        self.cachedAuthStatus = status.data
                    }
                }
            } catch let error {
                self.state.authStatus = .failed(error)
            }
        }
    }

    func signIn() {
        Task {
            try await signInUseCase.execute(state.email, state.password)
        }
    }

    private func applyAuthStatusToState() {
        guard case let cachedAuthStatus as AuthStatusAuthenticated = cachedAuthStatus else {
            return
        }
        routing.append(.top)
        state.authStatus = .loaded(
            cachedAuthStatus
        )
    }
}
