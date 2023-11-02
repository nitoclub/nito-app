import Dependencies
import NitoCombined

public struct SignOutUseCaseProvider {
    private static var signOutUseCase: SignOutUseCase {
        Container.shared.get(type: SignOutUseCase.self)
    }

    public let execute: () async throws -> Void
}

extension SignOutUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: SignOutUseCaseProvider = SignOutUseCaseProvider(
        execute: { @MainActor in
            try await signOutUseCase.invoke()
        }
    )
}

extension DependencyValues {
    public var signOutUseCase: SignOutUseCaseProvider {
        get { self[SignOutUseCaseProvider.self] }
        set { self[SignOutUseCaseProvider.self] = newValue }
    }
}
