import SwiftUI
import Settings
import Top

public struct RootView: View {
    @StateObject var stateMachine: RootStateMachine = .init()

    public init() {}

    public var body: some View {
        TopView(
            scheduleListViewProvider: { _ in
                EmptyView()
            },
            settingsViewProvider: { _ in
                SettingsView()
            }
        )
    }
}
