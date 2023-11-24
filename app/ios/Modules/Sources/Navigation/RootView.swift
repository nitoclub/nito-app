import Auth
import Common
import Schedule
import Settings
import SwiftUI
import Top
import NitoCombined

public struct RootView: View {
    @StateObject var stateMachine: RootStateMachine = .init()

    public init() {}

    public var body: some View {
        NavigationStack(path: $stateMachine.path) {
            ProgressView()
                .navigationDestination(for: Routing.self) { routing in
                    switch routing {
                    case .login:
                         ComposeLoginScreen(
                            onLoginSuccess: {
                                stateMachine.dispatch(intent: .routing(.top))
                            }
                        )
                         .navigationBarBackButtonHidden(true)
                         .ignoresSafeArea(.keyboard)
                    case .top:
                         ComposeTopScreen(
                            onScheduleListButtonClick: {
                                stateMachine.dispatch(intent: .routing(.scheduleList))
                            },
                            onSettingsButtonClick: {
                                stateMachine.dispatch(intent: .routing(.settings))
                            }
                        )
                         .navigationBarBackButtonHidden(true)
                    case .scheduleList:
                         ComposeScheduleListScreen()
                    case .settings:
                        ComposeSettingsScreen()
                    }
                }
        }
        .onAppear {
            Task { await stateMachine.load() }
        }
    }
}
