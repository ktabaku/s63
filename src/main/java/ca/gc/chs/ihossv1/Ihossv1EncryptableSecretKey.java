package ca.gc.chs.ihossv1;

import java.security.*;
import javax.crypto.*;

public abstract class Ihossv1EncryptableSecretKey extends Ihossv1SecretKey {

  private byte[] fieldEncryptedByteArray;

public void decrypt(SecretKey aSecretKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  setByteArray(Ihossv1EncryptionSupport.decryptBlowfish(getEncryptedByteArray(), aSecretKey));

}
public void encrypt(SecretKey aSecretKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  setEncryptedByteArray(Ihossv1EncryptionSupport.encryptBlowfish(getByteArray(), aSecretKey));

}
public byte[] getEncryptedByteArray() {

	return fieldEncryptedByteArray;

}
public String getEncryptedString() {

	return Ihossv1MiscellaneousSupport.hexString(getEncryptedByteArray());

}
public void initialize(byte[] aByteArray, byte[] anEncryptedByteArray) {

	initialize(aByteArray);
	setEncryptedByteArray(anEncryptedByteArray);

}
public void initialize(String aString, String anEncryptedString) {

	initialize(aString);
	setEncryptedString(anEncryptedString);

}
public void initialize(SecretKey aSecretKey, SecretKey anEncryptedSecretKey) {

	initialize(aSecretKey);
	setEncryptedByteArray(Ihossv1EncryptionSupport.byteArray(anEncryptedSecretKey));

}
public void setEncryptedByteArray(byte[] anEncryptedByteArray) {

	fieldEncryptedByteArray = anEncryptedByteArray;

}
public void setEncryptedString(String anEncryptedString) {

	setEncryptedByteArray(Ihossv1MiscellaneousSupport.byteArray(anEncryptedString));

}
}
