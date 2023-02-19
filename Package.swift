// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "DarajaMultiplatform",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "DarajaMultiplatform",
            targets: ["DarajaMultiplatform"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "DarajaMultiplatform",
            url: "https://github.com/VictorKabata/DarajaMultiplatform/DarajaMultiplatform.zip",
            checksum: "a9539d7da02a1a379a79370d19b175cf41c6edc2e21a0fe1254274c8b00b05cf"
        ),
    ]
)
