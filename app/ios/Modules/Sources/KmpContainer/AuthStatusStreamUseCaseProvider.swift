import Dependencies
import NitoKmp

public struct AuthStatusStreamUseCaseProvider {
    private static var observeAuthStatusUseCase: AuthStatusStreamUseCase {
        Container.shared.get(type: AuthStatusStreamUseCase.self)
    }

    public let execute: () -> AsyncThrowingStream<AuthStatus, Error>
}

extension AuthStatusStreamUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: AuthStatusStreamUseCaseProvider =
        AuthStatusStreamUseCaseProvider(
            execute: {
                observeAuthStatusUseCase.invoke().stream()
            }
        )
}

extension DependencyValues {
    public var authStatusStreamUseCase: AuthStatusStreamUseCaseProvider {
        get { self[AuthStatusStreamUseCaseProvider.self] }
        set { self[AuthStatusStreamUseCaseProvider.self] = newValue }
    }
}
