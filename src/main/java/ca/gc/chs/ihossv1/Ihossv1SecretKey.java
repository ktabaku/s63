package ca.gc.chs.ihossv1;

import javax.crypto.*;

public abstract class Ihossv1SecretKey {

  private SecretKey fieldSecretKey;

public byte[] getByteArray() {

	return Ihossv1EncryptionSupport.byteArray(getSecretKey());

}
public SecretKey getSecretKey() {

	return fieldSecretKey;

}
public String getString() {

	return Ihossv1EncryptionSupport.hexString(getSecretKey());

}
public void initialize(byte[] aByteArray) {

	setByteArray(aByteArray);

}
public void initialize(String aString) {

	setString(aString);

}
public void initialize(SecretKey aSecretKey) {

	setSecretKey(aSecretKey);

}
public void setByteArray(byte[] aByteArray) {

	setSecretKey(Ihossv1EncryptionSupport.blowfishSecretKey(aByteArray));

}
public void setSecretKey(SecretKey aSecretKey) {

	fieldSecretKey = aSecretKey;

}
public void setString(String aString) {

	setSecretKey(Ihossv1EncryptionSupport.blowfishSecretKey(aString));

}
public String toString() {

	String toString = getClass().getName() + "(";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
}
