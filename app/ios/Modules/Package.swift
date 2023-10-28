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
    targets: [
        .target(
            name: "About",
            dependencies: [
                "NitoCombined",
            ]
        ),
        .target(
            name: "Auth",
            dependencies: [
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
                "NitoCombined",
            ]
        ),
        .target(
            name: "Schedule",
            dependencies: [
                "NitoCombined",
            ]
        ),
        .target(
            name: "Settings",
            dependencies: [
                "NitoCombined",
            ]
        ),
        .target(
            name: "Top",
            dependencies: [
                "NitoCombined",
            ]
        ),

        .testTarget(
            name: "ModulesTests",
            dependencies: ["About"]
        ),
        
        .binaryTarget(
            name: "NitoCombined",
            path: "../../ios-combined/build/XCFrameworks/release/NitoCombined.xcframework"
        )
    ]
)
