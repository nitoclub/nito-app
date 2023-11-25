import Common
import Dependencies
import NitoKmp
import SwiftUI

public struct TopView: View {
    @StateObject var stateMachine: TopStateMachine = .init()

    private let onScheduleListButtonClick: () -> Void
    private let onSettingsButtonClick: () -> Void

    public init(
        onScheduleListButtonClick: @escaping () -> Void,
        onSettingsButtonClick: @escaping () -> Void
    ) {
        self.onScheduleListButtonClick = onScheduleListButtonClick
        self.onSettingsButtonClick = onSettingsButtonClick
    }

    public var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)

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

            Button {
                onScheduleListButtonClick()
            } label: {
                Text("スケジュール一覧を見る")
            }.buttonStyle(.borderless)
        }
        .padding()
        .navigationTitle("トップ")
        .navigationBarBackButtonHidden(true)
        .toolbar {
            Button {
                onSettingsButtonClick()
            } label: {
                Image(systemName: "gearshape")
                    .renderingMode(.template)
                    .imageScale(.large)
                    .foregroundStyle(.tint)
            }.buttonStyle(.borderless)
        }
    }
}

#Preview {
    TopView(
        onScheduleListButtonClick: {},
        onSettingsButtonClick: {}
    )
}
