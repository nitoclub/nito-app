import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeScheduleListScreen: UIViewControllerRepresentable {
    public init() {}

    public func makeUIViewController(context: Context) -> UIViewController {
        return ScheduleListScreen_iosKt.ScheduleListRouteViewController(
            stateMachine: ScheduleListStateMachine(
                getParticipantScheduleList: Container.shared.get(
                    type: GetParticipantScheduleListUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self),
                dateFormatter: Container.shared.get(type: CommonNitoDateFormatter.self)
            )
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
