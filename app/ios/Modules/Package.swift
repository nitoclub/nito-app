// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Modules",
    defaultLocalization: "ja",
    platforms: [
        .iOS(.v16),
        .macOS(.v12),
    ],
    products: [
        .library(
            name: "About",
            targets: ["About"]
        ),
        .library(
            name: "Auth",
            targets: ["Auth"]
        ),
        .library(
            name: "Navigation",
            targets: ["Navigation"]
        ),
        .library(
            name: "Schedule",
            targets: ["Schedule"]
        ),
        .library(
            name: "Settings",
            targets: ["Settings"]
        ),
        .library(
            name: "Top",
            targets: ["Top"]
        ),
    ],
    dependencies: [
        .package(url: "https://github.com/pointfreeco/swift-dependencies", from: "1.0.0"),
    ],
    targets: [
        .target(
            name: "About",
            dependencies: [
                "KmpContainer",
                "NitoCombined",
            ]
        ),
        .target(
            name: "Auth",
            dependencies: [
                "KmpContainer",
                "NitoCombined",
            ]
        ),
        .target(
            name: "Navigation",
            dependencies: [
                "About",
                "Auth",
                "Schedule",
                "Settings",
                "Top",
                "KmpContainer",
                "NitoCombined",
            ]
        ),
        .target(
            name: "Schedule",
            dependencies: [
                "KmpContainer",
                "NitoCombined",
            ]
        ),
        .target(
            name: "Settings",
            dependencies: [
                "KmpContainer",
                "NitoCombined",
            ]
        ),
        .target(
            name: "Top",
            dependencies: [
                "KmpContainer",
                "NitoCombined",
            ]
        ),

        .testTarget(
            name: "ModulesTests",
            dependencies: ["About"]
        ),

        .target(
            name: "KmpContainer",
            dependencies: [
                "NitoCombined",
                .product(name: "Dependencies", package: "swift-dependencies"),
            ]
        ),
        .binaryTarget(
            name: "NitoCombined",
            path: "../../ios-combined/build/XCFrameworks/release/NitoCombined.xcframework"
        )
    ]
)
