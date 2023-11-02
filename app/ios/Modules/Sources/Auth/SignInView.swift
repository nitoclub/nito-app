import Common
import Dependencies
import NitoCombined
import SwiftUI

enum SignInRouting: Hashable {
    case top
}

public struct SignInView<TopView: View>: View {
    @StateObject var stateMachine: SignInStateMachine = .init()

    private let topViewProvider: ViewProvider<Void, TopView>

    public init(
        topViewProvider: @escaping ViewProvider<Void, TopView>
    ) {
        self.topViewProvider = topViewProvider
    }

    public var body: some View {
        NavigationStack(path: $stateMachine.routing) {
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
            .navigationDestination(for: SignInRouting.self) { routing in
                switch routing {
                case .top:
                    topViewProvider(())
                }
            }
            .onAppear {
                Task { await stateMachine.load() }
            }
        }
    }
}

#Preview {
    SignInView(
        topViewProvider: { _ in EmptyView() }
    )
}
