import Dependencies
import NitoCombined

public struct DateTimeFormatterProvider {
    private static var dateTimeFormatter: DateFormatter {
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .medium
        dateFormatter.timeStyle = .short
        dateFormatter.locale = Locale(identifier: "ja_JP")
        return dateFormatter
    }

    public let dateTimeFormatter: DateFormatter
}

extension DateTimeFormatterProvider: DependencyKey {
    static public var liveValue: DateTimeFormatterProvider = DateTimeFormatterProvider(
        dateTimeFormatter: dateTimeFormatter
    )
}

extension DependencyValues {
    public var dateTimeFormatter: DateTimeFormatterProvider {
        get { self[DateTimeFormatterProvider.self] }
        set { self[DateTimeFormatterProvider.self] = newValue }
    }
}
