package ca.gc.chs.ihossv1;

import java.security.*;
import javax.crypto.*;

public class Ihossv1HardwareId extends Ihossv1EncryptableSecretKey {

  public static final int STRING_LENGTH = 10;
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;
  public static final int ENCRYPTED_STRING_LENGTH = STRING_LENGTH + 8 - (STRING_LENGTH % 8);
  public static final int ENCRYPTED_BYTE_ARRAY_LENGTH = ENCRYPTED_STRING_LENGTH / 2;

public Ihossv1HardwareId() {

	super();

}
public Ihossv1HardwareId(byte[] aByteArray, byte[] anEncryptedByteArray) {

	this();
	initialize(aByteArray, anEncryptedByteArray);

}
public Ihossv1HardwareId(String aString, String anEncryptedString) {

	this();
	initialize(aString, anEncryptedString);

}
public Ihossv1HardwareId(SecretKey aSecretKey, SecretKey anEncryptedSecretKey) {

	this();
	initialize(aSecretKey, anEncryptedSecretKey);

}
public void decrypt(Ihossv1ManufacturerKey aIhossv1ManufacturerKey)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  decrypt(aIhossv1ManufacturerKey.getSecretKey());

}
public void encrypt(Ihossv1ManufacturerKey aIhossv1ManufacturerKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  encrypt(aIhossv1ManufacturerKey.getSecretKey());

}
public byte[] getLongByteArray() {

	return Ihossv1MiscellaneousSupport.byteArray(getLongString());

}
public SecretKey getLongSecretKey() {

	return Ihossv1EncryptionSupport.blowfishSecretKey(getLongString());

}
public String getLongString() {

	return super.getString() + super.getString().substring(0, 2);

}
}
