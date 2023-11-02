import Common
import Dependencies
import Foundation
import KmpContainer
import NitoCombined

struct TopViewUIState: UIState {
    var recentScheduleUIState: LoadingState<RecentScheduleUIState> = .initial
}

struct RecentScheduleUIState: UIState {
    var data: Schedule
    var formatter: DateFormatter
}

@MainActor
final class TopStateMachine: ObservableObject {
    @Dependency(\.dateTimeFormatter) var dateTimeFormatterProvider
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
        state.recentScheduleUIState = .loading

        loadTask = Task {
            do {
                for try await case let recentSchedule as FetchSingleContentResultSuccess<Schedule>
                    in getRecentScheduleUseCase.execute()
                {
                    cachedRecentSchedule = recentSchedule.data
                }
            } catch let error {
                state.recentScheduleUIState = .failed(error)
            }
        }
    }

    private func applyRecentScheduleToState() {
        guard let cachedRecentSchedule = cachedRecentSchedule else {
            return
        }
        state.recentScheduleUIState = .loaded(
            RecentScheduleUIState(
                data: cachedRecentSchedule,
                formatter: dateTimeFormatterProvider.dateTimeFormatter
            )
        )
    }
}
