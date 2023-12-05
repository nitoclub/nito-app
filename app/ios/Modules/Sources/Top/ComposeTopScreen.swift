import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeTopScreen: UIViewControllerRepresentable {
    private let onRecentScheduleClicked: (String) -> Void
    private let onScheduleListButtonClick: () -> Void
    private let onSettingsButtonClick: () -> Void

    public init(
        onRecentScheduleClicked: @escaping (String) -> Void,
        onScheduleListButtonClick: @escaping () -> Void,
        onSettingsButtonClick: @escaping () -> Void
    ) {
        self.onRecentScheduleClicked = onRecentScheduleClicked
        self.onScheduleListButtonClick = onScheduleListButtonClick
        self.onSettingsButtonClick = onSettingsButtonClick
    }

    public func makeUIViewController(context: Context) -> UIViewController {
        return TopScreen_iosKt.TopRouteViewController(
            stateMachine: TopScreenStateMachine(
                getRecentSchedule: Container.shared.get(type: GetRecentScheduleUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self),
                dateTimeFormatter: Container.shared.get(type: CommonNitoDateFormatter.self)
            ),
            onRecentScheduleClicked: onRecentScheduleClicked,
            onScheduleListClick: onScheduleListButtonClick,
            onSettingsClick: onSettingsButtonClick
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
