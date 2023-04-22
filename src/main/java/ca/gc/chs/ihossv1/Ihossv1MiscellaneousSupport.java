package ca.gc.chs.ihossv1;

import java.io.*;
import java.math.*;
import java.nio.ByteBuffer;
import java.util.*;

public abstract class Ihossv1MiscellaneousSupport {

public static void append(String aString, File aFile) throws FileNotFoundException, IOException {

	write(aString, aFile, true);

}
public static byte[] byteArray(String aHexString) {

	byte[] aByteArray = null;

	if (aHexString != null) {
		aByteArray = new byte[aHexString.length() / 2];
		for (int i = 0; i < aByteArray.length; i = i + 1) {
			aByteArray[i] = (byte) Integer.parseInt(aHexString.substring(i * 2, i * 2 + 2), 16);
		}
	}

	return aByteArray;

}
public static String hexString(byte[] aByteArray) {

	String aHexString = null;

	if (aByteArray != null) {
		aHexString = "";
		for (int i = 0; i < aByteArray.length; i++) {
			byte aByte = aByteArray[i];
			String aByteHexString = Integer.toHexString(aByte);
			while (aByteHexString.length() < 4) {
				aByteHexString = "0" + aByteHexString;
			}
			aHexString = aHexString + aByteHexString.substring(aByteHexString.length() - 2, aByteHexString.length());
		}
		aHexString = aHexString.toUpperCase();
	}

	return aHexString;

}
public static void overwrite(String aString, File aFile) throws FileNotFoundException, IOException {

	write(aString, aFile, false);

}
public static String read(File aFile) throws FileNotFoundException, IOException {

  String theString = null;

  BufferedInputStream in = new BufferedInputStream(new FileInputStream(aFile));
  ByteArrayOutputStream out = new ByteArrayOutputStream();
  int i = in.read();
  while (i != -1) {
    out.write(i);
    i = in.read();
  }
  theString = out.toString();
  in.close();
  out.close();

  return theString;

}
public static String[] stringArray(String aString) throws IOException {

  BufferedReader in = new BufferedReader(new StringReader(aString));
  ArrayList anArrayList = new ArrayList();
  String tempString = in.readLine();
  while (tempString != null) {
    anArrayList.add(tempString);
    tempString = in.readLine();
  }
  String[] aStringArray = new String[anArrayList.size()];
  anArrayList.toArray(aStringArray);

  return aStringArray;

}
public static void write(String aString, File aFile, boolean append) throws FileNotFoundException, IOException {

  ByteArrayInputStream in = new ByteArrayInputStream(aString.getBytes());
  BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(aFile.getPath(), append));
  int i = in.read();
  while (i != -1) {
    out.write(i);
    i = in.read();
  }
  in.close();
  out.close();

}

public static UUID asUuid(byte[] bytes) {
	ByteBuffer bb = ByteBuffer.wrap(bytes);
	long firstLong = bb.getLong();
	long secondLong = bb.getLong();
	return new UUID(firstLong, secondLong);
}

public static byte[] asBytes(UUID uuid) {
	ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	bb.putLong(uuid.getMostSignificantBits());
	bb.putLong(uuid.getLeastSignificantBits());
	return bb.array();
}
public static UUID asUuid(String hexString) {
	byte[] bytes = byteArray(hexString);
	return asUuid(bytes);
}

public static String asHexString(UUID uuid) {
	byte[] bytes = asBytes(uuid);
	return hexString(bytes);
}
}
