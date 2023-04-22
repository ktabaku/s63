package ca.gc.chs.ihossv1;

public class Ihossv1CellSignatureFileEntryVerification {

  boolean fieldIhossv1CellDataFileVerification;
  boolean fieldIhossv1CertificateVerification;

public Ihossv1CellSignatureFileEntryVerification() {

	super();

}
public Ihossv1CellSignatureFileEntryVerification(boolean aFileVerification, boolean aCertificateVerification) {

	initialize(aFileVerification, aCertificateVerification);

}
public boolean getIhossv1CellDataFileVerification() {

	return fieldIhossv1CellDataFileVerification;

}
public boolean getIhossv1CertificateVerification() {

	return fieldIhossv1CertificateVerification;

}
public void initialize(boolean aIhossv1CellDataFileVerification, boolean aIhossv1CertificateVerification) {

	fieldIhossv1CellDataFileVerification = aIhossv1CellDataFileVerification;
	fieldIhossv1CertificateVerification = aIhossv1CertificateVerification;

}
public void setIhossv1CellDataFileVerification(boolean aIhossv1CellDataFileVerification) {

	fieldIhossv1CellDataFileVerification = aIhossv1CellDataFileVerification;

}
public void setIhossv1CertificateVerification(boolean aIhossv1CertificateVerification) {

	fieldIhossv1CertificateVerification = aIhossv1CertificateVerification;

}
public String toString() {

	String toString = getClass().getName() + "(\n";
	toString = toString + "File Verifies: " + (new Boolean(fieldIhossv1CellDataFileVerification)).toString() + "\n";
	toString = toString + "Certificate Verifies: " + (new Boolean(fieldIhossv1CertificateVerification)).toString() + "\n";
	toString = toString + ")\n";

	return toString;

}
}
