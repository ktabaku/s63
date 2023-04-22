package ca.gc.chs.ihossv1.testing;

import java.io.File;
import java.security.*;
import java.security.interfaces.*;

import org.junit.Test;

import ca.gc.chs.ihossv1.*;
import junit.framework.TestCase;

public class Tests	extends TestCase {

	@Test
	public static void testAddBasicPermitFileEntryToBasicPermitFile() {
		try {
			Examples.ihossv1BasicPermitFile().addIhossv1BasicPermitFileEntry(Examples.ihossv1BasicPermitFileEntry());
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testAddEcsMetaPermitFileEntryToMetaPermitFile() {
		try {
			Examples.ihossv1MetaPermitFile().addEcsIhossv1MetaPermitFileEntry(Examples.ihossv1MetaPermitFileEntry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testAddEncMetaPermitFileEntryToMetaPermitFile() {
		try {
			Examples.ihossv1MetaPermitFile().addEncIhossv1MetaPermitFileEntry(Examples.ihossv1MetaPermitFileEntry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateBasicPermitFile() {
		try {
			Examples.ihossv1BasicPermitFile().createFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateCellSignatureFile() {
		try {
			Examples.ihossv1Cell().createSignatureFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateEncryptedCellSignaturefile() {
		try {
			Examples.encryptedIhossv1Cell().createSignatureFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateMetaPermitFile() {
		try {
			Examples.ihossv1MetaPermitFile().createFile(Examples.ihossv1MetaPermitFileDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateTestKeysAndCertificates() {
		try {

			KeyPairGenerator aKeyPairGenerator = KeyPairGenerator.getInstance("DSA");

			KeyPair administratorKeyPair = aKeyPairGenerator.generateKeyPair();
			DSAPrivateKey administratorPrivateKey = (DSAPrivateKey) administratorKeyPair.getPrivate();
			DSAPublicKey administratorPublicKey = (DSAPublicKey) administratorKeyPair.getPublic();
			ca.gc.chs.ihossv1.Ihossv1PrivateKey administratorIhossv1PrivateKey = new Ihossv1PrivateKey(
					administratorPrivateKey);
			ca.gc.chs.ihossv1.Ihossv1PublicKey administratorIhossv1PublicKey = new Ihossv1PublicKey(
					administratorPublicKey);
			administratorIhossv1PrivateKey.write(TestFiles.schemeAdministratorPrivateKeyFile());
			administratorIhossv1PublicKey.write(TestFiles.schemeAdministratorPublicKeyFile());

			KeyPair serverKeyPair = aKeyPairGenerator.generateKeyPair();
			DSAPrivateKey serverPrivateKey = (DSAPrivateKey) serverKeyPair.getPrivate();
			DSAPublicKey serverPublicKey = (DSAPublicKey) serverKeyPair.getPublic();
			ca.gc.chs.ihossv1.Ihossv1PrivateKey serverIhossv1PrivateKey = new Ihossv1PrivateKey(serverPrivateKey);
			ca.gc.chs.ihossv1.Ihossv1PublicKey serverIhossv1PublicKey = new Ihossv1PublicKey(serverPublicKey);
			serverIhossv1PrivateKey.write(TestFiles.dataServerPrivateKeyFile());
			serverIhossv1PublicKey.write(TestFiles.dataServerPublicKeyFile());

			Ihossv1SelfSignedCertificate aIhossv1SelfSignedCertificate = new Ihossv1SelfSignedCertificate(
					serverIhossv1PrivateKey, serverIhossv1PublicKey);
			aIhossv1SelfSignedCertificate.write(TestFiles.dataServerSelfSignedCertificateFile());

			Ihossv1Certificate aIhossv1Certificate = new Ihossv1Certificate(administratorIhossv1PrivateKey,
					serverIhossv1PublicKey);
			aIhossv1Certificate.write(TestFiles.dataServerCertificateFile());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testCreateTestPermitFiles() {
		ca.gc.chs.ihossv1.testing.Tests.testCreateBasicPermitFile();
		ca.gc.chs.ihossv1.testing.Tests.testCreateMetaPermitFile();
	}

	@Test
	public static void testDecryptEncryptedCell() {
		try {
			Examples.encryptedIhossv1Cell().decrypt(TestFiles.cellsDirectory(), Examples.currentIhossv1CellKey() );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public static void testEncryptCell() {
		try {
			Examples.ihossv1Cell().encrypt(TestFiles.encryptedCellsDirectory(), Examples.currentIhossv1CellKey());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public static void testGetBasicPermitFileEntriesFromBasicPermitFile() {
		try {
			Ihossv1BasicPermitFileEntry[] ihossv1BasicPermitFileEntryArray = Examples.ihossv1BasicPermitFile().getIhossv1BasicPermitFileEntries();
			String name = ihossv1BasicPermitFileEntryArray[0].getIhossv1CellPermit().getIhossv1CellName().getString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testGetEcsMetaPermitFileEntriesFromMetaPermitFile() {
		try {
			Ihossv1MetaPermitFileEntry[] ihossv1MetaPermitFileEntryArray = Examples.ihossv1MetaPermitFile().getEcsIhossv1MetaPermitFileEntries();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testGetEncMetaPermitFileEntriesFromMetaPermitFile() {
		try {
			Ihossv1MetaPermitFileEntry[] ihossv1MetaPermitFileEntryArray = Examples.ihossv1MetaPermitFile().getEncIhossv1MetaPermitFileEntries();
			for(Ihossv1MetaPermitFileEntry anIhossv1MetaPermitFileEntry : ihossv1MetaPermitFileEntryArray) {
				System.out.println(anIhossv1MetaPermitFileEntry.getComment());
				Ihossv1CellPermit anIhossv1CellPermit = anIhossv1MetaPermitFileEntry.getIhossv1CellPermit();
				System.out.println(anIhossv1CellPermit.getString());
			}
			Ihossv1MetaPermitFileEntry[] ihossv1MetaPermitFileEntryArray2 = new Ihossv1MetaPermitFile(new File("src/test/resources/CHS IHOSSV1 1.0.0.0 Testing/Basic Permit Files and Meta Permit Files/2/PERMIT.TXT")).getEncIhossv1MetaPermitFileEntries();
			for(Ihossv1MetaPermitFileEntry anIhossv1MetaPermitFileEntry : ihossv1MetaPermitFileEntryArray2) {
				System.out.println(anIhossv1MetaPermitFileEntry.getComment());
				Ihossv1CellPermit anIhossv1CellPermit = anIhossv1MetaPermitFileEntry.getIhossv1CellPermit();
				System.out.println(anIhossv1CellPermit.getString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testGetMetaPermitFileDateFromMetaPermitFile() {
		try {
			Ihossv1MetaPermitFileDate ihmpfd = Examples.ihossv1MetaPermitFile().getIhossv1MetaPermitFileDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testSignCell() {
		try {
			Examples.ihossv1Cell().sign(Examples.dataServerIhossv1PrivateKey(),
					Examples.dataServerIhossv1Certificate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testSignEncryptedCell() {
		try {
			Examples.encryptedIhossv1Cell().sign(Examples.dataServerIhossv1PrivateKey(),
					Examples.dataServerIhossv1Certificate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testVerifyCell() {
		try {
			Ihossv1CellSignatureFileEntryVerification[] ihossv1CellSignatureFileEntryVerificationArray = Examples.ihossv1Cell().verify(Examples.schemeAdministratorIhossv1PublicKey());
			System.out.println(ihossv1CellSignatureFileEntryVerificationArray[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testVerifyDataServerCertificate() {
		try {
			assertTrue(Examples.dataServerIhossv1Certificate().verify(Examples.schemeAdministratorIhossv1PublicKey()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void testVerifyDataServerSelfSignedCertificate() {
		try {
			assertTrue(Examples.dataServerIhossv1SelfSignedCertificate().verify());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void testVerifyEncryptedCell() {
		try {
			Ihossv1CellSignatureFileEntryVerification[] ihossv1CellSignatureFileEntryVerificationArray = Examples.encryptedIhossv1Cell().verify(Examples.schemeAdministratorIhossv1PublicKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public static void testGetIhossv1UserPermit() {
		Ihossv1UserPermit ihossv1UserPermit = Examples.ihossv1UserPermit();
	}
	@Test
	public static void testGetMachineId() throws Exception {
		String machineId = Examples.getMachineId();
	}
	
	@Test
	public static void testGenerateFingerPrintFile() {
		File fprFile;
		try {
			fprFile = Examples.generateFingerPrintFile(TestFiles.testingRootDirectory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
