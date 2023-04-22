package ca.gc.chs.ihossv1;

import javax.crypto.*;
import java.security.*;

public class Ihossv1CellPermitChecksum extends Ihossv1Crc32Checksum {

  public static final int ENCRYPTED_STRING_LENGTH = STRING_LENGTH + 8 - (STRING_LENGTH % 8);
  public static final int ENCRYPTED_BYTE_ARRAY_LENGTH = ENCRYPTED_STRING_LENGTH / 2;

  private String fieldEncryptedString;

public Ihossv1CellPermitChecksum() {

	super();

}
public Ihossv1CellPermitChecksum(byte[] aByteArray, byte[] anEncryptedByteArray) {

	this();
	initialize(aByteArray, anEncryptedByteArray);

}
public Ihossv1CellPermitChecksum(
  Ihossv1CellName aIhossv1CellName,
  Ihossv1CellPermitDate aIhossv1CellPermitDate,
  Ihossv1CellKey currentIhossv1CellKey,
  Ihossv1CellKey nextIhossv1CellKey) {

  this();

  initialize(aIhossv1CellName, aIhossv1CellPermitDate, currentIhossv1CellKey, nextIhossv1CellKey);

}
public Ihossv1CellPermitChecksum(String aString, String anEncryptedString) {

	this();
	
	initialize(aString, anEncryptedString);

}
public void calculate(
	Ihossv1CellName aIhossv1CellName, 
	Ihossv1CellPermitDate aIhossv1CellPermitDate, 
	Ihossv1CellKey currentIhossv1CellKey, 
	Ihossv1CellKey nextIhossv1CellKey) {

	setString(
		Ihossv1MiscellaneousSupport.hexString(
			Ihossv1ChecksumSupport.computeCrc32Checksum(
				aIhossv1CellName.getString()
					+ aIhossv1CellPermitDate.getString()
					+ currentIhossv1CellKey.getEncryptedString()
					+ nextIhossv1CellKey.getEncryptedString()))); 

}
public void decrypt(Ihossv1HardwareId aIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  decrypt(aIhossv1HardwareId.getLongSecretKey());

}
public void decrypt(SecretKey aSecretKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  setByteArray(Ihossv1EncryptionSupport.decryptBlowfish(getEncryptedByteArray(), aSecretKey));

}
public void encrypt(Ihossv1HardwareId aIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  encrypt(aIhossv1HardwareId.getLongSecretKey());

}
public void encrypt(SecretKey aSecretKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  setEncryptedByteArray(Ihossv1EncryptionSupport.encryptBlowfish(getByteArray(), aSecretKey));

}
public byte[] getEncryptedByteArray() {

	return Ihossv1MiscellaneousSupport.byteArray(getEncryptedString());

}
public String getEncryptedString() {

	return fieldEncryptedString;

}
public void initialize(byte[] aByteArray, byte[] anEncryptedByteArray) {

  super.initialize(aByteArray);
  setEncryptedByteArray(anEncryptedByteArray);

}
public void initialize(
	Ihossv1CellName aIhossv1CellName, 
	Ihossv1CellPermitDate aIhossv1CellPermitDate, 
	Ihossv1CellKey currentIhossv1CellKey, 
	Ihossv1CellKey nextIhossv1CellKey) {

	calculate(aIhossv1CellName, aIhossv1CellPermitDate, currentIhossv1CellKey, nextIhossv1CellKey);

}
public void initialize(String aString, String anEncryptedString) {

  initialize(aString);
  setEncryptedString(anEncryptedString);

}
public void setEncryptedByteArray(byte[] anEncryptedByteArray) {

	setEncryptedString(Ihossv1MiscellaneousSupport.hexString(anEncryptedByteArray));

}
public void setEncryptedString(String anEncryptedString) {

	fieldEncryptedString = anEncryptedString;

}
public void verify(
  Ihossv1CellName aIhossv1CellName,
  Ihossv1CellPermitDate aIhossv1CellPermitDate,
  Ihossv1CellKey currentIhossv1CellKey,
  Ihossv1CellKey nextIhossv1CellKey)
  throws Ihossv1CellPermitChecksumException {

  if (!getString()
    .equals(
      Ihossv1MiscellaneousSupport.hexString(
        Ihossv1ChecksumSupport.computeCrc32Checksum(
          aIhossv1CellName.getString()
            + aIhossv1CellPermitDate.getString()
            + currentIhossv1CellKey.getEncryptedString()
            + nextIhossv1CellKey.getEncryptedString())))) {
    throw (new Ihossv1CellPermitChecksumException());
  }

}
}
