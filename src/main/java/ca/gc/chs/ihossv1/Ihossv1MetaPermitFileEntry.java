package ca.gc.chs.ihossv1;

import java.text.*;

public class Ihossv1MetaPermitFileEntry {

	Ihossv1CellPermit fieldIhossv1CellPermit;
	int fieldServiceIndicator, fieldEdition;
	String dataServerId, fieldComment;

	public Ihossv1MetaPermitFileEntry() {
		super();
	}

	public Ihossv1MetaPermitFileEntry(Ihossv1CellPermit anIhossv1CellPermit, int aServiceIndicatorInt, int anEditionInt,
			String aDataServerIdString, String aCommentString) {
		initialize(anIhossv1CellPermit, aServiceIndicatorInt, anEditionInt, aDataServerIdString, aCommentString);
	}

	public Ihossv1MetaPermitFileEntry(String aString) throws ParseException {
		initialize(aString);
	}

	
	public String getDataServerId() {
		return dataServerId;
	}


	public String getComment() {
		return fieldComment;
	}

	public int getEdition() {
		return fieldEdition;
	}

	public Ihossv1CellPermit getIhossv1CellPermit() {
		return fieldIhossv1CellPermit;
	}

	public int getServiceIndicator() {
		return fieldServiceIndicator;
	}

	public String getString() {
		String aString = getIhossv1CellPermit().getString();
		aString = aString + ',';
		aString = aString + getServiceIndicator();
		aString = aString + ',';
		aString = aString + (getEdition()==-1?"":getEdition());
		aString = aString + ",";
		aString = aString + getDataServerId();
		aString = aString + ",";
		aString = aString + getComment();
		return aString;
	}

	public void initialize(Ihossv1CellPermit anIhossv1CellPermit, int aServiceIndicatorInt, int anEditionInt, String aDataServerIdString, String aCommentString) {
		setIhossv1CellPermit(anIhossv1CellPermit);
		setServiceIndicator(aServiceIndicatorInt);
		setEdition(anEditionInt);
		setDataServerId(aDataServerIdString);
		setComment(aCommentString);
	}

	public void initialize(String aString) throws ParseException {
		setString(aString);
	}

	public void setDataServerId(String dataServerId) {
		this.dataServerId = dataServerId;
	}
	
	public void setComment(String aString) {
		fieldComment = aString;
	}

	public void setEdition(int anInt) {
		fieldEdition = anInt;
	}

	public void setIhossv1CellPermit(Ihossv1CellPermit anIhossv1CellPermit) {
		fieldIhossv1CellPermit = anIhossv1CellPermit;
	}

	public void setServiceIndicator(int anInt) {
		fieldServiceIndicator = anInt;
	}

	public void setString(String aString) throws ParseException {
		int startIndex, endIndex;

		startIndex = 0;
		endIndex = aString.indexOf(",", startIndex);
		String ihossv1UserPermitString = aString.substring(startIndex, endIndex);

		startIndex = endIndex + 1;
		endIndex = aString.indexOf(",", startIndex);
		String serviceIndicatorString = aString.substring(startIndex, endIndex);

		startIndex = endIndex + 1;
		endIndex = aString.indexOf(",", startIndex);
		String editionString = aString.substring(startIndex, endIndex);

		startIndex = endIndex + 1;
		endIndex = aString.indexOf(",", startIndex);
		String dataServerIdString = aString.substring(startIndex, endIndex);
		
		startIndex = endIndex + 1;
		String commentString = aString.substring(startIndex);

		fieldIhossv1CellPermit = new Ihossv1CellPermit(ihossv1UserPermitString);
		fieldServiceIndicator = new Integer(serviceIndicatorString).intValue();
		fieldEdition = !editionString.equals("") ? new Integer(editionString).intValue() : -1;
		dataServerId = dataServerIdString;
		fieldComment = commentString;
	}

	public String toString() {
		String toString = getClass().getName() + "(";
		toString = toString + getString();
		toString = toString + ")\n";

		return toString;
	}
}
