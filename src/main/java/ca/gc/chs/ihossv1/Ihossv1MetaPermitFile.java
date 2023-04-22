package ca.gc.chs.ihossv1;

import java.io.*;
import java.text.*;

public class Ihossv1MetaPermitFile extends Ihossv1File {


public Ihossv1MetaPermitFile() {

	super();

}
public Ihossv1MetaPermitFile(File aFile) {

  initialize(aFile);

}
public void addEcsIhossv1MetaPermitFileEntry(Ihossv1MetaPermitFileEntry anIhossv1MetaPermitFileEntry) throws IOException {

  Ihossv1MiscellaneousSupport.append(anIhossv1MetaPermitFileEntry.getString() + "\r\n", getFile());

}
public void addEncIhossv1MetaPermitFileEntry(Ihossv1MetaPermitFileEntry anIhossv1MetaPermitFileEntry) throws IOException {

  String oldString = Ihossv1MiscellaneousSupport.read(getFile());
  int cut = oldString.indexOf(":ECS\r\n");
  String newStartString = oldString.substring(0, cut);
  String newMiddleString = anIhossv1MetaPermitFileEntry.getString() + "\r\n";
  String newEndString = oldString.substring(cut);
  String newString = newStartString + newMiddleString + newEndString;

  Ihossv1MiscellaneousSupport.overwrite(newString, getFile());

}
public void createFile(Ihossv1MetaPermitFileDate anIhossv1MetaPermitFileDate) throws IOException {

  String aString = "";
  aString = aString + ":DATE " + anIhossv1MetaPermitFileDate.getString() + "\r\n";
  aString = aString + ":VERSION 1\r\n";
  aString = aString + ":ENC\r\n";
  aString = aString + ":ECS\r\n";

  Ihossv1MiscellaneousSupport.overwrite(aString, getFile());

}
public Ihossv1MetaPermitFileEntry[] getEcsIhossv1MetaPermitFileEntries() throws IOException, ParseException {

  String aString = Ihossv1MiscellaneousSupport.read(getFile());

  if (aString == null) {
    return null;
  }

  int startPostion = aString.indexOf(":ECS\r\n") + 6;
  String ecsPermitsString = aString.substring(startPostion);

  String[] ecsPermitsStringArray = Ihossv1MiscellaneousSupport.stringArray(ecsPermitsString);

  Ihossv1MetaPermitFileEntry[] ihossv1MetaPermitFileEntryArray = new Ihossv1MetaPermitFileEntry[ecsPermitsStringArray.length];
  for (int i = 0; i < ecsPermitsStringArray.length; i++) {
    ihossv1MetaPermitFileEntryArray[i] = new Ihossv1MetaPermitFileEntry(ecsPermitsStringArray[i]);
  }

  return ihossv1MetaPermitFileEntryArray;

}
public Ihossv1MetaPermitFileEntry[] getEncIhossv1MetaPermitFileEntries() throws IOException, ParseException {

  String aString = Ihossv1MiscellaneousSupport.read(getFile());

  if (aString == null) {
    return null;
  }

  int startPosition = aString.indexOf(":ENC\r\n") + 6;
  int endPosition = aString.indexOf(":ECS\r\n");
  if(endPosition==-1) endPosition = aString.indexOf(":ECS");
  String ecsPermitsString = aString.substring(startPosition, endPosition);

  String[] ecsPermitsStringArray = Ihossv1MiscellaneousSupport.stringArray(ecsPermitsString);

  Ihossv1MetaPermitFileEntry[] ihossv1MetaPermitFileEntryArray = new Ihossv1MetaPermitFileEntry[ecsPermitsStringArray.length];
	for (int i = 0; i < ecsPermitsStringArray.length; i++) {
		try {
			ihossv1MetaPermitFileEntryArray[i] = new Ihossv1MetaPermitFileEntry(ecsPermitsStringArray[i]);
		} catch (Exception e) {
			Ihossv1MetaPermitFileEntry incorrectIhossv1MetaPermitFileEntry = new Ihossv1MetaPermitFileEntry();
			incorrectIhossv1MetaPermitFileEntry.setComment(ecsPermitsStringArray[i] + ";" + e.getMessage());
			ihossv1MetaPermitFileEntryArray[i] = incorrectIhossv1MetaPermitFileEntry;
		}
	}

	return ihossv1MetaPermitFileEntryArray;

}
public Ihossv1MetaPermitFileDate getIhossv1MetaPermitFileDate() throws IOException, ParseException {

  String metaPermitFileString = Ihossv1MiscellaneousSupport.read(getFile());
  String ihossv1MetaPermitFileDateString = metaPermitFileString.substring(6, metaPermitFileString.indexOf("\r\n"));
  Ihossv1MetaPermitFileDate anIhossv1MetaPermitFileDate = new Ihossv1MetaPermitFileDate (ihossv1MetaPermitFileDateString);

  return anIhossv1MetaPermitFileDate;

}
}
