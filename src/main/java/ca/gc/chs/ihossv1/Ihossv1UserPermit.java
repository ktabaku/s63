package ca.gc.chs.ihossv1;

import java.security.*;
import javax.crypto.*;

public class Ihossv1UserPermit {

  public static int STRING_LENGTH =
    Ihossv1HardwareId.ENCRYPTED_STRING_LENGTH + Ihossv1UserPermitChecksum.STRING_LENGTH + Ihossv1ManufacturerId.STRING_LENGTH;

  private Ihossv1HardwareId fieldIhossv1HardwareId;
  private Ihossv1UserPermitChecksum fieldIhossv1UserPermitChecksum;
  private Ihossv1ManufacturerId fieldIhossv1ManufacturerId;

  public Ihossv1UserPermit() {

    super();

  }
  public Ihossv1UserPermit(Ihossv1HardwareId aIhossv1HardwareId, Ihossv1ManufacturerId aIhossv1ManufacturerId) {

    this();
    initialize(aIhossv1HardwareId, aIhossv1ManufacturerId);

  }
  public Ihossv1UserPermit(String aString) throws Ihossv1UserPermitChecksumException {

    this();
    initialize(aString);

  }
public void calculateIhossv1UserPermitChecksum() {

  fieldIhossv1UserPermitChecksum.calculate(fieldIhossv1HardwareId);

}
public void decryptIhossv1HardwareId(Ihossv1ManufacturerKey anIhossv1ManufacturerKey)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldIhossv1HardwareId.decrypt(anIhossv1ManufacturerKey);

}
public void encryptIhossv1HardwareId(Ihossv1ManufacturerKey anIhossv1ManufacturerKey)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldIhossv1HardwareId.encrypt(anIhossv1ManufacturerKey);

}
  public Ihossv1HardwareId getIhossv1HardwareId() {

    return fieldIhossv1HardwareId;

  }
  public Ihossv1ManufacturerId getIhossv1ManufacturerId() {

    return fieldIhossv1ManufacturerId;

  }
  public Ihossv1UserPermitChecksum getIhossv1UserPermitChecksum() {

    return fieldIhossv1UserPermitChecksum;

  }
  public String getString() {

    return fieldIhossv1HardwareId.getEncryptedString()
      + fieldIhossv1UserPermitChecksum.getString()
      + fieldIhossv1ManufacturerId.getString();

  }
  public void initialize(Ihossv1HardwareId aIhossv1HardwareId, Ihossv1ManufacturerId aIhossv1ManufacturerId) {

    fieldIhossv1HardwareId = aIhossv1HardwareId;
    fieldIhossv1UserPermitChecksum = new Ihossv1UserPermitChecksum();
    fieldIhossv1ManufacturerId = aIhossv1ManufacturerId;

  }
  public void initialize(String aString) throws Ihossv1UserPermitChecksumException {

    fieldIhossv1HardwareId = new Ihossv1HardwareId();
    fieldIhossv1UserPermitChecksum = new Ihossv1UserPermitChecksum();
    fieldIhossv1ManufacturerId = new Ihossv1ManufacturerId();

    setString(aString);

  }
  public void setIhossv1HardwareId(Ihossv1HardwareId aIhossv1HardwareId) {

    fieldIhossv1HardwareId = aIhossv1HardwareId;

  }
  public void setIhossv1ManufacturerId(Ihossv1ManufacturerId aIhossv1ManufacturerId) {

    fieldIhossv1ManufacturerId = aIhossv1ManufacturerId;

  }
  public void setString(String aString) {

    int startIndex;
    int endIndex;

    startIndex = 0;
    endIndex = startIndex + Ihossv1HardwareId.ENCRYPTED_STRING_LENGTH;
    fieldIhossv1HardwareId = new Ihossv1HardwareId(null, aString.substring(startIndex, endIndex));

    startIndex = endIndex;
    endIndex = startIndex + Ihossv1UserPermitChecksum.STRING_LENGTH;
    fieldIhossv1UserPermitChecksum.setString(aString.substring(startIndex, endIndex));

    startIndex = endIndex;
    endIndex = startIndex + Ihossv1ManufacturerId.STRING_LENGTH;
    fieldIhossv1ManufacturerId = new Ihossv1ManufacturerId(aString.substring(startIndex, endIndex));

  }
public String toString() {

	String toString = getClass().getName() + "(";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
public void verifyIhossv1UserPermitChecksum() throws Ihossv1UserPermitChecksumException {

  fieldIhossv1UserPermitChecksum.verify(fieldIhossv1HardwareId);

}
}
