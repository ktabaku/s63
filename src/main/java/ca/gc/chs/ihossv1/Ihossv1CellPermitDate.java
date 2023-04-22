package ca.gc.chs.ihossv1;

import java.util.*;
import java.text.*;

public class Ihossv1CellPermitDate extends Ihossv1Date {

	public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	public static int STRING_LENGTH = 8;

	public Ihossv1CellPermitDate() {
		super();
	}

	public Ihossv1CellPermitDate(String aString) throws ParseException {
		super();
		initialize(aString);
	}

	public Ihossv1CellPermitDate(Date aDate) {
		super();
		initialize(aDate);

	}

	@Override
	public SimpleDateFormat getDateFormat() {
		return SIMPLE_DATE_FORMAT;
	}
}
