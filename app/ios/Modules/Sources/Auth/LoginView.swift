import Common
import Dependencies
import NitoCombined
import SwiftUI

enum LoginRouting: Hashable {
    case top
}

public struct LoginView: View {
    @StateObject var stateMachine: LoginStateMachine = .init()

    private let onLoginSuccess: () -> Void

    public init(
        onLoginSuccess: @escaping () -> Void
    ) {
        self.onLoginSuccess = onLoginSuccess
    }

    public var body: some View {
        VStack {
            TextField("メールアドレス", text: $stateMachine.state.email)
                .textFieldStyle(.roundedBorder)
                .textContentType(.emailAddress)
                .keyboardType(.emailAddress)
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .padding(.vertical, 8)

            SecureField("パスワード", text: $stateMachine.state.password)
                .textFieldStyle(.roundedBorder)
                .textContentType(.password)
                .padding(.vertical, 8)

            Button {
                stateMachine.login()
            } label: {
                Text("ログイン")
            }
        }
        .padding()
        .navigationTitle("ログイン")
        .navigationBarBackButtonHidden(true)
        .onAppear {
            Task { await stateMachine.load() }
        }
        .onReceive(
            stateMachine.$event,
            perform: { event in
                switch event {
                case .some(.onLoginSuccess):
                    onLoginSuccess()
                default: break
                }
            })
    }
}

#Preview {
    LoginView(
        onLoginSuccess: {}
    )
}
