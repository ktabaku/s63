package ca.gc.chs.ihossv1;

public class Ihossv1CellName {

	public static final int STRING_LENGTH = 8;

	private String fieldString;

public Ihossv1CellName() {

	super();

}
public Ihossv1CellName(String aString) {

	this();

	initialize(aString);

}
public String getCellDataFileName() {

	return getString();

}
public String getCellSignatureFileName() {

	String nameString = getString();

	String namePrefixString = nameString.substring(0, 2);
	String nameNavCodeString = nameString.substring(2, 3);
	String nameSuffixString = nameString.substring(3, 8);

	String signatureFileNamePrefixString = namePrefixString;

	String signatureFileNameNavCodeReplacementString = null;
	if (nameNavCodeString.equals("1"))
		signatureFileNameNavCodeReplacementString = "I";
	if (nameNavCodeString.equals("2"))
		signatureFileNameNavCodeReplacementString = "J";
	if (nameNavCodeString.equals("3"))
		signatureFileNameNavCodeReplacementString = "K";
	if (nameNavCodeString.equals("4"))
		signatureFileNameNavCodeReplacementString = "L";
	if (nameNavCodeString.equals("5"))
		signatureFileNameNavCodeReplacementString = "M";
	if (nameNavCodeString.equals("6"))
		signatureFileNameNavCodeReplacementString = "N";

	String signatureFileNameSuffixString = nameSuffixString;

	String signatureFileNameString = 
		signatureFileNamePrefixString + signatureFileNameNavCodeReplacementString + signatureFileNameSuffixString; 

	return signatureFileNameString;

}
public String getString() {

	return fieldString;

}
public void initialize(String aString) {

  setString(aString);

}
public void setString(String string) {

  fieldString = string;

}
public String toString() {

  String toString = getClass().getName() + "(";
  toString = toString + getString();
  toString = toString + ")\n";

  return toString;

}
}
