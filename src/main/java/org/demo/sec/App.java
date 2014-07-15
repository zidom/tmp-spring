package org.demo.sec;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 * @author zidom
 *
 */
public class App {

	static BouncyCastleProvider provider = new BouncyCastleProvider();

	public static void main(String[] args) throws Throwable {

		String ALGORITHM = "DES";

		// KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, provider);
		//
		// kg.init(64);
		//
		// SecretKey key = kg.generateKey();

		byte[] keys = "jdsahljksdfhgjkashdkjgaksdlghjkashgjkahsdljkg"
				.getBytes();

		DESKeySpec desKeySpec = new DESKeySpec(keys);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey key = keyFactory.generateSecret(desKeySpec);

		System.out.println(Arrays.toString(key.getEncoded()));

		Cipher cipher = Cipher.getInstance(ALGORITHM, provider);

		String plaintext = "abcdefghijklmnopqrstuvwxyz01234567890qwertyuiop[]/asdfghjkl;'";

		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

		System.out.println(Base64.toBase64String(ciphertext));

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] plaintextBytes = cipher.doFinal(ciphertext);

		System.out.println(new String(plaintextBytes));

	}
}
