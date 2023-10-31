import NitoCombined
import SwiftUI

public struct SettingsView: View {
    @StateObject var viewModel: SettingsViewModel = .init()

    public init() {}

    public var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Greeting().greet())
        }
        .padding()
    }
}

#Preview {
    SettingsView()
}
