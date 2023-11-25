import Dependencies
import NitoKmp

public struct ObserveAuthStatusUseCaseProvider {
    private static var observeAuthStatusUseCase: ObserveAuthStatusUseCase {
        Container.shared.get(type: ObserveAuthStatusUseCase.self)
    }

    public let execute: () -> AsyncThrowingStream<FetchSingleResult, Error>
}

extension ObserveAuthStatusUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: ObserveAuthStatusUseCaseProvider =
        ObserveAuthStatusUseCaseProvider(
            execute: {
                observeAuthStatusUseCase.invoke().stream()
            }
        )
}

extension DependencyValues {
    public var observeAuthStatusUseCase: ObserveAuthStatusUseCaseProvider {
        get { self[ObserveAuthStatusUseCaseProvider.self] }
        set { self[ObserveAuthStatusUseCaseProvider.self] = newValue }
    }
}
