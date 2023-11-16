import Common
import Dependencies
import Foundation
import KmpContainer
import NitoCombined

struct TopViewUIState: UIState {
    var recentScheduleUIState: LoadingState<RecentScheduleUIState> = .initial
}

struct RecentScheduleUIState: UIState {
    var data: ParticipantSchedule
    var formatter: DateFormatter
}

@MainActor
final class TopStateMachine: ObservableObject {
    @Dependency(\.dateTimeFormatter) var dateTimeFormatterProvider
    @Dependency(\.getRecentScheduleUseCase) var getRecentScheduleUseCase

    @Published var state: TopViewUIState = .init()

    private var cachedRecentSchedule: FetchSingleContentResult? {
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

        loadTask = Task.detached { @MainActor in
            do {
                for try await recentSchedule in self.getRecentScheduleUseCase.execute() {
                    print("recentSchedule: \(recentSchedule)")
                    self.cachedRecentSchedule = recentSchedule
                }
            } catch let error {
                self.state.recentScheduleUIState = .failed(error)
            }
        }
    }

    private func applyRecentScheduleToState() {
        guard let cachedRecentSchedule = cachedRecentSchedule else {
            return
        }

        switch cachedRecentSchedule {
        case is FetchSingleContentResultLoading, is FetchSingleContentResultNoContent:
            state.recentScheduleUIState = .loading
        case let result as FetchSingleContentResultSuccess<ParticipantSchedule>:
            state.recentScheduleUIState = .loaded(
                RecentScheduleUIState(
                    data: result.data!,
                    formatter: dateTimeFormatterProvider.dateTimeFormatter
                )
            )
        case let result as FetchSingleContentResultFailure:
            state.recentScheduleUIState = .failed(

                iOSNitoError.error(result.error)
            )
        default: break
        }
    }
}

enum iOSNitoError: Error {
    case error(NitoError?)
}
