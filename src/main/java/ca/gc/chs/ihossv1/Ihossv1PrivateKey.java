package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class Ihossv1PrivateKey {

  DSAPrivateKey fieldDsaPrivateKey;

public Ihossv1PrivateKey() {

	super();

}
public Ihossv1PrivateKey(String[] aBlockedHexStringArray) throws InvalidKeySpecException {

  this();
  initialize(aBlockedHexStringArray);

}
public Ihossv1PrivateKey(File aFile) throws IOException, InvalidKeySpecException  {

  this();
  initialize(aFile);

}
public Ihossv1PrivateKey(String aIhossv1PrivateKeyString) throws IOException, InvalidKeySpecException {

  this();
  initialize(aIhossv1PrivateKeyString);

}
public Ihossv1PrivateKey(String pBlockedHexString, String qBlockedHexString, String gBlockedHexString, String xBlockedHexString)
  throws InvalidKeySpecException {

  this();
  initialize(pBlockedHexString, qBlockedHexString, gBlockedHexString, xBlockedHexString);

}
public Ihossv1PrivateKey(DSAPrivateKey aDsaPrivateKey) {

	this();
	initialize(aDsaPrivateKey);

}
public String[] getBlockedHexStringArray() {

	String[] aBlockedHexStringArray = new String[4];
	aBlockedHexStringArray[0] = Ihossv1SignatureSupport.pBlockedHexString(fieldDsaPrivateKey);
	aBlockedHexStringArray[1] = Ihossv1SignatureSupport.qBlockedHexString(fieldDsaPrivateKey);
	aBlockedHexStringArray[2] = Ihossv1SignatureSupport.gBlockedHexString(fieldDsaPrivateKey);
	aBlockedHexStringArray[3] = Ihossv1SignatureSupport.xBlockedHexString(fieldDsaPrivateKey);

	return aBlockedHexStringArray;

}
public DSAPrivateKey getDsaPrivateKey() {

	return fieldDsaPrivateKey;

}
public String getString() {

	String[] blockedHexStringArray = getBlockedHexStringArray();
	String aIhossv1PrivateKeyString = "";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + "// BIG p\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + blockedHexStringArray[0] + ".\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + "// BIG q\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + blockedHexStringArray[1] + ".\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + "// BIG g\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + blockedHexStringArray[2] + ".\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + "// BIG x\r\n";
	aIhossv1PrivateKeyString = aIhossv1PrivateKeyString + blockedHexStringArray[3] + ".\r\n";

	return aIhossv1PrivateKeyString;

}
public void initialize(String[] aBlockedHexStringArray) throws InvalidKeySpecException {

  setBlockedHexStringArray(aBlockedHexStringArray);

}
public void initialize(File aFile) throws IOException, InvalidKeySpecException  {

	read(aFile);

}
public void initialize(String aIhossv1PrivateKeyString) throws IOException, InvalidKeySpecException {

	setString(aIhossv1PrivateKeyString);

}
public void initialize(String pBlockedHexString, String qBlockedHexString, String gBlockedHexString, String xBlockedHexString)
  throws InvalidKeySpecException {

  setDsaPrivateKey(Ihossv1SignatureSupport.dsaPrivateKey(pBlockedHexString, qBlockedHexString, gBlockedHexString, xBlockedHexString));

}
public void initialize(DSAPrivateKey aDsaPrivateKey) {

	setDsaPrivateKey(aDsaPrivateKey);

}
public void read(File aFile) throws IOException, InvalidKeySpecException  {

	setString(Ihossv1MiscellaneousSupport.read(aFile));

}
public void setBlockedHexStringArray(String[] aBlockedHexStringArray) throws InvalidKeySpecException {

  setDsaPrivateKey(
    Ihossv1SignatureSupport.dsaPrivateKey(
      aBlockedHexStringArray[0],
      aBlockedHexStringArray[1],
      aBlockedHexStringArray[2],
      aBlockedHexStringArray[3]));

}
public void setDsaPrivateKey(DSAPrivateKey aDsaPrivateKey) {

	fieldDsaPrivateKey = aDsaPrivateKey;

}
public void setString(String aString) throws IOException, InvalidKeySpecException {

	setBlockedHexStringArray(Ihossv1SignatureSupport.blockedHexStringArray(aString));

}
public String toString() {

	String toString = getClass().getName() + "(\n";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
public void write(File outFile) throws IOException {

  Ihossv1MiscellaneousSupport.overwrite(getString(), outFile);

}
}
