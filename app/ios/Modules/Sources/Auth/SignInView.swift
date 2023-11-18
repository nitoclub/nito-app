import Common
import Dependencies
import NitoCombined
import SwiftUI

enum SignInRouting: Hashable {
    case top
}

public struct SignInView: View {
    @StateObject var stateMachine: SignInStateMachine = .init()

    private let onSignInSuccess: () -> Void

    public init(
        onSignInSuccess: @escaping () -> Void
    ) {
        self.onSignInSuccess = onSignInSuccess
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
                stateMachine.signIn()
            } label: {
                Text("サインイン")
            }
        }
        .padding()
        .navigationTitle("サインイン")
        .navigationBarBackButtonHidden(true)
        .onAppear {
            Task { await stateMachine.load() }
        }
        .onReceive(stateMachine.$event, perform: { event in
            switch event {
            case .some(.onSignInSuccess):
                onSignInSuccess()
            default: break
            }
        })
    }
}

#Preview {
    SignInView(
        onSignInSuccess: { }
    )
}
