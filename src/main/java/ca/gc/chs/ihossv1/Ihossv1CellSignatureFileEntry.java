package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.spec.*;

public class Ihossv1CellSignatureFileEntry {

  Ihossv1Signature fieldIhossv1Signature;
  Ihossv1Certificate fieldIhossv1Certificate;

public Ihossv1CellSignatureFileEntry() {

	super();

}
public Ihossv1CellSignatureFileEntry(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

	this();
	initialize(aBlockedHexStringArray);

}
public Ihossv1CellSignatureFileEntry(Ihossv1Signature aIhossv1Signature, Ihossv1Certificate aIhossv1Certificate) {

	this();
	initialize(aIhossv1Signature, aIhossv1Certificate);

}
public Ihossv1CellSignatureFileEntry(String aCellSignatureString) throws IOException, InvalidKeySpecException  {

	this();
	initialize(aCellSignatureString);

}
public Ihossv1CellSignatureFileEntry(
  String fileRBlockedHexString,
  String fileSBlockedHexString,
  String certificateRBlockedHexString,
  String certifiicateSBlockedHexString,
  String certifiicatePBlockedHexString,
  String certifiicateQBlockedHexString,
  String certifiicateGBlockedHexString,
  String certifiicateYBlockedHexString)
  throws IOException, InvalidKeySpecException {

  this();
  initialize(
    fileRBlockedHexString,
    fileSBlockedHexString,
    certificateRBlockedHexString,
    certifiicateSBlockedHexString,
    certifiicatePBlockedHexString,
    certifiicateQBlockedHexString,
    certifiicateGBlockedHexString,
    certifiicateYBlockedHexString);

}
public String[] getBlockedHexStringArray() throws IOException {

	String[] aIhossv1FileSignatureEntryHexStringArray = new String[6];
	System.arraycopy(fieldIhossv1Signature.getBlockedHexStringArray(), 0, aIhossv1FileSignatureEntryHexStringArray, 0, 2);
	System.arraycopy(fieldIhossv1Certificate.getBlockedHexStringArray(), 0, aIhossv1FileSignatureEntryHexStringArray, 2, 4);

	return aIhossv1FileSignatureEntryHexStringArray;

}
public Ihossv1Certificate getIhossv1Certificate() {

	return fieldIhossv1Certificate;

}
public Ihossv1Signature getIhossv1Signature() {

	return fieldIhossv1Signature;

}
public String getString() throws IOException {

	return fieldIhossv1Signature.getString() + fieldIhossv1Certificate.getString();

}
public void initialize(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

	setBlockedHexStringArray(aBlockedHexStringArray);

}
public void initialize(Ihossv1Signature aIhossv1Signature, Ihossv1Certificate aIhossv1Certificate) {

	setIhossv1Signature(aIhossv1Signature);
	setIhossv1Certificate(aIhossv1Certificate);

}
public void initialize(String aCellSignatureString) throws IOException, InvalidKeySpecException  {

	setString(aCellSignatureString);

}
public void initialize(
	String fileRBlockedHexString, 
	String fileSBlockedHexString, 
	String certificateRBlockedHexString, 
	String certifiicateSBlockedHexString, 
	String certifiicatePBlockedHexString, 
	String certifiicateQBlockedHexString, 
	String certifiicateGBlockedHexString, 
	String certifiicateYBlockedHexString) throws IOException, InvalidKeySpecException {

	setIhossv1Signature(new Ihossv1Signature(fileRBlockedHexString, fileSBlockedHexString));
	setIhossv1Certificate(
		new Ihossv1Certificate(
			certificateRBlockedHexString, 
			certifiicateSBlockedHexString, 
			certifiicatePBlockedHexString, 
			certifiicateQBlockedHexString, 
			certifiicateGBlockedHexString, 
			certifiicateYBlockedHexString)); 

}
public void setBlockedHexStringArray(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

	String[] aIhossv1SignatureHexStringArray = new String[2];
	System.arraycopy(aBlockedHexStringArray, 0, aIhossv1SignatureHexStringArray, 0, 2);
	setIhossv1Signature(new Ihossv1Signature(aIhossv1SignatureHexStringArray));

	String[] aIhossv1Ihossv1CertificateHexStringArray = new String[6];
	System.arraycopy(aBlockedHexStringArray, 2, aIhossv1Ihossv1CertificateHexStringArray, 0, 6);
	setIhossv1Certificate(new Ihossv1Certificate(aIhossv1Ihossv1CertificateHexStringArray));

}
public void setIhossv1Certificate(Ihossv1Certificate aIhossv1Certificate) {

	fieldIhossv1Certificate = aIhossv1Certificate;

}
public void setIhossv1Signature(Ihossv1Signature aIhossv1Signature) {

	fieldIhossv1Signature = aIhossv1Signature;

}
public void setString(String aString) throws IOException, InvalidKeySpecException {

	setBlockedHexStringArray(Ihossv1SignatureSupport.blockedHexStringArray(aString));

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
}
