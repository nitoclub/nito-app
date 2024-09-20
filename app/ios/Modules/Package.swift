// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Modules",
    defaultLocalization: "ja",
    platforms: [
        .iOS(.v17),
        .macOS(.v14),
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
        .package(url: "https://github.com/pointfreeco/swift-dependencies", from: "1.4.1")
    ],
    targets: [
        .target(
            name: "Common",
            dependencies: [
                "NitoKmp",
                .product(name: "Dependencies", package: "swift-dependencies"),
            ]
        ),
        .target(
            name: "Model",
            dependencies: [
                "Common",
                "NitoKmp",
            ]
        ),
        .target(
            name: "About",
            dependencies: [
                "KmpContainer",
                "NitoKmp",
            ]
        ),
        .target(
            name: "Auth",
            dependencies: [
                "Common",
                "Model",
                "KmpContainer",
                "NitoKmp",
            ]
        ),
        .target(
            name: "Navigation",
            dependencies: [
                "Common",
                "About",
                "Auth",
                "Schedule",
                "Settings",
                "Top",
                "KmpContainer",
                "NitoKmp",
            ]
        ),
        .target(
            name: "Schedule",
            dependencies: [
                "Common",
                "KmpContainer",
                "NitoKmp",
            ]
        ),
        .target(
            name: "Settings",
            dependencies: [
                "Common",
                "KmpContainer",
                "NitoKmp",
            ]
        ),
        .target(
            name: "Top",
            dependencies: [
                "Common",
                "KmpContainer",
                "NitoKmp",
            ]
        ),

        .testTarget(
            name: "ModulesTests",
            dependencies: ["About"]
        ),

        .target(
            name: "KmpContainer",
            dependencies: [
                "NitoKmp",
                .product(name: "Dependencies", package: "swift-dependencies"),
            ]
        ),
        .binaryTarget(
            name: "NitoKmp",
            path: "../../ios-combined/build/XCFrameworks/release/NitoKmp.xcframework"
        ),
    ]
)
