package ca.gc.chs.ihossv1;

import java.util.*;
import java.text.*;

public abstract class Ihossv1Date {


	private Date fieldDate;

	public Ihossv1Date() {
		super();
	}

	public Date getDate() {
		return fieldDate;
	}

	public String getString() {
		return getDateFormat().format(fieldDate);
	}

	public void initialize(String aString) throws ParseException {
		setString(aString);
	}

	public void initialize(Date aDate) {
		setDate(aDate);
	}

	public void setDate(Date aDate) {
		fieldDate = aDate;
	}

	public void setString(String string) throws ParseException {
		fieldDate = getDateFormat().parse(string);
	}

	public String toString() {
		String toString = getClass().getName() + "(";
		toString = toString + getString();
		toString = toString + ")\n";

		return toString;
	}
	
	public abstract SimpleDateFormat getDateFormat();
}
