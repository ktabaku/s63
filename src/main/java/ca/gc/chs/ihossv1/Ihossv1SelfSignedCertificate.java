package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Ihossv1SelfSignedCertificate extends Ihossv1Certificate {

public Ihossv1SelfSignedCertificate() {

	super();

}
public Ihossv1SelfSignedCertificate(String[] aBlockedHexStringArray) throws IOException, InvalidKeySpecException {

	super(aBlockedHexStringArray);

}
public Ihossv1SelfSignedCertificate(Ihossv1PrivateKey aIhossv1PrivateKey, Ihossv1PublicKey aIhossv1PublicKey)
  throws InvalidKeyException, SignatureException {

  super(aIhossv1PrivateKey, aIhossv1PublicKey);

}
public Ihossv1SelfSignedCertificate(Ihossv1PublicKey aIhossv1PublicKey) {

	super(aIhossv1PublicKey);

}
public Ihossv1SelfSignedCertificate(Ihossv1PublicKey aIhossv1PublicKey, Ihossv1Signature aIhossv1Signature) {

	super(aIhossv1PublicKey, aIhossv1Signature);

}
public Ihossv1SelfSignedCertificate(File aFile) throws IOException, InvalidKeySpecException  {

	super(aFile);

}
public Ihossv1SelfSignedCertificate(String aDigitalSignaturePublicKeyString) throws IOException, InvalidKeySpecException {

	super(aDigitalSignaturePublicKeyString);

}
public Ihossv1SelfSignedCertificate(
  String rBlockedHexString,
  String sBlockedHexString,
  String pBlockedHexString,
  String qBlockedHexString,
  String gBlockedHexString,
  String yBlockedHexString)
  throws IOException, InvalidKeySpecException {

  super(rBlockedHexString, sBlockedHexString, pBlockedHexString, qBlockedHexString, gBlockedHexString, yBlockedHexString);

}
public boolean verify() throws InvalidKeyException, SignatureException {

  return verify(getIhossv1PublicKey());

}
}
