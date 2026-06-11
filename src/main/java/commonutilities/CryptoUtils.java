package commonutilities;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class CryptoUtils {

	private static final String SECRET_KEY = "tc@123";
    private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";

    private int keySize;
    private int iterationCount;
    private Cipher cipher;

    /** Decode Base64 */
    private static byte[] base64(String str) {
        return Base64.getDecoder().decode(str);
    }

    /** Decode Hex string */
    private static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }

    /** Extract config from Base64 string */
    private Map<String, String> extractConfig(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) return Collections.emptyMap();

        String decoded = new String(Base64.getDecoder().decode(encodedString));
        String[] parts = decoded.split("::");

        if (parts.length != 5) return Collections.emptyMap();

        Map<String, String> map = new HashMap<>();
        map.put("keySize", parts[0]);
        map.put("iterationCount", parts[1]);
        map.put("iv", parts[2]);
        map.put("salt", parts[3]);
        map.put("cipherText", parts[4]);
        map.put("keyPhrase", SECRET_KEY);
        return map;
    }

    /** Generate AES key using PBKDF2 */
    private SecretKey generateKey(String salt, String passphrase) {
        try {
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException("Error generating key", e);
        }
    }

    /** Perform encryption/decryption */
    private byte[] doFinal(int mode, SecretKey key, String iv, byte[] data) {
        try {
            if (cipher == null) cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(mode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Cipher operation failed", e);
        }
    }

    /** Decrypt payload */
    public String decryptData(String encodedString) {
        Map<String, String> config = extractConfig(encodedString);
        this.keySize = Integer.parseInt(config.get("keySize"));
        this.iterationCount = Integer.parseInt(config.get("iterationCount"));
        SecretKey key = generateKey(config.get("salt"), config.get("keyPhrase"));
        byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, config.get("iv"), base64(config.get("cipherText")));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

}