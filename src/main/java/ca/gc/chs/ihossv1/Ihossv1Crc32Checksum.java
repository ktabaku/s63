package ca.gc.chs.ihossv1;

public abstract class Ihossv1Crc32Checksum {

  public static final int STRING_LENGTH = 8;
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;

  private String fieldString;

public byte[] getByteArray() {

	return Ihossv1MiscellaneousSupport.byteArray(getString());

}
public String getString() {

	return fieldString;

}
public void initialize(byte[] aByteArray) {

	setByteArray(aByteArray);

}
public void initialize(String aString) {

	setString(aString);

}
public void setByteArray(byte[] aByteArray) {

	setString(Ihossv1MiscellaneousSupport.hexString(aByteArray));

}
public void setString(String string) {

	fieldString = string;

}
public String toString() {

	String toString = getClass().getName() + "(";
	toString = toString + getString();
	toString = toString + ")\n";

	return toString;

}
}
