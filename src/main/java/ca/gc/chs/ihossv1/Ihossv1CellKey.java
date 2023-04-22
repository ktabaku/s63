package ca.gc.chs.ihossv1;

import java.security.*;
import javax.crypto.*;

public class Ihossv1CellKey extends Ihossv1EncryptableSecretKey {

  public static final int STRING_LENGTH = 10;
  public static final int ENCRYPTED_STRING_LENGTH = STRING_LENGTH + 8 - (STRING_LENGTH % 8);
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;
  public static final int ENCRYPTED_BYTE_ARRAY_LENGTH = ENCRYPTED_STRING_LENGTH / 2;

public Ihossv1CellKey() {

	super();

}
public Ihossv1CellKey(byte[] aByteArray, byte[] anEncryptedByteArray) {

	this();
	initialize(aByteArray, anEncryptedByteArray);

}
public Ihossv1CellKey(String aString, String anEncryptedString) {

	this();
	initialize(aString, anEncryptedString);

}
public Ihossv1CellKey(SecretKey aSecretKey, SecretKey anEncryptedSecretKey) {

	this();
	initialize(aSecretKey, anEncryptedSecretKey);

}
public void decrypt(Ihossv1HardwareId aIhossv1HardwareId)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  decrypt(aIhossv1HardwareId.getLongSecretKey());

}
public void encrypt(Ihossv1HardwareId aIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  encrypt(aIhossv1HardwareId.getLongSecretKey());

}
}
