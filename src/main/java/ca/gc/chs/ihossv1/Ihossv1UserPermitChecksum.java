package ca.gc.chs.ihossv1;

public class Ihossv1UserPermitChecksum extends Ihossv1Crc32Checksum {

public Ihossv1UserPermitChecksum() {

	super();

}
public Ihossv1UserPermitChecksum(byte[] aByteArray) {

	this();
	
	initialize(aByteArray);

}
public Ihossv1UserPermitChecksum(Ihossv1HardwareId aIhossv1HardwareId) {

	this();

	initialize(aIhossv1HardwareId);

}
public Ihossv1UserPermitChecksum(String aString) {

	this();

	initialize(aString);

}
public void calculate(Ihossv1HardwareId aIhossv1HardwareId) {

	setString(Ihossv1MiscellaneousSupport.hexString(Ihossv1ChecksumSupport.computeCrc32Checksum(aIhossv1HardwareId.getEncryptedString())));

}
public void initialize(Ihossv1HardwareId aIhossv1HardwareId) {

	calculate(aIhossv1HardwareId);

}
public void verify(Ihossv1HardwareId aIhossv1HardwareId) throws Ihossv1UserPermitChecksumException {

  if (!getString()
    .equals(
      Ihossv1MiscellaneousSupport.hexString(Ihossv1ChecksumSupport.computeCrc32Checksum(aIhossv1HardwareId.getEncryptedString())))) {
    throw (new Ihossv1UserPermitChecksumException());
  }

}
}
