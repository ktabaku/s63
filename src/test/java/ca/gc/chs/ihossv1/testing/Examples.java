package ca.gc.chs.ihossv1.testing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.util.Arrays;


import ca.gc.chs.ihossv1.Ihossv1BasicPermitFile;
import ca.gc.chs.ihossv1.Ihossv1BasicPermitFileEntry;
import ca.gc.chs.ihossv1.Ihossv1Cell;
import ca.gc.chs.ihossv1.Ihossv1CellKey;
import ca.gc.chs.ihossv1.Ihossv1CellName;
import ca.gc.chs.ihossv1.Ihossv1CellPermit;
import ca.gc.chs.ihossv1.Ihossv1CellPermitDate;
import ca.gc.chs.ihossv1.Ihossv1CellSignatureFileEntry;
import ca.gc.chs.ihossv1.Ihossv1Certificate;
import ca.gc.chs.ihossv1.Ihossv1HardwareId;
import ca.gc.chs.ihossv1.Ihossv1InstallPermit;
import ca.gc.chs.ihossv1.Ihossv1ManufacturerId;
import ca.gc.chs.ihossv1.Ihossv1ManufacturerKey;
import ca.gc.chs.ihossv1.Ihossv1MetaPermitFile;
import ca.gc.chs.ihossv1.Ihossv1MetaPermitFileDate;
import ca.gc.chs.ihossv1.Ihossv1MetaPermitFileEntry;
import ca.gc.chs.ihossv1.Ihossv1MiscellaneousSupport;
import ca.gc.chs.ihossv1.Ihossv1PrivateKey;
import ca.gc.chs.ihossv1.Ihossv1PublicKey;
import ca.gc.chs.ihossv1.Ihossv1SelfSignedCertificate;
import ca.gc.chs.ihossv1.Ihossv1SystemIdentificationKey;
import ca.gc.chs.ihossv1.Ihossv1UserPermit;

public abstract class Examples {

	public static Ihossv1CellKey currentIhossv1CellKey() {

		return new Ihossv1CellKey("C1CB518E9C", null);

	}

	public static Ihossv1Cell encryptedIhossv1Cell() {

		return new Ihossv1Cell(TestFiles.encryptedCellsDirectory(), ihossv1CellName(), "000");

	}

	public static Ihossv1BasicPermitFile ihossv1BasicPermitFile() {

		return new Ihossv1BasicPermitFile(TestFiles.basicPermitFile());

	}

	public static Ihossv1BasicPermitFileEntry ihossv1BasicPermitFileEntry() {

		return new Ihossv1BasicPermitFileEntry(ihossv1CellPermit());

	}

