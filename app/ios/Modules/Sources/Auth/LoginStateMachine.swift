import Common
import Dependencies
import Foundation
import KmpContainer
import NitoKmp
import SwiftUI

struct LoginViewUIState: UIState {
    var email: String = ""
    var password: String = ""
    var authStatus: LoadingState<AuthStatusAuthenticated> = .initial
}

enum LoginScreenEvent {
    case onLoginSuccess
}

@MainActor
final class LoginStateMachine: ObservableObject {
    @Dependency(\.loginUseCase) var loginUseCase
    @Dependency(\.authStatusStreamUseCase) var authStatusStream

    @Published var state: LoginViewUIState = .init()
    @Published var event: LoginScreenEvent? = nil
    @Published var routing: [LoginRouting] = []

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
                for try await authStatus in self.authStatusStream.execute() {
                    if case let status as FetchSingleResultSuccess<AuthStatus> = authStatus {
                        self.cachedAuthStatus = status.data
                    }
                }
            } catch let error {
                self.state.authStatus = .failed(error)
            }
        }
    }

    func login() {
        Task {
            try await loginUseCase.execute(state.email, state.password)
        }
    }

    private func applyAuthStatusToState() {
        guard case let cachedAuthStatus as AuthStatusAuthenticated = cachedAuthStatus else {
            return
        }
        event = .onLoginSuccess
        state.authStatus = .loaded(
            cachedAuthStatus
        )
    }
}
