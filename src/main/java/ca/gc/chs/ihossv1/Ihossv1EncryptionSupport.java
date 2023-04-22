package ca.gc.chs.ihossv1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sybina.ebr.ByteBufferInputStream;
import com.sybina.ebr.ByteBufferOutputStream;

public abstract class Ihossv1EncryptionSupport {

	static Cipher blowfishCipher;

	static {
		try {
			blowfishCipher = Cipher.getInstance("BLOWFISH/ECB/PKCS5PADDING");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public static SecretKey blowfishSecretKey(byte[] aByteArray) {

		SecretKey aSecretKey = null;

		if (aByteArray != null) {
			aSecretKey = (SecretKey) new SecretKeySpec(aByteArray, "Blowfish");
		}

		return aSecretKey;

	}

	public static SecretKey blowfishSecretKey(String aHexString) {

		SecretKey aSecretKey = null;

		if (aHexString != null) {
			aSecretKey = blowfishSecretKey(Ihossv1MiscellaneousSupport.byteArray(aHexString));
		}

		return aSecretKey;

	}

	public static byte[] byteArray(SecretKey aSecretKey) {

		byte[] aByteArray = null;

		if (aSecretKey != null) {
			aByteArray = ((SecretKeySpec) aSecretKey).getEncoded();
		}

		return aByteArray;

	}

	public static void compressZipThenEncryptBlowfish(File aFile, File aZippedThenEncryptedFile, SecretKey aSecretKey)
			throws IOException, SecurityException, InvalidKeyException {

		FileInputStream aFileInputStream = new FileInputStream(aFile);
		BufferedInputStream aBufferedInputStream = new BufferedInputStream(aFileInputStream);
		InputStream in = aBufferedInputStream;

		FileOutputStream aFileOutputStream = new FileOutputStream(aZippedThenEncryptedFile);
		BufferedOutputStream aBufferedOutputStream = new BufferedOutputStream(aFileOutputStream);
		blowfishCipher.init(Cipher.ENCRYPT_MODE, aSecretKey);
		CipherOutputStream cypherOutputStream = new CipherOutputStream(aBufferedOutputStream, blowfishCipher);
		ZipOutputStream aZipOutputStream = new ZipOutputStream(cypherOutputStream);
		aZipOutputStream.putNextEntry(new ZipEntry(aFile.getName()));
		OutputStream out = aZipOutputStream;

		int i = in.read();
		while (i != -1) {
			out.write(i);
			i = in.read();
		}

		in.close();
		out.close();

	}

	public static byte[] decryptBlowfish(byte[] aByteArray, SecretKey aSecretKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		byte[] aDecryptedByteArray = null;

		if ((aByteArray != null) && (aSecretKey != null)) {
			blowfishCipher.init(Cipher.DECRYPT_MODE, aSecretKey);
			aDecryptedByteArray = blowfishCipher.doFinal(aByteArray);
		}

		return aDecryptedByteArray;

	}
	
	public static ByteBuffer decryptBlowfish(FileInputStream fis, SecretKey aSecretKey)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		ByteBufferOutputStream aByteBufferOutputStream = new ByteBufferOutputStream(ByteBuffer.allocate((int)fis.getChannel().size()), true);
		
		blowfishCipher.init(Cipher.DECRYPT_MODE, aSecretKey);
		CipherInputStream cis = new CipherInputStream(fis, blowfishCipher);
		
		byte[] block = new byte[8];
		int i;
		while ((i = cis.read(block)) != -1) {
			aByteBufferOutputStream.write(block, 0, i);
		}
		aByteBufferOutputStream.close();
		return aByteBufferOutputStream.toByteBuffer();
	}
	
	public static void decryptBlowfishThenDecompressZip(File aCompressedThenEncryptedFile, File aFile,
			SecretKey aSecretKey) throws IOException, SecurityException, InvalidKeyException {

		FileInputStream aFileInputStream = new FileInputStream(aCompressedThenEncryptedFile);
		BufferedInputStream aBufferedInputStream = new BufferedInputStream(aFileInputStream);
		blowfishCipher.init(Cipher.DECRYPT_MODE, aSecretKey);
		CipherInputStream aCipherInputStream = new CipherInputStream(aBufferedInputStream, blowfishCipher);
		ZipInputStream aZipInputStream = new ZipInputStream(aCipherInputStream);
		aZipInputStream.getNextEntry();
		InputStream in = aZipInputStream;

		FileOutputStream aFileOutputStream = new FileOutputStream(aFile);
		BufferedOutputStream aBufferedOutputStream = new BufferedOutputStream(aFileOutputStream);
		OutputStream out = aBufferedOutputStream;

		int i = in.read();
		while (i != -1) {
			out.write(i);
			i = in.read();
		}

		in.close();
		out.close();

	}

	public static void decryptBlowfishThenDecompressZip(File aCompressedThenEncryptedFile, File aFile,
			SecretKey aSecretKey, SecretKey outputSecretKey)
			throws IOException, SecurityException, InvalidKeyException {

		FileInputStream aFileInputStream = new FileInputStream(aCompressedThenEncryptedFile);
		BufferedInputStream aBufferedInputStream = new BufferedInputStream(aFileInputStream);
		blowfishCipher.init(Cipher.DECRYPT_MODE, aSecretKey);
		CipherInputStream aCipherInputStream = new CipherInputStream(aBufferedInputStream, blowfishCipher);
		ZipInputStream aZipInputStream = new ZipInputStream(aCipherInputStream);
		aZipInputStream.getNextEntry();
		InputStream in = aZipInputStream;
		
		ByteBufferOutputStream aByteBufferOutputStream = new ByteBufferOutputStream(ByteBuffer.allocate((int)aCompressedThenEncryptedFile.length()), true);
		OutputStream out = aByteBufferOutputStream;
		
		int i = in.read();
		while (i != -1) {
			out.write(i);
			i = in.read();
		}
		in.close();
		out.close();
		
		ByteBufferInputStream aByteBufferInputStream = new ByteBufferInputStream(aByteBufferOutputStream.toByteBuffer());
		in = aByteBufferInputStream;
		
		FileOutputStream aFileOutputStream = new FileOutputStream(aFile);
		BufferedOutputStream aBufferedOutputStream = new BufferedOutputStream(aFileOutputStream);
		blowfishCipher.init(Cipher.ENCRYPT_MODE, outputSecretKey);
		CipherOutputStream cypherOutputStream = new CipherOutputStream(aBufferedOutputStream, blowfishCipher);
		out = cypherOutputStream;
		
		i = in.read();
		while (i != -1) {
			out.write(i);
			i = in.read();
		}
		in.close();
		out.close();
		aBufferedOutputStream.close();
		aFileOutputStream.close();
	}

	public static byte[] encryptBlowfish(byte[] aByteArray, SecretKey aSecretKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		byte[] anEncryptedByteArray = null;

		if ((aByteArray != null) && (aSecretKey != null)) {
			Cipher blowfishCipher = Cipher.getInstance("Blowfish" + "/" + "ECB" + "/" + "PKCS5Padding");
			blowfishCipher.init(Cipher.ENCRYPT_MODE, aSecretKey);
			anEncryptedByteArray = blowfishCipher.doFinal(aByteArray);
		}

		return anEncryptedByteArray;

	}

	public static String hexString(SecretKey aSecretKey) {

		String aHexString = null;

		if (aSecretKey != null) {
			aHexString = Ihossv1MiscellaneousSupport.hexString(byteArray(aSecretKey));
		}

		return aHexString;

	}

}
