import NitoCombined
import SwiftUI

public struct SettingsScreen: View {
    @StateObject var stateMachine: SettingsStateMachine = .init()

    public init() {}

    public var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Greeting().greet())

            Button {
                stateMachine.signOut()
            } label: {
                Text("サインアウト")
            }
        }
        .padding()
        .navigationTitle("設定")
    }
}

#Preview {
    SettingsScreen()
}
