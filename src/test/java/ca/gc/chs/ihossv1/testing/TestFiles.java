package ca.gc.chs.ihossv1.testing;

import java.io.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TestFiles extends TestCase	{

	@Test
	public static void testSchemeAdministratorPrivateKeyFile() {
		assertTrue(schemeAdministratorPrivateKeyFile().exists());
	}
	
	public static File schemeAdministratorPrivateKeyFile() {

		return new File(keysAndCertificatesDirectory(), "Scheme Administrator Private Key.txt");

	}

	@Test
	public static void testSchemeAdministratorPublicKeyFile() {
		assertTrue(schemeAdministratorPublicKeyFile().exists());
	}
	public static File schemeAdministratorPublicKeyFile() {

		return new File(keysAndCertificatesDirectory(), "Scheme Administrator Public Key.txt");

	}

	@Test
	public static void testBasicPermitFile() {
		assertTrue(basicPermitFile().exists());
	}
	public static File basicPermitFile() {

		return new File(basicPermitFilesAndMetaPermitFilesDirectory(), "ENC.PMT");

	}

	@Test
	public static void testBasicPermitFilesAndMetaPermitFilesDirectory() {
		assertTrue(basicPermitFilesAndMetaPermitFilesDirectory().exists());
	}
	public static File basicPermitFilesAndMetaPermitFilesDirectory() {

		return new File(testingRootDirectory(), "Basic Permit Files and Meta Permit Files");

	}

	@Test
	public static void testCellsDirectory() {
		assertTrue(cellsDirectory().exists());
	}
	public static File cellsDirectory() {

		return new File(testingRootDirectory(), "Cells");

	}

	@Test
	public static void testEncryptedCellsDirectory() {
		assertTrue(encryptedCellsDirectory().exists());
	}
	public static File encryptedCellsDirectory() {

		return new File(testingRootDirectory(), "Encrypted Cells");

	}

	@Test
	public static void testKeysAndCertificatesDirectory() {
		assertTrue(keysAndCertificatesDirectory().exists());
	}
	public static File keysAndCertificatesDirectory() {

		return new File(testingRootDirectory(), "Keys and Certificates");

	}

	@Test
	public static void testMetaPermitFile() {
		assertTrue(metaPermitFile().exists());
	}
	public static File metaPermitFile() {

		return new File(basicPermitFilesAndMetaPermitFilesDirectory(), "PERMIT.TXT");

	}

	@Test
	public static void testDataServerCertificateFile() {
		assertTrue(dataServerCertificateFile().exists());
	}
	public static File dataServerCertificateFile() {

		return new File(keysAndCertificatesDirectory(), "Data Server Certificate.txt");

	}

	@Test
	public static void testDataServerPrivateKeyFile() {
		assertTrue(dataServerPrivateKeyFile().exists());
	}
	public static File dataServerPrivateKeyFile() {

		return new File(keysAndCertificatesDirectory(), "Data Server Private Key.txt");

	}

	@Test
	public static void testDataServerPublicKeyFile() {
		assertTrue(dataServerPublicKeyFile().exists());
	}
	public static File dataServerPublicKeyFile() {

		return new File(keysAndCertificatesDirectory(), "Data Server Public Key.txt");

	}

	@Test
	public static void testDataServerSelfSignedCertificateFile() {
		assertTrue(dataServerSelfSignedCertificateFile().exists());
	}
	public static File dataServerSelfSignedCertificateFile() {

		return new File(keysAndCertificatesDirectory(), "Data Server Self Signed Server Certificate.txt");

	}

	@Test
	public static void testTestingRootDirectory() {
		assertTrue(testingRootDirectory().exists());
	}
	public static File testingRootDirectory() {

		return new File("src/test/resources/CHS IHOSSV1 1.0.0.0 Testing");

	}
}
