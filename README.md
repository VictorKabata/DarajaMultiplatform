<p align="center"><img src="assets/logo.png" alt="Daraja Multiplatform logo" height="100px"></p>

<p align="center">
<img  src="https://img.shields.io/badge/-ANDROID-3AA335?logo=android&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-IOS-3AA335?logo=ios&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-WINDOWS-CCCCCC?logo=windows&logoColor=606060&style=for-the-badge">
<img  src="https://img.shields.io/badge/-LINUX-CCCCCC?logo=linux&logoColor=606060&style=for-the-badge">
<img  src="https://img.shields.io/badge/-MACOS-CCCCCC?logo=apple&logoColor=606060&style=for-the-badge">
<img  src="https://img.shields.io/badge/-WEB-CCCCCC?logo=javascript&logoColor=606060&style=for-the-badge">
</p>

# ⚠️Not Stable⚠️

[Kotlin multiplatform](https://kotlinlang.org/docs/multiplatform.html) wrapper for Mpesa API dubbed [_Daraja API_](https://developer.safaricom.co.ke/) (Daraja means bridge in Swahili) that supports integration with your Android(Kotlin/Java), iOS(Swift) and JVM applications.
> M-PESA is a mobile money transfer service in Kenya that allows users to store and transfer money through their mobile phones.

## Prerequisite

To get started, you’ll need to create an account on the Daraja API portal to use the Daraja API. [How to get started with Daraja API](https://developer.safaricom.co.ke/Documentation).

After successfully creating an account on the Daraja API portal and creating a new Daraja app, you’ll need to add your ___consumer key___, ___consumer secret___ and ___pass key___ obtained from the Daraja API portal to your project.

## How to use in your project

Daraja Multiplatform is available for download from [Maven Central](https://mvnrepository.com/repos/central).

## Android

- In your android application project-level gradle file add the following dependency:

<details open>
<summary>Kotlin</summary>

```Kotlin
  dependencies {
    implementation ("io.github.victorkabata:daraja-multiplatform:0.9.2")
  }
```

</details>
  
<details>
<summary>Groovy</summary>

```Groovy
  dependencies {
    implementation 'io.github.victorkabata:daraja-multiplatform:0.9.2'
  }
 ```

 </details>

- Add your consumer secret, consumer key and pass key to your project. You can get them from the [Daraja API portal](https://developer.safaricom.co.ke/MyApps).

```Kotlin
object Constants {
    const val CONSUMER_SECRET="your_consumer_secret"
    const val CONSUMER_KEY="your_consumer_key"
    const val PASS_KEY="your_pass_key"
}
```

> You should not add your daraja API environment variables in a production application because it is a vulnerability to expose your environment secrets/variables in your version control system. Ideally, you should add them to your `Local.properties` files as demonstrated in the [sample](https://github.com/VictorKabata/DarajaMultiplatform/tree/main/app-android) android application.

- Create an instance of the Daraja object by passing the daraja environment variables. The daraja object provides functions to request for an access token and initiate M-Pesa express STK request.

```Kotlin
val daraja: Daraja = Daraja.Builder()
            .setConsumerSecret("your_consumer_secret")
            .setConsumerKey("your_consumer_key")
            .setPassKey("your_pass_key")
            .isProduction() // Optional. Will default to sandbox_mode = true
            .build()
```

> Network logging is enabled by default when using Daraja Multiplatform. in sandbox/testing mode. The logs can be accessed from the logcat in Android Studio under the `Daraja Multiplatform` tag.

> Network logs are strictly disabled in production mode.

- To request an access token from Daraja API, invoke the `requestAccessToken` function:

```Kotlin
val accessTokenResult: DarajaResult<DarajaToken> = daraja.requestAuthToken()

accessTokenResult
        .onSuccess { accessToken ->
            // Successfully fetched daraja access token
        }
        .onFailure { error ->
            // Failure fetching daraja access token
        }
```

- To initiate M-Pesa Express(Lipa na M-Pesa Online) STK request, invoke the `initiateDarajaStk` function:

```Kotlin
val darajaPaymentResponse: DarajaResult<DarajaPaymentResponse> = daraja.initiateDarajaStk(
            businessShortCode = "174379",
            amount = 1,
            phoneNumber = "07xxxxxxxx",
            transactionDesc = "M-Pesa payment",
            callbackUrl = "your_callback_url",
            accountReference = "CompanyName"
        )

darajaPaymentResponse
        .onSuccess { paymentResponse ->
            // Successfully requested M-Pesa STK request
        }
        .onFailure { error ->
            // Failed to request M-Pesa STK
        }
```

- To check the status of an M-pesa transaction, invoke the `queryMpesaTransaction` function:

```Kotlin
val darajaTransactionResponse: DarajaResult<DarajaTransactionResponse> = daraja.queryMpesaTransaction(
            businessShortCode = "174379",
            checkoutRequestID = "ws_CO_20122022180112029708374149"
        )

darajaTransactionResponse
        .onSuccess { transactionResponse ->
            // Successfully fetched M-pesa transaction status
        }
        .onFailure { error ->
            // Failure fetching M-pesa transaction status
        }
```
