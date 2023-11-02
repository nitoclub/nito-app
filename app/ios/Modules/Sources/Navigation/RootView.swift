import Auth
import Settings
import SwiftUI
import Top

public struct RootView: View {
    @StateObject var stateMachine: RootStateMachine = .init()

    public init() {}

    public var body: some View {
        let topView = TopView(
            scheduleListViewProvider: { _ in
                EmptyView()
            },
            settingsViewProvider: { _ in
                SettingsView()
            }
        )
        topView

//        SignInView(
//            topViewProvider: { _ in topView }
//        )
    }
}
