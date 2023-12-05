import Auth
import Common
import NitoKmp
import Schedule
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
                    case .login:
                        ComposeLoginScreen(
                            onLoginSuccess: {
                                stateMachine.dispatch(intent: .routing(.top))
                            }
                        )
                        .navigationTitle("ログイン")
                        .navigationBarBackButtonHidden(true)
                        .ignoresSafeArea(.keyboard)
                    case .top:
                        ComposeTopScreen(
                            onRecentScheduleClicked: { scheduleId in
                                stateMachine.dispatch(intent: .routing(.scheduleDetail(scheduleId: scheduleId)))
                            },
                            onScheduleListButtonClick: {
                                stateMachine.dispatch(intent: .routing(.scheduleList))
                            },
                            onSettingsButtonClick: {
                                stateMachine.dispatch(intent: .routing(.settings))
                            }
                        )
                        .navigationBarBackButtonHidden(true)
                    case .scheduleList:
                        ComposeScheduleListScreen(
                            onScheduleItemClick: { scheduleId in
                                stateMachine.dispatch(intent: .routing(.scheduleDetail(scheduleId: scheduleId)))
                            }
                        )
                        .navigationTitle("スケジュール一覧")
                    case .scheduleDetail(let scheduleId):
                        ComposeScheduleDetailScreen(scheduleId: scheduleId)
                            .navigationTitle("スケジュール詳細")
                    case .settings:
                        ComposeSettingsScreen()
                            .navigationTitle("設定")
                    }
                }
        }
        .onAppear {
            Task { await stateMachine.load() }
        }
    }
}
