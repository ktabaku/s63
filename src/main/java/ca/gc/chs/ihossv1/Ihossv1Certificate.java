package ca.gc.chs.ihossv1;

import java.security.interfaces.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Ihossv1Certificate {

  Ihossv1PublicKey fieldIhossv1PublicKey;
  Ihossv1Signature fieldIhossv1Signature;

public Ihossv1Certificate() {

	super();

}
public Ihossv1Certificate(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException { 

	this();
	initialize(aBlockedHexStringArray);

}
public Ihossv1Certificate(Ihossv1PrivateKey aIhossv1PrivateKey, Ihossv1PublicKey aIhossv1PublicKey)
  throws InvalidKeyException, SignatureException {

  this();
  initialize(aIhossv1PublicKey);
  sign(aIhossv1PrivateKey);

}
public Ihossv1Certificate(Ihossv1PublicKey aIhossv1PublicKey) {

	this();
	initialize(aIhossv1PublicKey);

}
public Ihossv1Certificate(Ihossv1PublicKey aIhossv1PublicKey, Ihossv1Signature aIhossv1Signature) {

	this();
	initialize(aIhossv1PublicKey, aIhossv1Signature);

}
public Ihossv1Certificate(File aFile) throws IOException, InvalidKeySpecException {

	this();
	initialize(aFile);

}
public Ihossv1Certificate(String aDigitalSignaturePublicKeyString) throws IOException, InvalidKeySpecException {

	this();
	initialize(aDigitalSignaturePublicKeyString);

}
public Ihossv1Certificate(
  String rBlockedHexString,
  String sBlockedHexString,
  String pBlockedHexString,
  String qBlockedHexString,
  String gBlockedHexString,
  String yBlockedHexString)
  throws IOException, InvalidKeySpecException {

  this();
  initialize(rBlockedHexString, sBlockedHexString, pBlockedHexString, qBlockedHexString, gBlockedHexString, yBlockedHexString);

}
public String[] getBlockedHexStringArray() throws IOException {

	String[] aIhossv1CertificateBlockedHexStringArray = new String[6];
	System.arraycopy(fieldIhossv1Signature.getBlockedHexStringArray(), 0, aIhossv1CertificateBlockedHexStringArray, 0, 2);
	System.arraycopy(fieldIhossv1PublicKey.getBlockedHexStringArray(), 0, aIhossv1CertificateBlockedHexStringArray, 2, 4);

	return aIhossv1CertificateBlockedHexStringArray;

}
public Ihossv1PublicKey getIhossv1PublicKey() {

	return fieldIhossv1PublicKey;

}
public Ihossv1Signature getIhossv1Signature() {

	return fieldIhossv1Signature;

}
public String getString() throws IOException {

	return fieldIhossv1Signature.getString() + fieldIhossv1PublicKey.getString();

}
public void initialize(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

	setBlockedHexStringArray(aBlockedHexStringArray);

}
public void initialize(Ihossv1PublicKey aIhossv1PublicKey) {

	setIhossv1PublicKey(aIhossv1PublicKey);

}
public void initialize(Ihossv1PublicKey aIhossv1PublicKey, Ihossv1Signature aIhossv1Signature) {

	setIhossv1PublicKey(aIhossv1PublicKey);
	setIhossv1Signature(aIhossv1Signature);

}
public void initialize(File aFile) throws IOException, InvalidKeySpecException {

  read(aFile);

}
public void initialize(String aIhossv1PrivateKeyString) throws IOException, InvalidKeySpecException {

	setString(aIhossv1PrivateKeyString);

}
public void initialize(
  String rBlockedHexString,
  String sBlockedHexString,
  String pBlockedHexString,
  String qBlockedHexString,
  String gBlockedHexString,
  String yBlockedHexString)
  throws IOException, InvalidKeySpecException {

  setIhossv1Signature(new Ihossv1Signature(rBlockedHexString, sBlockedHexString));
  setIhossv1PublicKey(new Ihossv1PublicKey(pBlockedHexString, qBlockedHexString, gBlockedHexString, yBlockedHexString));

}
public void read(File aFile) throws IOException, InvalidKeySpecException {

	setString(Ihossv1MiscellaneousSupport.read(aFile));

}
public void setBlockedHexStringArray(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

  String[] aIhossv1SignatureHexStringArray = new String[2];
  System.arraycopy(aBlockedHexStringArray, 0, aIhossv1SignatureHexStringArray, 0, 2);
  setIhossv1Signature(new Ihossv1Signature(aIhossv1SignatureHexStringArray));

  String[] aIhossv1PublicKeyHexStringArray = new String[4];
  System.arraycopy(aBlockedHexStringArray, 2, aIhossv1PublicKeyHexStringArray, 0, 4);
  setIhossv1PublicKey(new Ihossv1PublicKey(aIhossv1PublicKeyHexStringArray));

}
public void setIhossv1PublicKey(Ihossv1PublicKey aIhossv1PublicKey) {

	fieldIhossv1PublicKey = aIhossv1PublicKey;

}
public void setIhossv1Signature(Ihossv1Signature aIhossv1Signature) {

	fieldIhossv1Signature = aIhossv1Signature;

}
public void setString(String aString) throws IOException, InvalidKeySpecException {

	setBlockedHexStringArray(Ihossv1SignatureSupport.blockedHexStringArray(aString));

}
public void sign(Ihossv1PrivateKey aIhossv1PrivateKey) throws InvalidKeyException, SignatureException {

  fieldIhossv1Signature =
    new Ihossv1Signature(
      Ihossv1SignatureSupport.signSha1WithDsa(fieldIhossv1PublicKey.getString(), aIhossv1PrivateKey.getDsaPrivateKey()));

}
public String toString() {

  String toString = getClass().getName() + "(\n";
  try {
    toString = toString + getString();
  } catch (Exception e) {
    e.printStackTrace();
    toString = toString + "Unexpected exception occurred in toString() method.  See stack trace.";
  }
  toString = toString + ")\n";

  return toString;

}
public boolean verify(Ihossv1PublicKey aIhossv1PublicKey) throws InvalidKeyException, SignatureException {

  return Ihossv1SignatureSupport.verifySha1WithDsa(
    fieldIhossv1PublicKey.getString(),
    fieldIhossv1Signature.getByteArray(),
    aIhossv1PublicKey.getDsaPublicKey());

}
public void write(File outFile) throws IOException {

	Ihossv1MiscellaneousSupport.overwrite(getString(), outFile);

}
}
