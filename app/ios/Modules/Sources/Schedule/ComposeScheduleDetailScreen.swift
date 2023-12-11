import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeScheduleDetailScreen: UIViewControllerRepresentable {
    private let scheduleId: String

    public init(scheduleId: String) {
        self.scheduleId = scheduleId
    }

    public func makeUIViewController(context: Context) -> UIViewController {
        return ScheduleDetailScreen_iosKt.ScheduleDetailRouteViewController(
            id: scheduleId,
            stateMachine: ScheduleDetailStateMachine(
                scheduleId: scheduleId,
                scheduleStream: Container.shared.get(type: ScheduleStreamUseCase.self),
                scheduleParticipantsStream: Container.shared.get(type: ScheduleParticipantsStreamUseCase.self),
                myParticipantStatusStream: Container.shared.get(type: MyParticipantStatusStreamUseCase.self),
                participate: Container.shared.get(type: ParticipateUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self),
                dateTimeFormatter: Container.shared.get(type: CommonNitoDateFormatter.self)
            )
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
