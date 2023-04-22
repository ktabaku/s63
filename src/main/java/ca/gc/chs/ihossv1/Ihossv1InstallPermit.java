package ca.gc.chs.ihossv1;

import java.security.*;
import javax.crypto.*;

public class Ihossv1InstallPermit extends Ihossv1EncryptableSecretKey {

  public static final int STRING_LENGTH = 10;
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;
  public static final int ENCRYPTED_STRING_LENGTH = STRING_LENGTH + 8 - (STRING_LENGTH % 8);
  public static final int ENCRYPTED_BYTE_ARRAY_LENGTH = ENCRYPTED_STRING_LENGTH / 2;

public Ihossv1InstallPermit() {

	super();

}
public Ihossv1InstallPermit(byte[] aByteArray, byte[] anEncryptedByteArray) {

	this();
	initialize(aByteArray, anEncryptedByteArray);

}
public Ihossv1InstallPermit(String aString, String anEncryptedString) {

	this();
	initialize(aString, anEncryptedString);

}
public Ihossv1InstallPermit(SecretKey aSecretKey, SecretKey anEncryptedSecretKey) {

	this();
	initialize(aSecretKey, anEncryptedSecretKey);

}
public void decrypt(Ihossv1SystemIdentificationKey aIhossv1SystemIdentificationKey)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  decrypt(aIhossv1SystemIdentificationKey.getSecretKey());

}
public void encrypt(Ihossv1SystemIdentificationKey aIhossv1SystemIdentificationKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  encrypt(aIhossv1SystemIdentificationKey.getSecretKey());

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
