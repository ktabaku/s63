package ca.gc.chs.ihossv1;

import javax.crypto.*;

public class Ihossv1SystemIdentificationKey extends Ihossv1SecretKey {

  public static final int STRING_LENGTH = 32;
  public static final int BYTE_ARRAY_LENGTH = STRING_LENGTH / 2;

public Ihossv1SystemIdentificationKey() {

	super();

}
public Ihossv1SystemIdentificationKey(byte[] aByteArray) {

	this();
	
	initialize(aByteArray);

}
public Ihossv1SystemIdentificationKey(String aString) {

	this();
	
	initialize(aString);

}
public Ihossv1SystemIdentificationKey(SecretKey aSecretKey) {

	this();
	
	initialize(aSecretKey);

}
}
