package ca.gc.chs.ihossv1;

import java.text.*;

public class Ihossv1BasicPermitFileEntry {

  Ihossv1CellPermit fieldIhossv1CellPermit;

public Ihossv1BasicPermitFileEntry() {

  super();

}
public Ihossv1BasicPermitFileEntry(Ihossv1CellPermit anIhossv1CellPermit) {

  initialize(anIhossv1CellPermit);

}
public Ihossv1BasicPermitFileEntry(String aString) throws ParseException {

  initialize(aString);

}
public Ihossv1CellPermit getIhossv1CellPermit() {

  return fieldIhossv1CellPermit;

}
public String getString() {

  return fieldIhossv1CellPermit.getString();

}
public void initialize(Ihossv1CellPermit anIhossv1CellPermit) {

  setIhossv1CellPermit(anIhossv1CellPermit);

}
public void initialize(String aString) throws ParseException {

  setString(aString);

}
public void setIhossv1CellPermit(Ihossv1CellPermit anIhossv1CellPermit) {

  fieldIhossv1CellPermit = anIhossv1CellPermit;

}
public void setString(String aString) throws ParseException {

  fieldIhossv1CellPermit = new Ihossv1CellPermit(aString);

}
  public String toString() {

    String toString = getClass().getName() + "(";
    toString = toString + getString();
    toString = toString + ")\n";

    return toString;

  }
}
