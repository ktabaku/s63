package ca.gc.chs.ihossv1;

import javax.crypto.*;

public class Ihossv1ManufacturerKey extends Ihossv1SecretKey {

  public static final int STRING_LENGTH = 10;
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;

public Ihossv1ManufacturerKey() {

	super();

}
public Ihossv1ManufacturerKey(byte[] aByteArray) {

	this();
	
	initialize(aByteArray);

}
public Ihossv1ManufacturerKey(String aString) {

	this();
	
	initialize(aString);

}
public Ihossv1ManufacturerKey(SecretKey aSecretKey) {

	this();
	
	initialize(aSecretKey);

}
}
