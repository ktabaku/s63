package ca.gc.chs.ihossv1;

import java.io.*;
import java.security.spec.*;

public class Ihossv1CellSignatureFile extends Ihossv1File {


public Ihossv1CellSignatureFile() {

	super();

}
public Ihossv1CellSignatureFile(File aFile) {

  initialize(aFile);

}
public void addIhossv1CellSignatureFileEntry(Ihossv1CellSignatureFileEntry anIhossv1CellSignatureFileEntry) throws IOException {

  Ihossv1MiscellaneousSupport.append(anIhossv1CellSignatureFileEntry.getString(), getFile());

}
public void createFile() throws IOException {

  Ihossv1MiscellaneousSupport.overwrite("", getFile());

}
public Ihossv1CellSignatureFileEntry[] getIhossv1SignatureFileEntries() throws IOException, InvalidKeySpecException {

	String[] hexStringArray = Ihossv1SignatureSupport.blockedHexStringArray((Ihossv1MiscellaneousSupport.read(getFile())));
	Ihossv1CellSignatureFileEntry[] Ihossv1CellSignatureFileEntryArray = new Ihossv1CellSignatureFileEntry[hexStringArray.length / 8];
	for (int i = 0; i < hexStringArray.length; i = i + 8) {
		Ihossv1Signature anIhossv1Signature = new Ihossv1Signature(hexStringArray[i], hexStringArray[i + 1]);
		Ihossv1CellSignatureFileEntryArray[i / 8] = 
			new Ihossv1CellSignatureFileEntry(
				hexStringArray[i], 
				hexStringArray[i + 1], 
				hexStringArray[i + 2], 
				hexStringArray[i + 3], 
				hexStringArray[i + 4], 
				hexStringArray[i + 5], 
				hexStringArray[i + 6], 
				hexStringArray[i + 7]); 
	}

	return Ihossv1CellSignatureFileEntryArray;

}
}
