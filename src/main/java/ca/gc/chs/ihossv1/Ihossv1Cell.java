package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.*;
import java.security.spec.*;

import javax.crypto.SecretKey;

public class Ihossv1Cell {

	private File fieldDirectory;
	private Ihossv1CellName fieldIhossv1CellName;
	private String fieldExtension;

	public Ihossv1Cell() {

		super();

	}

	public Ihossv1Cell(File aDirectory, Ihossv1CellName aIhossv1CellName, String anExtension) {

		initialize(aDirectory, aIhossv1CellName, anExtension);

	}

	public void createSignatureFile() throws IOException {

		getIhossv1CellSignatureFile().createFile();

	}

	public Ihossv1Cell decrypt(File destinationDirectory, Ihossv1CellKey aIhossv1CellKey)
			throws IOException, SecurityException, InvalidKeyException {

		Ihossv1Cell destinationIhossv1File = new Ihossv1Cell(destinationDirectory, getIhossv1CellName(),
				getExtension());
		Ihossv1EncryptionSupport.decryptBlowfishThenDecompressZip(getIhossv1CellDataFile().getFile(),
				destinationIhossv1File.getIhossv1CellDataFile().getFile(), aIhossv1CellKey.getSecretKey());

		return destinationIhossv1File;

	}

	public Ihossv1Cell decrypt(File destinationDirectory, Ihossv1CellKey aIhossv1CellKey, SecretKey outputSecretKey, String extensionPrefix)
			throws IOException, SecurityException, InvalidKeyException {

		Ihossv1Cell destinationIhossv1File = new Ihossv1Cell(destinationDirectory, getIhossv1CellName(),
				extensionPrefix+getExtension());
		Ihossv1EncryptionSupport.decryptBlowfishThenDecompressZip(getIhossv1CellDataFile().getFile(),
				destinationIhossv1File.getIhossv1CellDataFile().getFile(), aIhossv1CellKey.getSecretKey(),
				outputSecretKey);

		return destinationIhossv1File;

	}

	public Ihossv1Cell encrypt(File destinationDirectory, Ihossv1CellKey aIhossv1CellKey)
			throws IOException, SecurityException, InvalidKeyException {

		Ihossv1Cell destinationIhossv1File = new Ihossv1Cell(destinationDirectory, getIhossv1CellName(),
				getExtension());
		Ihossv1EncryptionSupport.compressZipThenEncryptBlowfish(getIhossv1CellDataFile().getFile(),
				destinationIhossv1File.getIhossv1CellDataFile().getFile(), aIhossv1CellKey.getSecretKey());

		return destinationIhossv1File;

	}

	public File getDirectory() {

		return fieldDirectory;

	}

	public String getExtension() {

		return fieldExtension;

	}

	public Ihossv1CellDataFile getIhossv1CellDataFile() {

		return new Ihossv1CellDataFile(
				new File(getDirectory(), getIhossv1CellName().getCellDataFileName() + "." + getExtension()));

	}

	public Ihossv1CellName getIhossv1CellName() {

		return fieldIhossv1CellName;

	}

	public Ihossv1CellSignatureFile getIhossv1CellSignatureFile() {

		return new Ihossv1CellSignatureFile(
				new File(getDirectory(), getIhossv1CellName().getCellSignatureFileName() + "." + getExtension()));

	}

	public void initialize(File aDirectory, Ihossv1CellName aIhossv1CellName, String anExtension) {

		fieldDirectory = aDirectory;
		fieldIhossv1CellName = aIhossv1CellName;
		fieldExtension = anExtension;

	}

	public void setDirectory(File aDirectory) {

		fieldDirectory = aDirectory;

	}

	public void setExtension(String anExtension) {

		fieldExtension = anExtension;

	}

	public void setIhossv1CellName(Ihossv1CellName aIhossv1CellName) {

		fieldIhossv1CellName = aIhossv1CellName;

	}

	public void sign(Ihossv1PrivateKey aIhossv1PrivateKey, Ihossv1Certificate aIhossv1Certificate)
			throws InvalidKeyException, SignatureException, IOException {

		Ihossv1CellSignatureFileEntry aIhossv1CellSignatureFileEntry = new Ihossv1CellSignatureFileEntry(
				getIhossv1CellDataFile().sign(aIhossv1PrivateKey), aIhossv1Certificate);
		getIhossv1CellSignatureFile().addIhossv1CellSignatureFileEntry(aIhossv1CellSignatureFileEntry);

	}

	public String toString() {

		String toString = getClass().getName() + "(";
		toString = toString + getIhossv1CellDataFile().getFile().toString();
		toString = toString + ")\n";

		return toString;

	}

	public Ihossv1CellSignatureFileEntryVerification[] verify(Ihossv1PublicKey administratorPublicKey)
			throws IOException, InvalidKeySpecException, InvalidKeyException, SignatureException, IOException {

		Ihossv1CellSignatureFileEntry[] theIhossv1SignatureFileEntries = getIhossv1CellSignatureFile()
				.getIhossv1SignatureFileEntries();
		Ihossv1CellSignatureFileEntryVerification[] theIhossv1CellSignatureFileEntryVerifications = new Ihossv1CellSignatureFileEntryVerification[theIhossv1SignatureFileEntries.length];

		for (int i = 0; i < theIhossv1SignatureFileEntries.length; i++) {
			Ihossv1CellSignatureFileEntry aIhossv1CellSignatureFileEntry = theIhossv1SignatureFileEntries[i];
			Ihossv1Signature aIhossv1Signature = aIhossv1CellSignatureFileEntry.getIhossv1Signature();
			Ihossv1Certificate aIhossv1Certificate = aIhossv1CellSignatureFileEntry.getIhossv1Certificate();
			Ihossv1PublicKey aIhossv1PublicKey = aIhossv1Certificate.getIhossv1PublicKey();
			Ihossv1CellSignatureFileEntryVerification aIhossv1CellSignatureFileEntryVerification = new Ihossv1CellSignatureFileEntryVerification();
			aIhossv1CellSignatureFileEntryVerification.setIhossv1CellDataFileVerification(
					getIhossv1CellDataFile().verify(aIhossv1Signature, aIhossv1PublicKey));
			aIhossv1CellSignatureFileEntryVerification
					.setIhossv1CertificateVerification(aIhossv1Certificate.verify(administratorPublicKey));
			theIhossv1CellSignatureFileEntryVerifications[i] = aIhossv1CellSignatureFileEntryVerification;
		}

		return theIhossv1CellSignatureFileEntryVerifications;

	}
}
