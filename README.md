# **Daraja Multiplatform**

<p align="left">
<img  src="https://img.shields.io/badge/-ANDROID-3AA335?logo=kotlin&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-IOS-3AA335?logo=swift&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-DESKTOP-3AA335?logo=openjdk&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-WEB-CCCCCC?logo=javascript&logoColor=606060&style=for-the-badge">
</p>

## ⚠️Work in progress - Sandbox Mode⚠️

[Kotlin multiplatform](https://kotlinlang.org/docs/multiplatform.html) wrapper for Mpesa API
dubbed [_Daraja API_](https://developer.safaricom.co.ke/) (Daraja means bridge in Swahili) that
supports integration with your Android(Kotlin/Java), iOS(Swift) and JVM applications.

> M-PESA is a mobile money transfer service in Kenya that allows users to store and transfer money
> through their mobile phones.

## Table of Content

- [Prerequisite](#prerequisite)
- [Features](#features)
- [Usage](#usage)
    - [Android - Kotlin](#android---kotlin)
        - [Setting Up](#setting-up)
        - [Request Access Token](#request-access-token)
        - [Initiate M-Pesa Express STK Request](#initiate-m-pesa-express-stk-request)
        - [Dynamic QR](#generate-dynamic-qr-code)
        - [Query M-Pesa Transaction](#query-m-pesa-transaction)
        - [Customer To Business(C2B)](#customer-to-businessc2b)
        - [Account Balance](#account-balance)
    - [iOS - Swift](#ios---swift)
        - [Setting Up](#setting-up-1)
        - [Request Access Token](#request-access-token)
        - [Initiate M-Pesa Express STK Request](#initiate-m-pesa-express-stk-request-1)
        - [Dynamic QR](#generate-dynamic-qr-code)
        - [Query M-Pesa Transaction](#query-m-pesa-transaction-1)
        - [Customer To Business(C2B)](#customer-to-businessc2b)
        - [Account Balance](#account-balance)

## Prerequisite

To get started, you’ll need to create an account on the Daraja API portal to use the Daraja
API. [How to get started with Daraja API](https://developer.safaricom.co.ke/Documentation).

After successfully creating an account on the Daraja API portal and creating a new Daraja app,
you’ll need to add your **_consumer key_**, **_consumer secret_** and **_pass key_** obtained from
the Daraja API portal to your project.

## Features

The SDK offers the following functionalities from the Daraja API:

- [x] Authorization - Gives you a time bound access token to call allowed APIs.
- [x] M-Pesa Express - Merchant initiated online payments.
- [x] M-Pesa Express Query - Check the status of a Lipa Na M-Pesa Online Payment(M-Pesa Express).
- [x] Dynamic QR - Generates a dynamic M-PESA QR code.
- [x] Customer To Business (C2B)
- [ ] Business To Customer (B2C) - Transact between an M-Pesa short code to a phone number
  registered on M-Pesa.
- [x] Transaction Status - Check the status of a transaction.
- [x] Account Balance - Enquire the balance on an M-Pesa BuyGoods (Till Number)
- [ ] Reversal - Reverses an M-Pesa transaction.
- [ ] Tax Remittance - This API enables businesses to remit tax to Kenya Revenue Authority (KRA).
- [ ] Business Pay Bill - Pay bills directly from your business account to a pay bill number, or a
  paybill store.
- [ ] Business Buy Goods - Pay for goods and services directly from your business account to a till
  number or merchant store number.

## Usage

# Android - Kotlin <img src="assets/kotlin_logo.png" width="34" />

### Setting Up

- In your android application project-level gradle file add the following dependency:

<details open>
<summary>Kotlin</summary>

```Kotlin
  dependencies {
    implementation("io.github.victorkabata:daraja-multiplatform:0.9.3")
}
```

</details>

<details>
<summary>Groovy</summary>

```Groovy
  dependencies {
    implementation 'io.github.victorkabata:daraja-multiplatform:0.9.3'
}
```

 </details>

- Add your consumer secret, consumer key and pass key to your project. You can get them from
  the [Daraja API portal](https://developer.safaricom.co.ke/MyApps).

> You should not add your daraja API environment variables in a production application because it is
> a vulnerability to expose your environment secrets/variables in your version control system.
> Ideally, you should add them to your `local.properties` files as demonstrated in
> the [sample](https://github.com/VictorKabata/DarajaMultiplatform/tree/main/app-android) android
> application.

- Create an instance of the Daraja object by passing the daraja environment variables. The daraja
  object provides functions to request for an access token and initiate M-Pesa express STK request.

```Kotlin
val daraja: Daraja = Daraja.Builder()
    .setConsumerSecret("your_consumer_secret")
    .setConsumerKey("your_consumer_key")
    .setPassKey("your_pass_key")
    .isProduction() // Optional. Will default to sandbox_mode = true
    .build()
```

> Network logging is enabled by default when using Daraja Multiplatform. in sandbox/testing mode.
> The logs can be accessed from the logcat in Android Studio under the `Daraja Multiplatform` tag.

> Network logs are disabled in production mode.

### Request Access Token

- To request an access token from Daraja API, invoke the `authorization` function:

```Kotlin
val accessTokenResult: DarajaResult<DarajaToken> = daraja.authorization()

accessTokenResult
    .onSuccess { accessToken ->
        // Successfully fetched daraja access token
    }
    .onFailure { error ->
        // Failure fetching daraja access token
    }
```

### Initiate M-Pesa Express STK Request

- To initiate M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpress` function:

```kotlin
val darajaPaymentResponse: DarajaResult<MpesaExpressResponse> = daraja.mpesaExpress(
    businessShortCode = "174379",
    amount = 1,
    phoneNumber = "07xxxxxxxx",
    transactionDesc = "M-Pesa payment",
    callbackUrl = "your_callback_url",
    accountReference = "CompanyName"
)

darajaPaymentResponse.onSuccess { paymentResponse ->
    // Successfully requested M-Pesa STK request
}.onFailure { error ->
    // Failed to request M-Pesa STK
}
```

### Query M-Pesa Express STK

- To check the status of M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpressQuery` function:

```kotlin
val darajaMpesaExpressQuery:DarajaResult<QueryMpesaExpressResponse> = daraja.mpesaExpressQuery(
    businessShortCode = "174379",
    timeStamp = "20160216165627",
    checkOutRequestID = "ws_CO_260520211133524545"
)

darajaMpesaExpressQuery.onSuccess{ mpesaExpressQuery->
    // Successfully request M-Pesa STK request status
}.onFailure{ error->
    // Failed to request M-Pesa STK status
}
```

### Generate Dynamic QR Code

- Generate a dynamic m-pesa qr code

```Kotlin
val darajaQrCode: DarajaResult<DynamicQrResponse> = daraja.generateDynamicQr(
    merchantName = "TEST SUPERMARKET",
    referenceNumber = "Invoice Test",
    amount = 1,
    transactionCode = DarajaTransactionCode.BG,
    cpi = "373132",
    size = 400
)

darajaQrCode.onSuccess {
    // Successfully generated a QR code in base64 string format
}.onFailure {
    // Failed to generate a QR code
}
```

### Query M-Pesa Transaction

- To check the status of an M-pesa transaction, invoke the `transactionStatus` function:

```Kotlin
val darajaTransactionResponse: DarajaResult<DarajaTransactionResponse> = daraja.transactionStatus(
    businessShortCode = "174379",
    checkoutRequestID = "ws_CO_20122022180112029708374149"
)

darajaTransactionResponse
    .onSuccess { transactionResponse ->
        // Successfully fetched M-pesa transaction status
    }.onFailure { error ->
        // Failure fetching M-pesa transaction status
    }
```

### Customer To Business(C2B)

- To register the C2B validation and confirmation URL, invoke the `c2bRegistration` function:

```kotlin
val darajaC2BRegistrationResponse: DarajaResult<C2BResponse> = daraja.c2bRegistration(
    confirmationURL = "https://mydomain.com/confirmation",
    responseType = C2BResponseType.COMPLETED, // C2BResponseType.CANCELLED
    businessShortCode = 600981,
    validationURL = "https://mydomain.com/validation"
)

darajaC2BRegistrationResponse.onSuccess {
    // Successfully registered  confirmation and validation URL
}.onFailure {
    // Failure registering confirmation and validation URL
}

```

- To initiate a Customer to Business paybill, invoke the `c2b` function:

```kotlin
val c2bResponse: DarajaResult<C2BResponse> = daraja.c2b(
    amount = 1,
    billReferenceNumber = "600977",
    transactionType = DarajaTransactionType.CustomerBuyGoodsOnline, // DarajaTransactionType.CustomerPayBillOnline
    phoneNumber = "0708374149",
    businessShortCode = "600977" //Optional when using CustomerBuyGoodsOnline
)

c2bResponse.onSuccess {
    // Successfully invoked C2B request
}.onFailure {
    // Failure invoking C2B request
}
```

### Account Balance

Request the account balance of a short code. This can be used for both B2C, buy goods and pay bill
accounts.

```kotlin
    val accountBalanceResponse = daraja.accountBalance(
    initiator = "testapi",
    initiatorPassword = "Safaricom999!*!",
    partyA = 600987,
    identifierType = DarajaIdentifierType.TILL_NUMBER,
    queueTimeOutURL = "https://mydomain.com/AccountBalance/queue/",
    resultURL = "https://mydomain.com/AccountBalance/result/"
)

accountBalanceResponse.onSuccess {
    // Successfully request account balance
}.onFailure {
    // Failed to request account balance
}
```

# iOS - Swift <img src="assets/swift_logo.png" width="40" />

### Setting Up

- To add **_DarajaMultiplatform_** package to your Xcode Project, open your Xcode project, navigate
  to the File tab within the macOS bar and click **Select Packages** then **Add Package Dependency
  **. Enter the package name ie. DarajaMultiplatform or the URL package GitHub URL:

```curl
https://github.com/VictorKabata/DarajaSwiftPackage.git
```

https://github.com/VictorKabata/DarajaSwiftPackage/assets/39780120/b9283612-3c5e-4100-aff8-c2c0d9f31863

- Create an instance of the Daraja object by passing the daraja environment variables. The daraja
  object provides functions to request for an access token and initiate M-Pesa express STK request.

```swift
var daraja=Daraja(
        consumerKey: "your_consumer_key",
        consumerSecret: "your_customer_secret",
        passKey:"your_pass_key",
        environment: DarajaEnvironment.sandboxEnvironment
        )
```

> Network logging is enabled by default when using Daraja Multiplatform. in sandbox/testing mode.
> The logs can be accessed from the logs in XCode IDE

> Network logs are strictly disabled in production mode ie. DarajaEnvironment.productionEnvironment

### Request Access Token

- To request an access token from Daraja API, invoke the `authorization` function:

```swift
var accessTokenResult = daraja.authorization()

accessTokenResult.onSuccess(action: { accessToken in
    // Successfully fetched daraja access token
        // Successfully fetched the access token
    }).onFailure(action: { error in
     // Failure fetching daraja access token
    })
```

### Initiate M-Pesa Express STK Request

- To initiate M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpress` function:

```swift
var darajaResponse = daraja.mpesaExpress(
  businessShortCode: "174379",
  amount: 1,
  phoneNumber: "07xxxxxxxx",
  transactionType: DarajaTransactionType.customerpaybillonline, transactionDesc: "M-Pesa payment",
  callbackUrl: "https://mydomain.com/path",
  accountReference: "Company name")

  darajaResponse.onSuccess(action: { data in
    // Successfully requested M-Pesa STK request
    })
    .onFailure(action: { error in
    // Failed to request M-Pesa STK
    })

```

### Query M-Pesa Express STK

- To check the status of M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpressQuery` function:

```swift
var mpesaExpressQuery = daraja.mpesaExpressQuery(
    businessShortCode: "174379",
    timestamp: "20160216165627",
    checkoutRequestID: "ws_CO_260520211133524545"
)

mpesaExpressQuery.onSuccess(action:{ data in
// Successfully requested M-Pesa STK request status
})
.onFailure(action: {error in
// Failed to request M-Pesa STK status
})
```

### Generate Dynamic QR Code

- Generate a dynamic m-pesa qr code

```swift
var darajaQrCode= daraja.generateDynamicQr(
    merchantName = "TEST SUPERMARKET",
    referenceNumber = "Invoice Test",
    amount = 1,
    transactionCode = DarajaTransactionCode.BG,
    cpi = "373132",
    size = 400
)

darajaQrCode.onSuccess(action:{data in
// Successfully generated a QR code in base64 string format
})
.onFailure(action: {error in
// Failed to generate a QR code
})
```

### Query M-Pesa Transaction

- To check the status of an M-pesa transaction, invoke the `transactionStatus` function:

```swift
var darajaTransactionResponse = daraja.transactionStatus(
  businessShortCode: "174379", checkoutRequestID: "ws_CO_20122022180112029708374149")

darajaTransactionResponse.onSuccess(action: { data in
  // Successfully fetched M-pesa transaction status
}).onFailure(action: { error in
  // Failure fetching M-pesa transaction status
})
```

### Account Balance

Request the account balance of a short code. This can be used for both B2C, buy goods and pay bill
accounts.

```swift
    var accountBalanceResponse = daraja.accountBalance(
    initiator = "testapi",
    initiatorPassword = "Safaricom999!*!",
    partyA = 600987,
    identifierType = DarajaIdentifierType.TILL_NUMBER,
    queueTimeOutURL = "https://mydomain.com/AccountBalance/queue/",
    resultURL = "https://mydomain.com/AccountBalance/result/"
)

accountBalanceResponse.onSuccess(action: { data in
    // Successfully request account balance
}).onFailure(action: { error in
    // Failed to request account balance
})
```