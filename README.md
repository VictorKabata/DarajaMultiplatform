# **Daraja Multiplatform**

<p align="left">
<img  src="https://img.shields.io/badge/-ANDROID-3AA335?logo=kotlin&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-IOS-3AA335?logo=swift&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-DESKTOP-3AA335?logo=openjdk&logoColor=white&style=for-the-badge">
<img  src="https://img.shields.io/badge/-WEB-CCCCCC?logo=javascript&logoColor=606060&style=for-the-badge">
</p>

[Kotlin multiplatform](https://kotlinlang.org/docs/multiplatform.html) wrapper for Mpesa API
dubbed [_Daraja API_](https://developer.safaricom.co.ke/) (Daraja means bridge in Swahili) that
supports integration with your Android(Kotlin/Java), iOS(Swift) and JVM applications.

> [!Note]
> M-PESA is a mobile money transfer service in Kenya that allows users to store and transfer money
> through their mobile phones.

> [!Caution]
> Daraja Multiplatform is under heavy development and, despite being heavily tested, its API isn't yet stabilized; _breaking changes
> might happen on minor releases._ However, we will always provide migration guides.
>
> Report any issue or bug <a href="/issues">in the GitHub repository.</a>

## Table of Content

- [Prerequisite](#prerequisite)
- [Features](#features)

## Prerequisite

To get started, you’ll need to create an account on the Daraja API portal to use the Daraja
API. [How to get started with Daraja API](https://developer.safaricom.co.ke/Documentation).

After successfully creating an account on the Daraja API portal and creating a new Daraja app,
you’ll need to add your **_consumer key_**, **_consumer secret_** and **_pass key_** obtained from
the Daraja API portal to your project.

## Features

The SDK offers the following functionalities from the Daraja API:

- [x] Authorization - Gives you a time-bound access token to call allowed APIs.
- [x] M-Pesa Express - Merchant initiated online payments.
- [x] M-Pesa Express Query - Check the status of a Lipa Na M-Pesa Online Payment(M-Pesa Express).
- [ ] Dynamic QR - Generates a dynamic M-PESA QR code.
- [ ] Customer To Business (C2B)
- [ ] Business To Customer (B2C) - Transact between an M-Pesa shortcode to a phone number
  registered on M-Pesa.
- [ ] Transaction Status - Check the status of a transaction.
- [ ] Account Balance - Enquire the balance on an M-Pesa BuyGoods (Till Number)
- [ ] Reversal - Reverses an M-Pesa transaction.
- [ ] Tax Remittance - This API enables businesses to remit tax to the Kenya Revenue Authority (KRA).
- [ ] Business Pay Bill - Pay bills directly from your business account to a pay bill number, or a
  pay bill store.
- [ ] Business Buy Goods - Pay for goods and services directly from your business account to a till
  number or merchant store number.

> [!Important]
> See the [project's website](https://victorkabata.github.io/DarajaMultiplatform/) for documentation.
