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
            checksum: "f2c6d55f42808617f5be53fa9f559fac099597da980ba262c980812139a61534"
        ),
    ]
)
