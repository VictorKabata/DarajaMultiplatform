# Kotlin SDK 

## Getting Started

To get started with Daraja Multiplatform SDK, you will need to [create a Daraja API account](https://developer.safaricom.co.ke/) on the Daraja API portal and [set up a new test application](https://developer.safaricom.co.ke/MyApps).

Once you have access to the created test app, retrieve the ___Consumer Key___, ___Consumer Secret___ and ___Passkey___.

## Installation

- In Android projects, add the following dependency in the app/feature module gradle file:

```Kotlin hl_lines="2"
dependencies {
    implementation("io.github.victorkabata:daraja-multiplatform:<latest-version>")
}
```

- In Kotlin Multiplatform projects, add the following dependency in the common/shared module gradle file:

```Kotlin hl_lines="4"
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.victorkabata:daraja-multiplatform:<latest-version>")
        }
    }
}
```

## Setting Up

- Add the Consumer Key, Consumer Secret and Passkey to your project's environment secrets or local.properties file.
> To protect your sensitive API keys, it's recommended to store your Consumer Key, Consumer Secret, and Passkey in your project's environment secrets or a local properties file (outside of version control). This ensures they are not accidentally exposed in public repositories.

- Instantiate a `Daraja` object, providing the necessary environment variables. This Daraja instance serves as the entry point for various M-Pesa operations. Core functionalities include obtaining an access token required for subsequent API calls and initiating M-Pesa Express STK push requests.

```Kotlin
val daraja: Daraja = Daraja.Builder()
    .setConsumerSecret("your_consumer_secret")
    .setConsumerKey("your_consumer_key")
    .setPassKey("your_pass_key")
    .isProduction() // Optional. Will default to sandbox_mode = true
    .build()
```
> Daraja Multiplatform includes built-in network logging, which is active by default in sandbox mode but disabled in production mode. To inspect network requests and responses, view logs in Android Studio's Logcat under the __Daraja Multiplatform__ tag.

## Usage

### Request Access Token

To request an access token from Daraja API, invoke the `authorization` function:

```Kotlin
val accessTokenResult: DarajaResult<DarajaToken> = daraja.authorization()

accessTokenResult.onSuccess { accessToken ->
    println(accessToken)
}.onFailure { error ->
    println(error)
}
```

### Initiate M-Pesa Express STK Request

- To initiate M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpress` function:

```Kotlin
val darajaPaymentResponse: DarajaResult<MpesaExpressResponse> = daraja.mpesaExpress(
        businessShortCode = "174379",
        amount = 1,
        phoneNumber = "07xxxxxxxx", // or +2547xxxxxxxx or 2547xxxxxxxx
        transactionDesc = "M-Pesa payment",
        callbackUrl = "your_callback_url",
        accountReference = "CompanyName"
    )

darajaPaymentResponse.onSuccess { paymentResponse ->
    println(paymentResponse)
}.onFailure { error ->
   println(error)
}
```

### Query M-Pesa Express STK Status

- To check the status of M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `mpesaExpressQuery` function:

```Kotlin
val darajaMpesaExpressQuery: DarajaResult<QueryMpesaExpressResponse> = daraja.mpesaExpressQuery(
        businessShortCode = "174379",
        timeStamp = "20160216165627",
        checkOutRequestID = "ws_CO_260520211133524545"
    )

darajaMpesaExpressQuery.onSuccess { mpesaExpressQuery ->
    println(mpesaExpressQuery)
}.onFailure { error ->
   println(error)
}
```



