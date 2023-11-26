import KmpContainer
import NitoKmp
import SwiftUI
import UIKit

public struct ComposeScheduleListScreen: UIViewControllerRepresentable {
    public init() {}

    public func makeUIViewController(context: Context) -> UIViewController {
        return ScheduleListScreen_iosKt.ScheduleListRouteViewController(
            viewModel: ScheduleListViewModel(
                getParticipantScheduleListUseCase: Container.shared.get(
                    type: GetParticipantScheduleListUseCase.self),
                userMessageStateHolder: Container.shared.get(type: UserMessageStateHolder.self),
                dateTimeFormatter: Container.shared.get(type: CommonNitoDateFormatter.self)
            )
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
