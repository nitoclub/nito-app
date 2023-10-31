import Dependencies
import NitoCombined

public struct SignInUseCaseProvider {
    private static var signInUseCase: SignInUseCase {
        Container.shared.get(type: SignInUseCase.self)
    }

    public let execute: (String, String) async throws -> Void
}

extension SignInUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: SignInUseCaseProvider = SignInUseCaseProvider(
        execute: { @MainActor email, password in
            try await signInUseCase.invoke(email: email, password: password)
        }
    )
}

public extension DependencyValues {
    var signInUseCase: SignInUseCaseProvider {
        get { self[SignInUseCaseProvider.self] }
        set { self[SignInUseCaseProvider.self] = newValue }
    }
}
