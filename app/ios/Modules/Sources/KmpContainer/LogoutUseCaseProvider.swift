import Dependencies
import NitoCombined

public struct LogoutUseCaseProvider {
    private static var logoutUseCase: LogoutUseCase {
        Container.shared.get(type: LogoutUseCase.self)
    }

    public let execute: () async throws -> Void
}

extension LogoutUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: LogoutUseCaseProvider = LogoutUseCaseProvider(
        execute: { @MainActor in
            try await logoutUseCase.invoke()
        }
    )
}

extension DependencyValues {
    public var logoutUseCase: LogoutUseCaseProvider {
        get { self[LogoutUseCaseProvider.self] }
        set { self[LogoutUseCaseProvider.self] = newValue }
    }
}
