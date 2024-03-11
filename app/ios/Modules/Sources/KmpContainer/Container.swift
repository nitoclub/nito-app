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
    var applicationId: String
    var versionName: String
    var debugBuild: Bool
    var flavor: Flavor

    init() {
        self.applicationId =
            Bundle.main.object(forInfoDictionaryKey: "CFBundleIdentifier") as? String ?? ""
        self.versionName =
            Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? ""
        #if DEBUG
            self.debugBuild = true
        #else
            self.debugBuild = false
        #endif

        switch Bundle.main.object(forInfoDictionaryKey: "Flavor") as? String ?? "" {
        case "dev":
            self.flavor = Flavor.dev
        case "prod":
            self.flavor = Flavor.prod
        default:
            self.flavor = Flavor.dev
        }
    }
}


