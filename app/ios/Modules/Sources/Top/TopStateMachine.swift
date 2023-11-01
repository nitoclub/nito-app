import Common
import Dependencies
import Foundation
import KmpContainer
import NitoCombined

struct TopViewUIState: UIState {
    var recentSchedule: LoadingState<Schedule> = .initial
}

@MainActor
final class TopStateMachine: ObservableObject {
    @Dependency(\.getRecentScheduleUseCase) var getRecentScheduleUseCase

    @Published var state: TopViewUIState = .init()

    private var cachedRecentSchedule: Schedule? {
        didSet {
            applyRecentScheduleToState()
        }
    }
    private var loadTask: Task<Void, Error>?

    deinit {
        loadTask?.cancel()
    }

    func load() async {
        state.recentSchedule = .loading

        loadTask = Task {
            do {
                for try await case let recentSchedule as FetchSingleContentResultSuccess<Schedule> in getRecentScheduleUseCase.execute() {
                    cachedRecentSchedule = recentSchedule.data
                }
            } catch let error {
                state.recentSchedule = .failed(error)
            }
        }
    }

    private func applyRecentScheduleToState() {
        guard let cachedRecentSchedule = cachedRecentSchedule else {
            return
        }
        state.recentSchedule = .loaded(
            cachedRecentSchedule
        )
    }
}
