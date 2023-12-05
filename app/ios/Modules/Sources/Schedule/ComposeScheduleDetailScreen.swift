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
                id: scheduleId,
                fetchParticipantScheduleById: Container.shared.get(
                    type: FetchParticipantScheduleByIdUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self),
                dateTimeFormatter: Container.shared.get(type: CommonNitoDateFormatter.self)
            )
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
