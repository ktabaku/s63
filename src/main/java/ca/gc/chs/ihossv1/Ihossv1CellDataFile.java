package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.*;

public class Ihossv1CellDataFile extends Ihossv1File {

public Ihossv1CellDataFile() {

	super();

}
public Ihossv1CellDataFile(File aFile) {

	initialize(aFile);

}
public Ihossv1Signature sign(Ihossv1PrivateKey anIhossv1PrivateKey) throws InvalidKeyException, SignatureException, IOException {

  return new Ihossv1Signature(Ihossv1SignatureSupport.signSha1WithDsa(getFile(), anIhossv1PrivateKey.getDsaPrivateKey()));

}
public boolean verify(Ihossv1Signature anIhossv1Signature, Ihossv1PublicKey anIhossv1PublicKey)
  throws InvalidKeyException, SignatureException, IOException {

  return Ihossv1SignatureSupport.verifySha1WithDsa(getFile(), anIhossv1Signature.getByteArray(), anIhossv1PublicKey.getDsaPublicKey());

}
}
