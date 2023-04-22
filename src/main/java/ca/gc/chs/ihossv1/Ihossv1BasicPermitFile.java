package ca.gc.chs.ihossv1;

import java.io.*;
import java.text.*;

public class Ihossv1BasicPermitFile extends Ihossv1File {


public Ihossv1BasicPermitFile() {

	super();

}
public Ihossv1BasicPermitFile(File aFile) {

  initialize(aFile);

}
public void addIhossv1BasicPermitFileEntry(Ihossv1BasicPermitFileEntry anIhossv1BasicPermitFileEntry) throws IOException {

  Ihossv1MiscellaneousSupport.append(anIhossv1BasicPermitFileEntry.getString() + "\r\n", getFile());

}
public void createFile() throws IOException {

  Ihossv1MiscellaneousSupport.overwrite("", getFile());

}
public Ihossv1BasicPermitFileEntry[] getIhossv1BasicPermitFileEntries() throws IOException, ParseException {

  String aString = Ihossv1MiscellaneousSupport.read(getFile());
  if (aString != null) {
    String[] aStringArray = Ihossv1MiscellaneousSupport.stringArray(aString);
    Ihossv1BasicPermitFileEntry[] ihossv1BasicPermitFileEntryArray = new Ihossv1BasicPermitFileEntry[aStringArray.length];
    for (int i = 0; i < aStringArray.length; i++) {
      ihossv1BasicPermitFileEntryArray[i] = new Ihossv1BasicPermitFileEntry(aStringArray[i]);
    }
    return ihossv1BasicPermitFileEntryArray;
  }

  return null;

}
}
