package com.sybina.ebr;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Certificate;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.bc.BcX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

class GenerateCertificates {

	private static final String PROVIDER_NAME = BouncyCastleProvider.PROVIDER_NAME;
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    private static final String SIGNATURE_ALGORITHM = "SHA512withRSA";
    private static final String KEY_GENERATION_ALGORITHM = "RSA";

	
    public static void main(String[] args) throws UnsupportedEncodingException, SocketException, UnknownHostException {
    	try {
    		createCertificate("Sybina Master CA Certificate", "Sybina", "Elbasan", "Elbasan",
    				"Albania", new Date(System.currentTimeMillis() - 86400000L),
    				new Date(System.currentTimeMillis() + 3155695200000L), "C:\\", "ebr_system", "C:\\", "ebr_system", "ebr_secret_pass123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void createCertificate(String commonName, String organization, String locality, String state,
			String country, Date validFrom, Date validTo, String pathToWriteCert, String certName, String pathToWriteKeyStore, String keyStoreName, String keyStorePassword) throws Exception {
    	KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_GENERATION_ALGORITHM, PROVIDER_NAME);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String certificateDn = "CN=" + commonName + ", O=" + organization + ", L=" + locality + ", ST=" + state
				+ ", C= " + country;
        X509Certificate cert = createCACert(keyPair.getPublic(), keyPair.getPrivate(), certificateDn, validFrom, validTo);
        saveCert(cert, keyPair, pathToWriteCert, certName);
        saveInKeyStore(certName, pathToWriteKeyStore, keyStoreName, keyStorePassword, keyPair, cert);
    }
    
    /**
     * Create a certificate to use by a Certificate Authority
     * 
     * Retrieved from http://www.programcreek.com/java-api-examples/index.php?class=org.bouncycastle.cert.X509v3CertificateBuilder&method=addExtension
     * 
     * @param publicKey Public key
     * @param privateKey Private key
     * @return Generated X509 Certificate
     */
    private static X509Certificate createCACert(PublicKey publicKey, PrivateKey privateKey, String certificateDn, Date validFrom, Date validTo) throws Exception {
    	
        X500Name issuerName = new X500Name(certificateDn);

        X500Name subjectName = issuerName;

        BigInteger serial = BigInteger.valueOf(new SecureRandom().nextInt());

        X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(issuerName, serial, validFrom, validTo, subjectName, publicKey);
        builder.addExtension(Extension.subjectKeyIdentifier, false, createSubjectKeyIdentifier(publicKey));
        builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));

        KeyUsage usage = new KeyUsage(KeyUsage.keyCertSign | KeyUsage.digitalSignature | KeyUsage.keyEncipherment | KeyUsage.dataEncipherment | KeyUsage.cRLSign);
        builder.addExtension(Extension.keyUsage, false, usage);

        ASN1EncodableVector purposes = new ASN1EncodableVector();
        purposes.add(KeyPurposeId.id_kp_serverAuth);
        purposes.add(KeyPurposeId.id_kp_clientAuth);
        purposes.add(KeyPurposeId.anyExtendedKeyUsage);
        builder.addExtension(Extension.extendedKeyUsage, false, new DERSequence(purposes));

        X509Certificate cert = signCertificate(builder, privateKey);
        cert.checkValidity(new Date());
        cert.verify(publicKey);
        return cert;
    }
    
    /**
     * Helper method
     * 
     * Retrieved from http://www.programcreek.com/java-api-examples/index.php?api=org.bouncycastle.cert.bc.BcX509ExtensionUtils
     * 
     * @param key
     * @return
     * @throws Exception
     */
    private static SubjectKeyIdentifier createSubjectKeyIdentifier(Key key) throws Exception {
        ASN1InputStream is = new ASN1InputStream(new ByteArrayInputStream(key.getEncoded()));
        ASN1Sequence seq = (ASN1Sequence) is.readObject();
        is.close();
        @SuppressWarnings("deprecation")
        SubjectPublicKeyInfo info = new SubjectPublicKeyInfo(seq);
        return new BcX509ExtensionUtils().createSubjectKeyIdentifier(info);
    }

    /**
     * Helper method
     * 
     * Retrieved from http://www.programcreek.com/java-api-examples/index.php?source_dir=mockserver-master/mockserver-core/src/main/java/org/mockserver/socket/KeyStoreFactory.java
     * 
     * @param certificateBuilder
     * @param signedWithPrivateKey
     * @return
     * @throws Exception
     */
    private static X509Certificate signCertificate(X509v3CertificateBuilder certificateBuilder, PrivateKey signedWithPrivateKey) throws Exception {
        ContentSigner signer = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).setProvider(PROVIDER_NAME).build(signedWithPrivateKey);
        return new JcaX509CertificateConverter().setProvider(PROVIDER_NAME).getCertificate(certificateBuilder.build(signer));
    }
    
    
    
    
    private static void saveCert(X509Certificate cert, KeyPair key, String pathToWriteCert, String certName) throws Exception {
    	Base64.Encoder encoder = Base64.getEncoder();
		String certStr = "-----BEGIN CERTIFICATE-----\n" + encoder.encodeToString(cert.getEncoded())
				+ "\n-----END CERTIFICATE-----";
		System.out.println("\nCertificate:\n" + certStr);
		writeToFile(pathToWriteCert + "\\" + certName + ".cert", certStr);

		String privateKeyStr = encoder.encodeToString(key.getPrivate().getEncoded());
		System.out.println("\n\nPrivate key:\n" + privateKeyStr);
		writeToFile(pathToWriteCert + "\\" + certName + ".key", privateKeyStr);

		String publicKeyStr = encoder.encodeToString(key.getPublic().getEncoded());
		System.out.println("\n\nPublic key (yet in certificate):\n" + publicKeyStr);
	}

	private static void writeToFile(String absolutePath, String content) {
        BufferedWriter writer = null;
        try {
            try {
                File file = new File(absolutePath);
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    writer.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        finally {
            try {
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	private static void saveInKeyStore(String certName, String pathToWriteKeyStore, String keyStoreName,
			String keyStorePassword, KeyPair keyPair, X509Certificate cert) throws KeyStoreException {
		KeyStore ks = KeyStore.getInstance("JKS");
		try {
			ks.load(null, (new String(genNonce())).toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ks.setKeyEntry(certName+"_key", keyPair.getPrivate(), keyStorePassword.toCharArray(), new java.security.cert.Certificate[] {cert});
        ks.setCertificateEntry(certName+"_cert", cert);
        writeKeystoreToFile(pathToWriteKeyStore + "\\" + keyStoreName + ".jks", ks, keyStorePassword);
	}
	private static void writeKeystoreToFile(String absolutePath, KeyStore ks, String password) {
		try(java.io.FileOutputStream fos = new java.io.FileOutputStream(absolutePath);){
		    
		    try {
				ks.store(fos, password.toCharArray());
			} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	private static byte[] genNonce() {
        SecureRandom rand = new SecureRandom();
        byte[] nonce = new byte[2048];
        rand.nextBytes(nonce);
        return nonce;
    }
}