	public static Ihossv1BasicPermitFileEntry ihossv1BasicPermitFileEntryFromString() {

		try {
			return new Ihossv1BasicPermitFileEntry(ihossv1BasicPermitFileEntry().getString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1Cell ihossv1Cell() {

		return new Ihossv1Cell(TestFiles.cellsDirectory(), ihossv1CellName(), "000");

	}

	public static Ihossv1CellName ihossv1CellName() {

		return new Ihossv1CellName("NOLD0613");

	}

	public static Ihossv1CellPermit ihossv1CellPermit() {

		try {
			Ihossv1CellPermit anIhossv1CellPermit = new Ihossv1CellPermit(ihossv1CellName(), ihossv1CellPermitDate(),
					currentIhossv1CellKey(), nextIhossv1CellKey());
			Ihossv1HardwareId anIhossv1HardwareId = ihossv1HardwareId();
			anIhossv1CellPermit.encryptCurrentIhossv1CellKey(anIhossv1HardwareId);
			anIhossv1CellPermit.encryptNextIhossv1CellKey(anIhossv1HardwareId);
			anIhossv1CellPermit.calculateIhossv1CellPermitChecksum();
			anIhossv1CellPermit.encryptIhossv1CellPermitChecksum(anIhossv1HardwareId);
			return anIhossv1CellPermit;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1CellPermitDate ihossv1CellPermitDate() {

		try {
			return new Ihossv1CellPermitDate("20000830");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1CellPermit ihossv1CellPermitFromString() {

		try {
			Ihossv1CellPermit anIhossv1CellPermit = new Ihossv1CellPermit(ihossv1CellPermit().getString());
			Ihossv1HardwareId anIhossv1HardwareId = ihossv1HardwareId();
			anIhossv1CellPermit.decryptIhossv1CellPermitChecksum(anIhossv1HardwareId);
			anIhossv1CellPermit.decryptCurrentIhossv1CellKey(anIhossv1HardwareId);
			anIhossv1CellPermit.decryptNextIhossv1CellKey(anIhossv1HardwareId);
			anIhossv1CellPermit.verifyIhossv1CellPermitChecksum();
			return anIhossv1CellPermit;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1CellSignatureFileEntry ihossv1CellSignatureFileEntry() {

		// Warning, this is a hack test method!!!
		// In this Ihossv1CellSignatureFileEntry the cell signature is just
		// coppied from the the certificate signature.
		// This would NEVER happen in the real world.

		return new Ihossv1CellSignatureFileEntry(dataServerIhossv1SelfSignedCertificate().getIhossv1Signature(),
				dataServerIhossv1SelfSignedCertificate());

	}

	public static Ihossv1CellSignatureFileEntry ihossv1CellSignatureFileEntryFromString() {

		try {
			return new Ihossv1CellSignatureFileEntry(ihossv1CellSignatureFileEntry().getString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Ihossv1HardwareId ihossv1HardwareId() {
		try {
			
//			Ihossv1HardwareId aIhossv1HardwareId = new Ihossv1HardwareId("3132333438", null);
			Ihossv1HardwareId aIhossv1HardwareId = new Ihossv1HardwareId(getServerAssignedInstallPermit(), null);
			
			aIhossv1HardwareId.encrypt(ihossv1ManufacturerKey());
			return aIhossv1HardwareId;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Ihossv1ManufacturerId ihossv1ManufacturerId() {

		return new Ihossv1ManufacturerId("3031");

	}

	public static Ihossv1ManufacturerKey ihossv1ManufacturerKey() {

		return new Ihossv1ManufacturerKey("3938373635");

	}

	public static Ihossv1MetaPermitFile ihossv1MetaPermitFile() {

		return new Ihossv1MetaPermitFile(TestFiles.metaPermitFile());

	}

	public static Ihossv1MetaPermitFileDate ihossv1MetaPermitFileDate() {

		try {
			return new Ihossv1MetaPermitFileDate("20000131 11:11");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1MetaPermitFileEntry ihossv1MetaPermitFileEntry() {

		return new Ihossv1MetaPermitFileEntry(ihossv1CellPermit(), 1, 5, "GB", "This is the comment!");

	}

	public static Ihossv1MetaPermitFileEntry ihossv1MetaPermitFileEntryFromString() {

		try {
			return new Ihossv1MetaPermitFileEntry(ihossv1MetaPermitFileEntry().getString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1UserPermit ihossv1UserPermit() {

		try {
			Ihossv1UserPermit anIhossv1UserPermit = new Ihossv1UserPermit(ihossv1HardwareId(), ihossv1ManufacturerId());
			anIhossv1UserPermit.encryptIhossv1HardwareId(ihossv1ManufacturerKey());
			anIhossv1UserPermit.calculateIhossv1UserPermitChecksum();

			return anIhossv1UserPermit;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1UserPermit ihossv1UserPermitFromString() {

		try {
			Ihossv1UserPermit anIhossv1UserPermit = new Ihossv1UserPermit(ihossv1UserPermit().getString());
			anIhossv1UserPermit.decryptIhossv1HardwareId(ihossv1ManufacturerKey());
			anIhossv1UserPermit.verifyIhossv1UserPermitChecksum();
			return anIhossv1UserPermit;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1CellKey nextIhossv1CellKey() {

		return new Ihossv1CellKey("421571CC66", null);

	}

	public static Ihossv1PrivateKey schemeAdministratorIhossv1PrivateKey() {

		try {
			return new Ihossv1PrivateKey(TestFiles.schemeAdministratorPrivateKeyFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1PublicKey schemeAdministratorIhossv1PublicKey() {

		try {
			return new Ihossv1PublicKey(TestFiles.schemeAdministratorPublicKeyFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Ihossv1Certificate dataServerIhossv1Certificate() {

		try {
			return new Ihossv1Certificate(TestFiles.dataServerCertificateFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1PrivateKey dataServerIhossv1PrivateKey() {

		try {
			return new Ihossv1PrivateKey(TestFiles.dataServerPrivateKeyFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1PublicKey dataServerIhossv1PublicKey() {

		try {
			return new Ihossv1PublicKey(TestFiles.dataServerPublicKeyFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Ihossv1SelfSignedCertificate dataServerIhossv1SelfSignedCertificate() {

		try {
			return new Ihossv1SelfSignedCertificate(TestFiles.dataServerSelfSignedCertificateFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	private static String getServerAssignedMachineId() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InterruptedException {
		String machineUuidString = getMachineId();
		Ihossv1SystemIdentificationKey theIhossv1SystemIdentificationKey = new Ihossv1SystemIdentificationKey(machineUuidString);
		Ihossv1InstallPermit theIhossv1InstallPermit = new Ihossv1InstallPermit(null, getServerAssignedInstallPermit());
		theIhossv1InstallPermit.decrypt(theIhossv1SystemIdentificationKey);
		return theIhossv1InstallPermit.getString();
	}
	
	static String getMachineId() throws IOException, InterruptedException  {
//		HardwareBinder hb = new HardwareBinder();
//		hb.ignoreHostName();
//		hb.ignoreNetwork();
//		hb.ignoreArchitecture();
//		UUID machineUuid = hb.getMachineId();
		UUID machineUuid = getSerialNumber();
		String machineUuidString = Ihossv1MiscellaneousSupport.asHexString(machineUuid);
		return machineUuidString;
	}
    public static UUID getSerialNumber() throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("wmic", "baseboard", "get", "serialnumber");
		Process process = pb.start();
		process.waitFor();
		String serialNumber = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if (line.length() < 1 || line.startsWith("SerialNumber")) {
					continue;
				}
				serialNumber = line;
				break;
			}
		}
		return UUID.nameUUIDFromBytes(serialNumber.getBytes(Charset.forName("UTF-8")));
    }
	
	private static String getServerAssignedInstallPermit() {
		return "B15B0B6A6FE6A496";
	}

	
	private static final String PUBLIC_KEY_ALIAS_EBR_WEB = "ebr_web_cert";
	private static final String PRIVATE_KEY_ALIAS = "ebr_system_key";
    private static final String PRIVATE_KEY_PASS = "ebr_secret_pass123";
    private static final String KEY_STORE_PASS = "ebr_secret_pass123";
    private static final String KEY_STORE_TYPE = "JKS";
    private static final String KEY_STORE_PATH = "src/test/resources/ebr_system.jks";
    

    static File generateFingerPrintFile(File folder) throws Exception {
    	String machineUuidHexString = getMachineId();
    	byte[] signedMachineUuidByteArray = encryptMachineUuid(machineUuidHexString);
    	
    	File fingerprintFile = new File(folder, "ebr_fpr_"+new Date().getTime()+".efpr");
    	FileOutputStream fprfos = new FileOutputStream(fingerprintFile);
    	fprfos.write(signedMachineUuidByteArray);
    	fprfos.close();
        return fingerprintFile;
    }
    private static byte[] encryptMachineUuid(String machineUuidHexString) throws Exception {
    	byte[] machineUuidByteArray = machineUuidHexString.getBytes();
    	byte[] signedMachineUuidByteArray = encryptMachineUuid(machineUuidByteArray);
    	return signedMachineUuidByteArray;
    }
    private static byte[] encryptMachineUuid(byte[] machineUuidByteArray) throws Exception {
    	final KeyStore keyStore = loadKeyStore(new File(KEY_STORE_PATH));
        final PrivateKey privateKey = (PrivateKey)keyStore.getKey(PRIVATE_KEY_ALIAS, PRIVATE_KEY_PASS.toCharArray());
        final X509Certificate webPubCert = (X509Certificate)keyStore.getCertificate(PUBLIC_KEY_ALIAS_EBR_WEB);
        
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.ENCRYPT_MODE, webPubCert.getPublicKey());
        byte[] signedFirstPass = cipher1.doFinal(machineUuidByteArray);
        
        Cipher cipher2 = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher2.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] signedMachineUuidByteArray = cipher2.doFinal(signedFirstPass);
        return signedMachineUuidByteArray;
	}
	
    private static KeyStore loadKeyStore(File privateKeyFile) throws Exception {
		try (final InputStream fileInputStream = new FileInputStream(privateKeyFile)) {
            final KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
            keyStore.load(fileInputStream, KEY_STORE_PASS.toCharArray());
            return keyStore;
        }
    }
}
