import Common
import Dependencies
import NitoCombined
import SwiftUI

enum TopRouting: Hashable {
    case scheduleList
    case settings
}

public struct TopView<ScheduleListView: View, SettingsView: View>: View {
    @StateObject var stateMachine: TopStateMachine = .init()

    private let scheduleListViewProvider: ViewProvider<Void, ScheduleListView>
    private let settingsViewProvider: ViewProvider<Void, SettingsView>

    public init(
        scheduleListViewProvider: @escaping ViewProvider<Void, ScheduleListView>,
        settingsViewProvider: @escaping ViewProvider<Void, SettingsView>
    ) {
        self.scheduleListViewProvider = scheduleListViewProvider
        self.settingsViewProvider = settingsViewProvider
    }

    public var body: some View {
        NavigationStack {
            VStack {
                Image(systemName: "globe")
                    .imageScale(.large)
                    .foregroundStyle(.tint)
                Text(Greeting().greet())

                Group {
                    switch stateMachine.state.recentScheduleUIState {
                    case .initial, .loading:
                        ProgressView()
                            .task {
                                await stateMachine.load()
                            }
                    case .loaded(let recentSchedule):
                        Text(
                            recentSchedule.formatter.string(
                                from: recentSchedule.data.scheduledAt.toDate()
                            )
                        )
                    case .failed(let error):
                        Text(error.localizedDescription)
                    }
                }
                .padding(.vertical, 16)

                NavigationLink(value: TopRouting.scheduleList) {
                    Text("スケジュール一覧を見る")
                }
            }
            .padding()
            .navigationTitle("トップ")
            .toolbar {
                NavigationLink(value: TopRouting.settings) {
                    Image(systemName: "gear")
                        .renderingMode(.template)
                        .imageScale(.large)
                        .foregroundStyle(.tint)
                }
            }
            .navigationDestination(for: TopRouting.self) { routing in
                switch routing {
                case .scheduleList:
                    scheduleListViewProvider(())
                case .settings:
                    settingsViewProvider(())
                }
            }
        }
    }
}

#Preview {
    TopView(
        scheduleListViewProvider: { _ in EmptyView() },
        settingsViewProvider: { _ in EmptyView() }
    )
}
