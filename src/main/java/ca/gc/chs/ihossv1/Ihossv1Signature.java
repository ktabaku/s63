package ca.gc.chs.ihossv1;

import java.security.interfaces.*;
import java.io.*;

public class Ihossv1Signature {

  byte[] fieldByteArray;

public Ihossv1Signature() {

	super();

}
public Ihossv1Signature(byte[] aByteArray) {

	this();
	initialize(aByteArray);

}
public Ihossv1Signature(String[] aBlockedHexStringArray) throws IOException {

  this();
  initialize(aBlockedHexStringArray);

}
public Ihossv1Signature(String aString) throws IOException {

  this();
  initialize(aString);

}
public Ihossv1Signature(String rBlockedHexString, String sBlockedHexString) throws IOException {

  this();
  initialize(rBlockedHexString, sBlockedHexString);

}
public String[] getBlockedHexStringArray() throws IOException {

	String[] aBlockedHexStringArray = new String[2];
	aBlockedHexStringArray[0] = Ihossv1SignatureSupport.rBlockedHexString(fieldByteArray);
	aBlockedHexStringArray[1] = Ihossv1SignatureSupport.sBlockedHexString(fieldByteArray);

	return aBlockedHexStringArray;

}
public byte[] getByteArray() {

	return fieldByteArray;

}
public String getString() throws IOException {

	String[] blockedHexStringArray = getBlockedHexStringArray();
	String aIhossv1SignatureString = "";
	aIhossv1SignatureString = aIhossv1SignatureString + "// Signature part R:\r\n";
	aIhossv1SignatureString = aIhossv1SignatureString + blockedHexStringArray[0] + ".\r\n";
	aIhossv1SignatureString = aIhossv1SignatureString + "// Signature part S:\r\n";
	aIhossv1SignatureString = aIhossv1SignatureString + blockedHexStringArray[1] + ".\r\n";

	return aIhossv1SignatureString;

}
public void initialize(byte[] aByteArray) {

	setByteArray(aByteArray);

}
public void initialize(String[] aBlockedHexStringArray) throws IOException {

  setBlockedHexStringArray(aBlockedHexStringArray);

}
public void initialize(String aString) throws IOException {

  setString(aString);

}
public void initialize(String rBlockedHexString, String sBlockedHexString) throws IOException {

  setByteArray(Ihossv1SignatureSupport.derEncodedByteArray(rBlockedHexString, sBlockedHexString));

}
public void setBlockedHexStringArray(String[] aBlockedHexStringArray) throws IOException {

  setByteArray(Ihossv1SignatureSupport.derEncodedByteArray(aBlockedHexStringArray[0], aBlockedHexStringArray[1]));

}
public void setByteArray(byte[] aByteArray) {

	fieldByteArray = aByteArray;

}
public void setString(String aString) throws IOException {

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
