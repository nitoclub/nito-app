import Dependencies
import NitoCombined

public struct LoginUseCaseProvider {
    private static var loginUseCase: LoginUseCase {
        Container.shared.get(type: LoginUseCase.self)
    }

    public let execute: (String, String) async throws -> Void
}

extension LoginUseCaseProvider: DependencyKey {
    @MainActor
    static public var liveValue: LoginUseCaseProvider = LoginUseCaseProvider(
        execute: { @MainActor email, password in
            try await loginUseCase.invoke(email: email, password: password)
        }
    )
}

extension DependencyValues {
    public var loginUseCase: LoginUseCaseProvider {
        get { self[LoginUseCaseProvider.self] }
        set { self[LoginUseCaseProvider.self] = newValue }
    }
}
