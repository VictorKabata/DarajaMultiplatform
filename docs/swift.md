# __Swift SDK__

## Getting Started

To get started with Daraja Multiplatform SDK, you will need
to [create a Daraja API account](https://developer.safaricom.co.ke/) on the Daraja API portal
and [set up a new test application](https://developer.safaricom.co.ke/MyApps).

Once you have access to the created test app, retrieve the ___Consumer Key___, ___Consumer Secret___ and ___Passkey___.

## Installation

- In Xcode add the DarajaMultiplatform package, navigate to File -> Add package dependecies. Enter the package GitHub
  url below, select the package and choose the target(s) to add it to and click "Add Package".

```curl
https://github.com/VictorKabata/DarajaSwiftPackage.git
```

> To protect your sensitive API keys, it's recommended to store your Consumer Key, Consumer Secret, and Passkey in your
> project's environment secrets (outside of version control). This ensures they are not accidentally exposed in public
> repositories.

- Instantiate a `Daraja` object, providing the necessary environment variables. This Daraja instance serves as the entry
  point for various M-Pesa operations. Core functionalities include obtaining an access token required for subsequent
  API calls and initiating M-Pesa Express STK push requests.

```swift
let daraja = Daraja(
    consumerKey: "your_consumer_key",
    consumerSecret: "your_consumer_secret",
    passKey: "your_pass_key",
    environment: DarajaEnvironment.sandboxEnvironment)
```

- The environment can be either _DarajaEnvironment.sandboxEnvironment_ or _DarajaEnvironment.productionEnvironment_.

> Daraja Multiplatform includes built-in network logging, which is active by default in sandbox mode but disabled in
> production mode. To inspect network requests and responses, view logs in Android Studio's Logcat under the __Daraja
Multiplatform__ tag.

## Usage

- Start by importing the package to your Swift code:

```swift
import DarajaMultiplatform
```

### Request Access Token

To request an access token from Daraja API, invoke the `authorization` function:

```swift
daraja.authorization()
.onSuccess(action: { accessToken in
    print(accessToken)
})
.onFailure(action: { error in
    print(error)
})

```

### Initiate M-Pesa Express STK Request

- To initiate M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpress` function:

```swift
daraja.mpesaExpress(
    businessShortCode: "174379",
    amount: 1,
    phoneNumber: "07xxxxxxxx", // or +2547xxxxxxxx or 2547xxxxxxxx
    transactionType: .customerBuyGoodsOnline,
    transactionDesc: "your_callback_url",
    callbackUrl: "your_callback_url",
    accountReference: "CompanyName"
).onSuccess(action: { paymentResponse in
    print(paymentResponse)
})
.onFailure(action: { error in
    print(error)
})

```

### Query M-Pesa Express STK Status

- To check the status of M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpressQuery` function:

```swift
daraja.mpesaExpressQuery(
    businessShortCode: "174379",
    timestamp: "20160216165627",
    checkoutRequestID: "ws_CO_260520211133524545"
).onSuccess(action: { transaction in
    print(transaction)
})
.onFailure(action: { error in
    print(error)
})
```

### Generate Dynamic QR Code

- To generate a dynamic QR code that can be used to trigger payment, invoke the `generateQR` function:

```swift
daraja.generateDynamicQr(
    merchantName: "Shop 1",
    referenceNumber: UUID().uuidString,
    amount: 10,
    transactionCode: DarajaTransactionCode.sm,
    cpi: "373132",
    size: 300
).onSuccess(action: { qrCode in
    print(qrCode)
})
.onFailure(action: { error in
    print(error)
})
```