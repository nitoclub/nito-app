import Dependencies
import NitoCombined

public struct ObserveAuthStatusUseCaseProvider {
    private static var observeAuthStatusUseCase: ObserveAuthStatusUseCase {
        Container.shared.get(type: ObserveAuthStatusUseCase.self)
    }

    public let execute: () -> AsyncThrowingStream<FetchSingleContentResult, Error>
}

extension ObserveAuthStatusUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: ObserveAuthStatusUseCaseProvider = ObserveAuthStatusUseCaseProvider(
        execute: {
            observeAuthStatusUseCase.invoke().stream()
        }
    )
}

public extension DependencyValues {
    var observeAuthStatusUseCase: ObserveAuthStatusUseCaseProvider {
        get { self[ObserveAuthStatusUseCaseProvider.self] }
        set { self[ObserveAuthStatusUseCaseProvider.self] = newValue }
    }
}
