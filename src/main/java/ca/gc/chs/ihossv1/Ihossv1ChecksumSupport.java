package ca.gc.chs.ihossv1;

import java.util.zip.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.util.*;

public abstract class Ihossv1ChecksumSupport {

	static CRC32 crc32Checksum = new CRC32();

	public static byte[] computeCrc32Checksum(String aString) {

		byte[] aCrc32Checksum = null;

		if (aString != null) {
			crc32Checksum.reset();
			crc32Checksum.update(aString.getBytes());
			int crcValue = (int) crc32Checksum.getValue();
			String hexString = Integer.toHexString(crcValue);
			while (hexString.length() < 8) {
				hexString = "0" + hexString;
			}
			aCrc32Checksum = Ihossv1MiscellaneousSupport.byteArray(hexString);
		}

		return aCrc32Checksum;

	}

	public static boolean verifyCrc32Checksum(String aString, byte[] aChecksumByteArray) {
		boolean verifies = false;
		if ((aString != null) && (aChecksumByteArray != null)) {
			verifies = Arrays.equals(computeCrc32Checksum(aString), aChecksumByteArray);
		}
		return verifies;
	}
	public static boolean verifyDecryptedCrc32Checksum(FileInputStream in, SecretKey secretKey, byte[] aChecksumByteArray) {
		boolean verifies = false;
		try {
			ByteBuffer bb = Ihossv1EncryptionSupport.decryptBlowfish(in, secretKey);
			bb.rewind();
		    byte[] bytes = new byte[bb.remaining()];
		    bb.get(bytes, 0, bytes.length);
		    verifies = Arrays.equals(computeCrc32Checksum(bytes), aChecksumByteArray);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			
		}
		return verifies;
	}

	public static byte[] computeCrc32Checksum(byte[] aByteArray) {
		byte[] aCrc32Checksum = null;

		if (aByteArray != null) {
			crc32Checksum.reset();
			crc32Checksum.update(aByteArray);
			int crcValue = (int) crc32Checksum.getValue();
			String hexString = Integer.toHexString(crcValue);
			while (hexString.length() < 8) {
				hexString = "0" + hexString;
			}
			aCrc32Checksum = Ihossv1MiscellaneousSupport.byteArray(hexString);
		}

		return aCrc32Checksum;
	}

	public static boolean verifyCrc32Checksum(byte[] aByteArray, byte[] aChecksumByteArray) {

		boolean verifies = false;

		if ((aByteArray != null) && (aChecksumByteArray != null)) {
			verifies = Arrays.equals(computeCrc32Checksum(aByteArray), aChecksumByteArray);
		}

		return verifies;

	}
}
