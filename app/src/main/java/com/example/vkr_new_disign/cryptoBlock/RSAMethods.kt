package com.example.vkr_new_disign.cryptoBlock

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
fun stringToPublicKey(publicKeyString: String): PublicKey {
    val publicKeyBytes = Base64.getDecoder().decode(publicKeyString)
    val keySpec = X509EncodedKeySpec(publicKeyBytes)
    val keyFactory = KeyFactory.getInstance("RSA")
    return keyFactory.generatePublic(keySpec)
}
fun generateRSAKeys(): Pair<PublicKey, PrivateKey> {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048) // устанавливаем размер ключа
    val keyPair = keyPairGenerator.generateKeyPair()
    return Pair(keyPair.public, keyPair.private)
}

fun encryptRSA(publicKey: PublicKey, data: String): ByteArray {
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)
    return cipher.doFinal(data.toByteArray())
}

fun decryptRSA(privateKey: PrivateKey, encryptedData: ByteArray): String {
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    return String(cipher.doFinal(encryptedData))
}

