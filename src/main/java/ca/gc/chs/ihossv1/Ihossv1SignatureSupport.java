package ca.gc.chs.ihossv1;

import java.security.*;
import java.io.*;
import java.security.spec.*;
import java.util.*;
import java.security.interfaces.*;
import java.math.*;
import sun.security.util.*;

public abstract class Ihossv1SignatureSupport {

  private static KeyFactory dsaKeyFactory;
  private static Signature sha1WithDsaSignature;

  static {
    try {
      sha1WithDsaSignature = Signature.getInstance("SHA1withDSA");
      dsaKeyFactory = KeyFactory.getInstance("DSA");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

private static String block(String aHexString) {

	String aBlockedHexString = null;

	if (aHexString != null) {
		if (aHexString == "") {
			aBlockedHexString = "";
		} else {
			String aPaddedHexString;
			if ((aHexString.length() % 4) == 1) {
				aPaddedHexString = "000" + aHexString;
			} else if ((aHexString.length() % 4) == 2) {
				aPaddedHexString = "00" + aHexString;
			} else if ((aHexString.length() % 4) == 3) {
				aPaddedHexString = "0" + aHexString;
			} else {
				aPaddedHexString = aHexString;
			}
			aBlockedHexString = aPaddedHexString.substring(0, 4);
			int index = 8;
			while (index <= aPaddedHexString.length()) {
				aBlockedHexString = aBlockedHexString + " " + aPaddedHexString.substring(index - 4, index);
				index = index + 4;
			}
		}
	}

	return aBlockedHexString;

}
public static String[] blockedHexStringArray(String aCommentedString) throws IOException {

  BufferedReader in = new BufferedReader(new StringReader(aCommentedString));
  ArrayList anArrayList = new ArrayList();
  String aString = in.readLine();
  while (aString != null) {
    if (!aString.startsWith("//")) {
      anArrayList.add(aString.substring(0, aString.length() - 1));
    }
    aString = in.readLine();
  }
  String[] hexStringArray = new String[anArrayList.size()];
  anArrayList.toArray(hexStringArray);

  return hexStringArray;

}
public static byte[] derEncodedByteArray(String anRBlockedHexString, String anSBlockedHexString) throws IOException {

  byte[] aDsaSignatureByteArray = null;

  if ((anRBlockedHexString != null) && (anSBlockedHexString != null)) {
    DerOutputStream aDerOutputStream = new DerOutputStream();
    aDerOutputStream.putInteger(new BigInteger(unblock(anRBlockedHexString), 16));
    aDerOutputStream.putInteger(new BigInteger(unblock(anSBlockedHexString), 16));
    aDsaSignatureByteArray = (new DerValue((byte) 48, aDerOutputStream.toByteArray())).toByteArray();
  }

  return aDsaSignatureByteArray;

}
public static DSAPrivateKey dsaPrivateKey(
  String aPBlockedHexString,
  String aQBlockedHexString,
  String aGBlockedHexString,
  String anXBlockedHexString)
  throws InvalidKeySpecException {

  DSAPrivateKey aDsaPrivateKey = null;

  if ((aPBlockedHexString != null)
    && (aQBlockedHexString != null)
    && (aGBlockedHexString != null)
    && (anXBlockedHexString != null)) {
    BigInteger aPBigInteger = new BigInteger(unblock(aPBlockedHexString), 16);
    BigInteger aQBigInteger = new BigInteger(unblock(aQBlockedHexString), 16);
    BigInteger aGBigInteger = new BigInteger(unblock(aGBlockedHexString), 16);
    BigInteger anXBigInteger = new BigInteger(unblock(anXBlockedHexString), 16);
    aDsaPrivateKey =
      (DSAPrivateKey) dsaKeyFactory.generatePrivate(new DSAPrivateKeySpec(anXBigInteger, aPBigInteger, aQBigInteger, aGBigInteger));
  }

  return aDsaPrivateKey;

}
public static DSAPublicKey dsaPublicKey(
  String aPBlockedHexString,
  String aQBlockedHexString,
  String aGBlockedHexString,
  String aYBlockedHexString)
  throws InvalidKeySpecException {

  DSAPublicKey aDsaPublicKey = null;

  if ((aPBlockedHexString != null) && (aQBlockedHexString != null) && (aGBlockedHexString != null) && (aYBlockedHexString != null)) {
    BigInteger aPBigInteger = new BigInteger(unblock(aPBlockedHexString), 16);
    BigInteger aQBigInteger = new BigInteger(unblock(aQBlockedHexString), 16);
    BigInteger aGBigInteger = new BigInteger(unblock(aGBlockedHexString), 16);
    BigInteger aYBigInteger = new BigInteger(unblock(aYBlockedHexString), 16);
    aDsaPublicKey =
      (DSAPublicKey) dsaKeyFactory.generatePublic(new DSAPublicKeySpec(aYBigInteger, aPBigInteger, aQBigInteger, aGBigInteger));
  }

  return aDsaPublicKey;

}
public static String gBlockedHexString(DSAPrivateKey aDsaPrivateKey) {

	BigInteger aGBigInteger = null;

	if (aDsaPrivateKey != null) {
		aGBigInteger = aDsaPrivateKey.getParams().getG();
	}

	return block(aGBigInteger.toString(16).toUpperCase());

}
public static String gBlockedHexString(DSAPublicKey aDsaPublicKey) {

	BigInteger aGBigInteger = null;

	if (aDsaPublicKey != null) {
		aGBigInteger = aDsaPublicKey.getParams().getG();
	}

	return block(aGBigInteger.toString(16).toUpperCase());

}
public static String pBlockedHexString(DSAPrivateKey aDsaPrivateKey) {

	BigInteger aPBigInteger = null;

	if (aDsaPrivateKey != null) {
		aPBigInteger = aDsaPrivateKey.getParams().getP();
	}

	return block(aPBigInteger.toString(16).toUpperCase());

}
public static String pBlockedHexString(DSAPublicKey aDsaPublicKey) {

	BigInteger aPBigInteger = null;

	if (aDsaPublicKey != null) {
		aPBigInteger = aDsaPublicKey.getParams().getP();
	}

	return block(aPBigInteger.toString(16).toUpperCase());

}
public static String qBlockedHexString(DSAPrivateKey aDsaPrivateKey) {

	BigInteger aQBigInteger = null;

	if (aDsaPrivateKey != null) {
		aQBigInteger = aDsaPrivateKey.getParams().getQ();
	}

	return block(aQBigInteger.toString(16).toUpperCase());

}
public static String qBlockedHexString(DSAPublicKey aDsaPublicKey) {

	BigInteger aQBigInteger = null;

	if (aDsaPublicKey != null) {
		aQBigInteger = aDsaPublicKey.getParams().getQ();
	}

	return block(aQBigInteger.toString(16).toUpperCase());

}
public static String rBlockedHexString(byte[] aDerEncodedDsaSignatureByteArray) throws IOException {

  String anRBlockedHexString = null;

  DerInputStream aDerInputStream = new DerInputStream(aDerEncodedDsaSignatureByteArray);
  anRBlockedHexString = block(aDerInputStream.getSequence(2)[0].getBigInteger().toString(16).toUpperCase());

  return anRBlockedHexString;

}
public static String sBlockedHexString(byte[] aDerEncodedDsaSignatureByteArray) throws IOException {

  String anRBlockedHexString = null;

  DerInputStream aDerInputStream = new DerInputStream(aDerEncodedDsaSignatureByteArray);
  anRBlockedHexString = block(aDerInputStream.getSequence(2)[1].getBigInteger().toString(16).toUpperCase());

  return anRBlockedHexString;

}
public static byte[] signSha1WithDsa(byte[] aByteArray, PrivateKey aPrivateKey) throws InvalidKeyException, SignatureException {

  byte[] aSha1WithDsaSignatureByteArray = null;

  if ((aByteArray != null) && (aPrivateKey != null)) {
    sha1WithDsaSignature.initSign(aPrivateKey);
    sha1WithDsaSignature.update(aByteArray);
    aSha1WithDsaSignatureByteArray = sha1WithDsaSignature.sign();
  }

  return aSha1WithDsaSignatureByteArray;

}
public static byte[] signSha1WithDsa(File aFile, PrivateKey aPrivateKey)
  throws InvalidKeyException, SignatureException, IOException {

  byte[] aSha1WithDsaSignatureByteArray = null;

  if ((aFile != null) && (aPrivateKey != null)) {
    sha1WithDsaSignature.initSign(aPrivateKey);
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(aFile));
    int i = in.read();
    while (i != -1) {
      sha1WithDsaSignature.update((byte) i);
      i = in.read();
    }
    in.close();
    aSha1WithDsaSignatureByteArray = sha1WithDsaSignature.sign();
  }

  return aSha1WithDsaSignatureByteArray;

}
public static byte[] signSha1WithDsa(String aString, PrivateKey aPrivateKey) throws InvalidKeyException, SignatureException {

  byte[] aSha1WithDsaSignatureByteArray = null;

  if ((aString != null) && (aPrivateKey != null)) {
    aSha1WithDsaSignatureByteArray = signSha1WithDsa(aString.getBytes(), aPrivateKey);
  }

  return aSha1WithDsaSignatureByteArray;

}
private static String unblock(String aBlockedHexString) {

	String aHexString = null;

	if (aBlockedHexString != null) {
		if (aBlockedHexString.length() <= 4) {
			aHexString = aBlockedHexString;
		} else {
			aHexString = aBlockedHexString.substring(0, 4);
			int index = 9;
			while (index <= aBlockedHexString.length()) {
				aHexString = aHexString + aBlockedHexString.substring(index - 4, index);
				index = index + 5;
			}
			if (index - 5 != aBlockedHexString.length()) {
				aHexString = aHexString + aBlockedHexString.substring(index - 4, aBlockedHexString.length());
			}
		}
	}

	return aHexString;

}
public static boolean verifySha1WithDsa(byte[] aByteArray, byte[] aSha1WithDsaSignatureByteArray, PublicKey aPublicKey)
  throws InvalidKeyException, SignatureException {

  boolean verifies = false;

  if ((aByteArray != null) && (aSha1WithDsaSignatureByteArray != null) && (aPublicKey != null)) {
    sha1WithDsaSignature.initVerify(aPublicKey);
    sha1WithDsaSignature.update(aByteArray);
    verifies = sha1WithDsaSignature.verify(aSha1WithDsaSignatureByteArray);
  }

  return verifies;

}
public static boolean verifySha1WithDsa(File aFile, byte[] aSha1WithDsaSignatureByteArray, PublicKey aPublicKey)
  throws InvalidKeyException, SignatureException, IOException {

  boolean verifies = false;

  if ((aFile != null) && (aSha1WithDsaSignatureByteArray != null) && (aPublicKey != null)) {
    sha1WithDsaSignature.initVerify(aPublicKey);
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(aFile));
    int i = in.read();
    while (i != -1) {
      sha1WithDsaSignature.update((byte) i);
      i = in.read();
    }
    in.close();
    verifies = sha1WithDsaSignature.verify(aSha1WithDsaSignatureByteArray);
  }

  return verifies;

}
public static boolean verifySha1WithDsa(String aString, byte[] aSha1WithDsaSignatureByteArray, PublicKey aPublicKey)
  throws InvalidKeyException, SignatureException {

  boolean verifies = false;

  if ((aString != null) && (aSha1WithDsaSignatureByteArray != null) && (aPublicKey != null)) {
    verifies = verifySha1WithDsa(aString.getBytes(), aSha1WithDsaSignatureByteArray, aPublicKey);
  }

  return verifies;

}
public static String xBlockedHexString(DSAPrivateKey aDsaPrivateKey) {

	BigInteger anXBigInteger = null;

	if (aDsaPrivateKey != null) {
		anXBigInteger = aDsaPrivateKey.getX();
	}

	return block(anXBigInteger.toString(16).toUpperCase());

}
public static String yBlockedHexString(DSAPublicKey aDsaPublicKey) {

	BigInteger aYBigInteger = null;

	if (aDsaPublicKey != null) {
		aYBigInteger = aDsaPublicKey.getY();
	}

	return block(aYBigInteger.toString(16).toUpperCase());

}
}
