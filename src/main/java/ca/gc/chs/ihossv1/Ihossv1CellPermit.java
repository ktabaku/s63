package ca.gc.chs.ihossv1;

import java.util.*;
import java.text.*;
import java.io.*;
import javax.crypto.*;
import java.security.*;

public class Ihossv1CellPermit {

  public static int STRING_LENGTH =
    Ihossv1CellName.STRING_LENGTH
      + Ihossv1CellPermitDate.STRING_LENGTH
      + 2 * Ihossv1CellKey.ENCRYPTED_STRING_LENGTH
      + Ihossv1CellPermitChecksum.ENCRYPTED_STRING_LENGTH;

  private Ihossv1CellName fieldIhossv1CellName;
  private Ihossv1CellPermitDate fieldIhossv1CellPermitDate;
  private Ihossv1CellKey fieldCurrentIhossv1CellKey;
  private Ihossv1CellKey fieldNextIhossv1CellKey;
  private Ihossv1CellPermitChecksum fieldIhossv1CellPermitChecksum;

public Ihossv1CellPermit() {

	super();

}
public Ihossv1CellPermit(
  Ihossv1CellName aIhossv1CellName,
  Ihossv1CellPermitDate aIhossv1CellPermitDate,
  Ihossv1CellKey currentIhossv1CellKey,
  Ihossv1CellKey nextIhossv1CellKey) {

  this();
  initialize(aIhossv1CellName, aIhossv1CellPermitDate, currentIhossv1CellKey, nextIhossv1CellKey);

}
public Ihossv1CellPermit(String aString) throws ParseException {

	this();
	initialize(aString);

}
public void calculateIhossv1CellPermitChecksum() {

	fieldIhossv1CellPermitChecksum.calculate(fieldIhossv1CellName, fieldIhossv1CellPermitDate, fieldCurrentIhossv1CellKey, fieldNextIhossv1CellKey);

}
public void decryptCurrentIhossv1CellKey(Ihossv1HardwareId anIhossv1HardwareId)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldCurrentIhossv1CellKey.decrypt(anIhossv1HardwareId);

}
public void decryptIhossv1CellPermitChecksum(Ihossv1HardwareId anIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldIhossv1CellPermitChecksum.decrypt(anIhossv1HardwareId);

}
public void decryptNextIhossv1CellKey(Ihossv1HardwareId anIhossv1HardwareId)
  throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldNextIhossv1CellKey.decrypt(anIhossv1HardwareId);

}
public void encryptCurrentIhossv1CellKey(Ihossv1HardwareId anIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldCurrentIhossv1CellKey.encrypt(anIhossv1HardwareId);

}
public void encryptIhossv1CellPermitChecksum(Ihossv1HardwareId anIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldIhossv1CellPermitChecksum.encrypt(anIhossv1HardwareId);

}
public void encryptNextIhossv1CellKey(Ihossv1HardwareId anIhossv1HardwareId)
  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

  fieldNextIhossv1CellKey.encrypt(anIhossv1HardwareId);

}
public Ihossv1CellKey getCurrentIhossv1CellKey() {

	return fieldCurrentIhossv1CellKey;

}
public Ihossv1CellName getIhossv1CellName() {

	return fieldIhossv1CellName;

}
public Ihossv1CellPermitChecksum getIhossv1CellPermitChecksum() {

  return fieldIhossv1CellPermitChecksum;

}
public Ihossv1CellPermitDate getIhossv1CellPermitDate() {

	return fieldIhossv1CellPermitDate;

}
public Ihossv1CellKey getNextIhossv1CellKey() {

	return fieldNextIhossv1CellKey;

}
public String getString() {

  return fieldIhossv1CellName.getString()
    + fieldIhossv1CellPermitDate.getString()
    + fieldCurrentIhossv1CellKey.getEncryptedString()
    + fieldNextIhossv1CellKey.getEncryptedString()
    + fieldIhossv1CellPermitChecksum.getEncryptedString();

}
public void initialize(
  Ihossv1CellName aIhossv1CellName,
  Ihossv1CellPermitDate aIhossv1CellPermitDate,
  Ihossv1CellKey currentIhossv1CellKey,
  Ihossv1CellKey nextIhossv1CellKey) {

  fieldIhossv1CellName = aIhossv1CellName;
  fieldIhossv1CellPermitDate = aIhossv1CellPermitDate;
  fieldCurrentIhossv1CellKey = currentIhossv1CellKey;
  fieldNextIhossv1CellKey = nextIhossv1CellKey;
  fieldIhossv1CellPermitChecksum = new Ihossv1CellPermitChecksum();

}
public void initialize(String aString) throws ParseException {

  fieldIhossv1CellName = new Ihossv1CellName();
  fieldIhossv1CellPermitDate = new Ihossv1CellPermitDate();
  fieldCurrentIhossv1CellKey = new Ihossv1CellKey();
  fieldNextIhossv1CellKey = new Ihossv1CellKey();
  fieldIhossv1CellPermitChecksum = new Ihossv1CellPermitChecksum();

  setString(aString);

}
public void setCurrentIhossv1CellKey(Ihossv1CellKey aIhossv1CellKey) {

	fieldCurrentIhossv1CellKey = aIhossv1CellKey;

}
public void setIhossv1CellName(Ihossv1CellName aIhossv1CellName) {

	fieldIhossv1CellName = aIhossv1CellName;

}
public void setIhossv1CellPermitDate(Ihossv1CellPermitDate aIhossv1CellPermitDate) {

	fieldIhossv1CellPermitDate = aIhossv1CellPermitDate;

}
public void setNextIhossv1CellKey(Ihossv1CellKey aIhossv1CellKey) {

	fieldNextIhossv1CellKey = aIhossv1CellKey;

}
public void setString(String aString) throws ParseException {

  int startIndex;
  int endIndex;

  startIndex = 0;
  endIndex = startIndex + Ihossv1CellName.STRING_LENGTH;
  fieldIhossv1CellName = new Ihossv1CellName(aString.substring(startIndex, endIndex));

  startIndex = endIndex;
  endIndex = startIndex + Ihossv1CellPermitDate.STRING_LENGTH;
  fieldIhossv1CellPermitDate = new Ihossv1CellPermitDate(aString.substring(startIndex, endIndex));

  startIndex = endIndex;
  endIndex = startIndex + Ihossv1CellKey.ENCRYPTED_STRING_LENGTH;
  fieldCurrentIhossv1CellKey = new Ihossv1CellKey(null, aString.substring(startIndex, endIndex));

  startIndex = endIndex;
  endIndex = startIndex + Ihossv1CellKey.ENCRYPTED_STRING_LENGTH;
  fieldNextIhossv1CellKey = new Ihossv1CellKey(null, aString.substring(startIndex, endIndex));

  startIndex = endIndex;
  endIndex = startIndex + Ihossv1CellPermitChecksum.ENCRYPTED_STRING_LENGTH;
  fieldIhossv1CellPermitChecksum.setEncryptedString(aString.substring(startIndex, endIndex));

}
public String toString() {

	String toString = getClass().getName() + "(";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
public void verifyIhossv1CellPermitChecksum() throws Ihossv1CellPermitChecksumException {

  fieldIhossv1CellPermitChecksum.verify(
    fieldIhossv1CellName,
    fieldIhossv1CellPermitDate,
    fieldCurrentIhossv1CellKey,
    fieldNextIhossv1CellKey);

}
}
