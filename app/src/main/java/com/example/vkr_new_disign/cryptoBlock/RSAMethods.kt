package com.example.vkr_new_disign.cryptoBlock

import android.os.Build
import android.webkit.URLUtil.decode
import androidx.annotation.RequiresApi
import java.lang.Byte.decode
import java.net.URLDecoder.decode
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import java.security.KeyFactory

import android.util.Base64
import java.math.BigInteger
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec
import java.security.SecureRandom
import java.security.spec.MGF1ParameterSpec
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource





fun stringToPublicKey(input: String): PublicKey {
    val pattern = "<Modulus>(.*?)</Modulus><Exponent>(.*?)</Exponent>".toRegex()

    val matchResult = pattern.find(input)
    val modulusString = matchResult?.groupValues?.get(1)
    val exponentString = matchResult?.groupValues?.get(2)

    val modulusBytes = Base64.decode(modulusString, Base64.DEFAULT)
    val exponentBytes = Base64.decode(exponentString, Base64.DEFAULT)

    val modulus = BigInteger(1, modulusBytes)
    val exponent = BigInteger(1, exponentBytes)

    val publicKeySpec = RSAPublicKeySpec(modulus, exponent)
    val keyFactory = KeyFactory.getInstance("RSA")
    val publicKey = keyFactory.generatePublic(publicKeySpec) as RSAPublicKey


    return publicKey
}

fun byteArrToString(input: ByteArray): String{
    var result = ""
    for(e in input){
        result = "$result$e,"
    }
    return result
}

fun generateRSAKeys(): Pair<PublicKey, PrivateKey> {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048) // устанавливаем размер ключа
    val keyPair = keyPairGenerator.generateKeyPair()
    return Pair(keyPair.public, keyPair.private)
}

fun encryptRSA(publicKey: PublicKey, data: String): ByteArray {
    val cipher = Cipher.getInstance("RSA/ECB/OAEPPadding")

    cipher.init(Cipher.ENCRYPT_MODE, publicKey)

    println(byteArrToString(cipher.doFinal(data.toByteArray())))
    return cipher.doFinal(data.toByteArray())
}

fun decryptRSA(privateKey: PrivateKey, encryptedData: ByteArray): String {
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    return String(cipher.doFinal(encryptedData))
}

