import Auth
import Common
import Settings
import SwiftUI
import Top

public struct RootView: View {
    @StateObject var stateMachine: RootStateMachine = .init()

    public init() {}

    public var body: some View {
        NavigationStack(path: $stateMachine.path) {
            ProgressView()
                .navigationDestination(for: Routing.self) { routing in
                    switch routing {
                    case .signIn:
                        SignInView(
                            onSignInSuccess: {
                                stateMachine.dispatch(intent: .routing(.top))
                            }
                        )
                    case .top:
                        TopView(
                            onScheduleListButtonClick: {
                                stateMachine.dispatch(intent: .routing(.scheduleList))
                            },
                            onSettingsButtonClick: {
                                stateMachine.dispatch(intent: .routing(.settings))
                            }
                        )
                    case .scheduleList:
                        EmptyView()
                    case .settings:
                        SettingsView()
                    }
                }
        }
        .onAppear {
            Task { await stateMachine.load() }
        }
    }
}
