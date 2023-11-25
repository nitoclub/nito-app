import Common
import Dependencies
import Foundation
import NitoKmp
import SwiftUI

struct RootViewUIState: UIState {
    var path: NavigationPath = .init()
    var authStatus: LoadingState<AuthStatusAuthenticated> = .initial
}

enum RootViewIntent {
    case routing(Routing)
}

enum Routing: Hashable {
    case login
    case top
    case scheduleList
    case settings
}

@MainActor
class RootStateMachine: ObservableObject {
    @Published var state: RootViewUIState = .init()
    @Published var path: NavigationPath = .init()
    @Dependency(\.observeAuthStatusUseCase) var observeAuthStatusUseCase

    private var cachedAuthStatus: AuthStatus? {
        didSet {
            applyAuthStatusToState()
        }
    }
    private var loadTask: Task<Void, Error>?

    deinit {
        loadTask?.cancel()
    }

    func dispatch(intent: RootViewIntent) {
        switch intent {
        case .routing(let routing):
            path.append(routing)
        }
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

    private func applyAuthStatusToState() {
        switch cachedAuthStatus {
        case .some(is AuthStatusNotAuthenticated):
            path.removeLast(path.count)
            path.append(Routing.login)
        case .some(is AuthStatusAuthenticated):
            path.removeLast(path.count)
            path.append(Routing.top)
        default: break
        }
    }
}
