import NitoKmp

public struct Container {
    public static let shared: Container = .init()

    private let entryPoint: KmpEntryPoint
    private init() {
        entryPoint = .init()

        entryPoint.doInit(
            buildConfig: AppBuildConfig()
        )
    }

    public func get<TypeProtocol, ReturnType>(type: TypeProtocol) -> ReturnType
    where TypeProtocol: Protocol {
        guard let object = entryPoint.get(objCProtocol: type) as? ReturnType else {
            fatalError("Not found instance for \(type)")
        }
        return object
    }
}

private class AppBuildConfig: BuildConfig {
    var debugBuild: Bool
    var versionName: String

    init() {
        #if DEBUG
            self.debugBuild = true
        #else
            self.debugBuild = false
        #endif
        self.versionName =
            Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? ""
    }
}
