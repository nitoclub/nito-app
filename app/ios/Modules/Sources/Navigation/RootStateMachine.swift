import Common
import Foundation

struct RootViewUIState: UIState {
}

@MainActor
class RootStateMachine: ObservableObject {
    @Published var state: RootViewUIState = .init()
}
