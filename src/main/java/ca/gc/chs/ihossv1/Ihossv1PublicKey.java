package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.interfaces.*;
import java.security.spec.*;

public class Ihossv1PublicKey {

  DSAPublicKey fieldDsaPublicKey;

public Ihossv1PublicKey() {

	super();

}
public Ihossv1PublicKey(String[] aBlockedHexStringArray) throws InvalidKeySpecException {

	this();
	initialize(aBlockedHexStringArray);

}
public Ihossv1PublicKey(File aFile) throws InvalidKeySpecException, IOException {

	this();
	initialize(aFile);

}
public Ihossv1PublicKey(String aDigitalSignaturePublicKeyString) throws InvalidKeySpecException, IOException {

	this();
	initialize(aDigitalSignaturePublicKeyString);

}
public Ihossv1PublicKey(String pBlockedHexString, String qBlockedHexString, String gBlockedHexString, String yBlockedHexString) throws InvalidKeySpecException {

	this();
	initialize(pBlockedHexString, qBlockedHexString, gBlockedHexString, yBlockedHexString);

}
public Ihossv1PublicKey(DSAPublicKey aDsaPublicKey) {

	this();
	initialize(aDsaPublicKey);

}
public String[] getBlockedHexStringArray() {

	String[] aBlockedHexStringArray = new String[4];
	aBlockedHexStringArray[0] = Ihossv1SignatureSupport.pBlockedHexString(fieldDsaPublicKey);
	aBlockedHexStringArray[1] = Ihossv1SignatureSupport.qBlockedHexString(fieldDsaPublicKey);
	aBlockedHexStringArray[2] = Ihossv1SignatureSupport.gBlockedHexString(fieldDsaPublicKey);
	aBlockedHexStringArray[3] = Ihossv1SignatureSupport.yBlockedHexString(fieldDsaPublicKey);

	return aBlockedHexStringArray;

}
public DSAPublicKey getDsaPublicKey() {

	return fieldDsaPublicKey;

}
public String getString() {

	String[] blockedHexStringArray = getBlockedHexStringArray();
	String aIhossv1PublicKeyString = "";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + "// BIG p\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + blockedHexStringArray[0] + ".\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + "// BIG q\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + blockedHexStringArray[1] + ".\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + "// BIG g\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + blockedHexStringArray[2] + ".\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + "// BIG y\r\n";
	aIhossv1PublicKeyString = aIhossv1PublicKeyString + blockedHexStringArray[3] + ".\r\n";

	return aIhossv1PublicKeyString;

}
public void initialize(String[] aBlockedHexStringArray) throws InvalidKeySpecException{

	setBlockedHexStringArray(aBlockedHexStringArray);

}
public void initialize(File aFile) throws InvalidKeySpecException, IOException  {

	read(aFile);

}
public void initialize(String aIhossv1PublicKeyString) throws InvalidKeySpecException, IOException  {

	setString(aIhossv1PublicKeyString);

}
public void initialize(String pBlockedHexString, String qBlockedHexString, String gBlockedHexString, String yBlockedHexString) throws InvalidKeySpecException {

	setDsaPublicKey(Ihossv1SignatureSupport.dsaPublicKey(pBlockedHexString, qBlockedHexString, gBlockedHexString, yBlockedHexString));

}
public void initialize(DSAPublicKey aDsaPublicKey) {

	setDsaPublicKey(aDsaPublicKey);

}
public void read(File aFile) throws InvalidKeySpecException, IOException {

	setString(Ihossv1MiscellaneousSupport.read(aFile));

}
public void setBlockedHexStringArray(String[] aBlockedHexStringArray) throws InvalidKeySpecException {

	setDsaPublicKey(
		Ihossv1SignatureSupport.dsaPublicKey(
			aBlockedHexStringArray[0], 
			aBlockedHexStringArray[1], 
			aBlockedHexStringArray[2], 
			aBlockedHexStringArray[3])); 

}
public void setDsaPublicKey(DSAPublicKey aDsaPublicKey) {

	fieldDsaPublicKey = aDsaPublicKey;

}
public void setString(String aString) throws InvalidKeySpecException, IOException {

	setBlockedHexStringArray(Ihossv1SignatureSupport.blockedHexStringArray(aString));

}
public String toString() {

	String toString = getClass().getName() + "(\n";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
public void write(File outFile) throws InvalidKeySpecException, IOException {

	Ihossv1MiscellaneousSupport.overwrite(getString(), outFile);

}
}
