# Overview

**Daraja Multiplatform** is a Kotlin Multiplatform library that simplifies M-Pesa integration for cross-platform mobile applications. It provides a unified API for both Android(Kotlin) and iOS(Swift), enabling developers to build feature-rich apps with seamless M-Pesa functionalities.

> [M-Pesa](https://en.wikipedia.org/wiki/M-Pesa) is a mobile money transfer service developed by [Vodafone](https://www.vodafone.com/) and [Safaricom](https://www.safaricom.co.ke/) that allows users to store and transfer money through their mobile phones.

## Project Goals

Daraja Multiplatform aims to streamline the integration of M-Pesa services into Kotlin and Swift applications by providing a simplified interface to the [Daraja API](https://developer.safaricom.co.ke/APIs).

By abstracting away the complexities of the underlying API, the library significantly reduces development time and effort. Additionally, Daraja Multiplatform promotes code reusability and maintainability across different platforms, allowing developers to focus on core application features rather than low-level integration details.

## Supported Platforms & Features

Currently, Daraja Multiplatform supports Kotlin and Swift applications.

Core features include:

* [x] Authorization - Gives you a time bound access token to call allowed APIs.
- [x] M-Pesa Express - Merchant initiated online payments.
- [x] M-Pesa Express Query - Check the status of a Lipa Na M-Pesa Online Payment(M-Pesa Express).
- [ ] Dynamic QR - Generates a dynamic M-PESA QR code.
- [ ] Customer To Business (C2B)
- [ ] Business To Customer (B2C) - Transact between an M-Pesa short code to a phone number
  registered on M-Pesa.
- [ ] Transaction Status - Check the status of a transaction.
- [ ] Account Balance - Enquire the balance on an M-Pesa BuyGoods (Till Number)
- [ ] Reversal - Reverses an M-Pesa transaction.
- [ ] Tax Remittance - This API enables businesses to remit tax to Kenya Revenue Authority (KRA).
- [ ] Business Pay Bill - Pay bills directly from your business account to a pay bill number, or a
  paybill store.
- [ ] Business Buy Goods - Pay for goods and services directly from your business account to a till number or merchant store number.


