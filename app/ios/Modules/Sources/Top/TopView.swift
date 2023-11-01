import Common
import SwiftUI
import NitoCombined

enum TopRouting: Hashable {
    case scheduleList
    case settings
}

public struct TopView<ScheduleListView: View, SettingsView: View>: View {
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

                NavigationLink(value: TopRouting.scheduleList) {
                    Text("スケジュール一覧を見る")
                }
                NavigationLink(value: TopRouting.settings) {
                        HStack(spacing: 16) {
                            Image(systemName: "wrench")
                                .renderingMode(.template)
                                .imageScale(.large)
                                .foregroundStyle(.tint)
                            Text("設定")
                        }
                        .padding(.horizontal, 12)
                        .padding(.vertical, 24)
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
            }
            .padding()
            .navigationTitle("トップ")
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
        scheduleListViewProvider: {_ in EmptyView()},
        settingsViewProvider: {_ in EmptyView()}
    )
}
