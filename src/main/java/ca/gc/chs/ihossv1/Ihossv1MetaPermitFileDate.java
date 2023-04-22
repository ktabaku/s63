package ca.gc.chs.ihossv1;

import java.util.*;
import java.text.*;

public class Ihossv1MetaPermitFileDate extends Ihossv1Date {

	public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm");
	public static int STRING_LENGTH = 14;

	public Ihossv1MetaPermitFileDate() {
		super();
	}

	public Ihossv1MetaPermitFileDate(String aString) throws ParseException {
		super();
		initialize(aString);
	}

	public Ihossv1MetaPermitFileDate(Date aDate) {
		super();
		initialize(aDate);
	}

	@Override
	public SimpleDateFormat getDateFormat() {
		return SIMPLE_DATE_FORMAT;
	}
}
