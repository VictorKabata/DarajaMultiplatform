package com.vickbt.darajakmp.utils

import at.asitplus.crypto.datatypes.CryptoPublicKey
import at.asitplus.crypto.datatypes.pki.X509Certificate
import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.toByteArray
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM
import okio.buffer
import okio.use


fun getCertCredentials(filePath: String) {
    val certBytes = FileSystem.SYSTEM.source(filePath.toPath()).buffer().use {certBuffer->
        certBuffer.readByteArray()
    }
    println(certBytes)

    val cert = X509Certificate.decodeFromTlv()
    println("Re-encoding it produces the same bytes? ${cert.encodeToDer()}")
    val publicKey = cert?.publicKey as CryptoPublicKey.Rsa

    //val cipher=Cipher

    println(
        "Certificate with serial no. ${cert.tbsCertificate.serialNumber.encodeBase64()} contains a ${publicKey.bits.number} bit RSA public key"
    )
}
