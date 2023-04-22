package ca.gc.chs.ihossv1;

import java.io.*;

public abstract class Ihossv1File {

  private File fieldFile;

public File getFile() {

	return fieldFile;

}
public void initialize(File aFile) {

  fieldFile = aFile;

}
public void setFile(File aFile) {

	fieldFile = aFile;

}
public String toString() {

	String toString = getClass().getName() + "(";
	toString = toString + getFile().toString();
	toString = toString + ")\n";

	return toString;

}
}
