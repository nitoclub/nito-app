import NitoKmp
import SwiftUI

public struct SettingsScreen: View {
    @StateObject var stateMachine: SettingsStateMachine = .init()

    public init() {}

    public var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)

            Button {
                stateMachine.logout()
            } label: {
                Text("ログアウト")
            }
        }
        .padding()
        .navigationTitle("設定")
    }
}

#Preview {
    SettingsScreen()
}
