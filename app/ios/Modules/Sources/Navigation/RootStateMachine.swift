import Common
import Foundation
import SwiftUI

struct RootViewUIState: UIState {
    var paths: NavigationPath = .init()
}

enum RootViewIntent {
    case routing(Routing)
}

enum Routing {
    case top
}

@MainActor
class RootStateMachine: ObservableObject {
    @Published var state: RootViewUIState = .init()

    func dispatch(intent: RootViewIntent) {
        switch intent {
        case .routing(let routing):
            state.paths.append(routing)
        }
    }
}
