package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import java.security.cert.Certificate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class privateclass {
	
	//     pfx to prublic and private cer file(icici)
	  @GetMapping("/keys")
	    public String generateKeysFromPfx() {
	        try {
	            // Path to the .pfx file and the password
	            String pfxFilePath = "C:\\Users\\sa\\Downloads\\icici\\icici\\paisalo.pfx";
	            String pfxPassword = "123456"; // Replace with the actual password

	            // Load the .pfx file as a KeyStore
	            KeyStore keyStore = KeyStore.getInstance("PKCS12");
	            FileInputStream fis = new FileInputStream(pfxFilePath);
	            keyStore.load(fis, pfxPassword.toCharArray());

	            // Retrieve the alias (assumes the first alias)
	            String alias = keyStore.aliases().nextElement();

	            // Retrieve the private key
	            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, pfxPassword.toCharArray());
	            if (privateKey == null) {
	                return "Private key not found!";
	            }

	            // Retrieve the certificate and extract the public key
	            Certificate certificate = keyStore.getCertificate(alias);
	            
	            
	            if (certificate instanceof X509Certificate) {
	                X509Certificate x509Certificate = (X509Certificate) certificate;
	                
	                // Save the certificate as a .cer file
	                FileOutputStream cerOutputStream = new FileOutputStream("C:\\Users\\sa\\Downloads\\icici\\icici\\public_key_pdl_cer.cer");
	                cerOutputStream.write(x509Certificate.getEncoded());
	                cerOutputStream.close();
	                
	                System.out.println("Public Key has been saved to public_key.cer");
	            } else {
	                System.out.println("The certificate is not of type X.509.");
	            }
	            
	            
	            
	            PublicKey publicKey = certificate.getPublicKey();

	            // Convert public and private keys to PEM format and save to files
	            saveKeyToPem(publicKey, "C:\\Users\\sa\\Downloads\\icici\\icici\\public_key_pdl.pem", "PUBLIC KEY");
	            saveKeyToPem(privateKey, "C:\\Users\\sa\\Downloads\\icici\\icici\\private_key_pdl.pem", "PRIVATE KEY");

	            return "Public and private keys have been saved successfully.";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "An error occurred: " + e.getMessage();
	        }
	    }

	    private void saveKeyToPem(Object key, String filePath, String keyType) throws IOException {
	        // Encode the key in Base64
	        String base64EncodedKey = Base64.getEncoder().encodeToString(
	                key instanceof PublicKey ? ((PublicKey) key).getEncoded() : ((PrivateKey) key).getEncoded()
	        );

	        // Format as PEM
	        StringBuilder pemKey = new StringBuilder();
	        pemKey.append("-----BEGIN ").append(keyType).append("-----\n");
	        pemKey.append(base64EncodedKey);
	        pemKey.append("\n-----END ").append(keyType).append("-----");

	        // Write to file
	        try (FileWriter writer = new FileWriter(filePath)) {
	            writer.write(pemKey.toString());
	        }
	    }
	
	
	
	
	
	
	//pfx to public and private(rkjain)
	
	/*
	 * @GetMapping("/keys") public String generateKeysFromPfx() { try { // Path to
	 * the .pfx file and the password String pfxFilePath =
	 * "D:\\cert\\rkj_seil_capricorn.pfx"; String pfxPassword = "rkjain"; // Replace
	 * with the actual password
	 * 
	 * // Load the .pfx file as a KeyStore KeyStore keyStore =
	 * KeyStore.getInstance("PKCS12"); FileInputStream fis = new
	 * FileInputStream(pfxFilePath); keyStore.load(fis, pfxPassword.toCharArray());
	 * 
	 * // Retrieve the alias (assumes the first alias) String alias =
	 * keyStore.aliases().nextElement();
	 * 
	 * // Retrieve the private key PrivateKey privateKey = (PrivateKey)
	 * keyStore.getKey(alias, pfxPassword.toCharArray()); if (privateKey == null) {
	 * return "Private key not found!"; }
	 * 
	 * // Retrieve the certificate and extract the public key Certificate
	 * certificate = keyStore.getCertificate(alias); PublicKey publicKey =
	 * certificate.getPublicKey();
	 * 
	 * // Convert public and private keys to PEM format and save to files
	 * saveKeyToPem(publicKey, "D:\\cert\\public_key_pdl.pem", "PUBLIC KEY");
	 * saveKeyToPem(privateKey, "D:\\cert\\private_key_pdl.pem", "PRIVATE KEY");
	 * 
	 * return "Public and private keys have been saved successfully."; } catch
	 * (Exception e) { e.printStackTrace(); return "An error occurred: " +
	 * e.getMessage(); } }
	 * 
	 * private void saveKeyToPem(Object key, String filePath, String keyType) throws
	 * IOException { // Encode the key in Base64 String base64EncodedKey =
	 * Base64.getEncoder().encodeToString( key instanceof PublicKey ? ((PublicKey)
	 * key).getEncoded() : ((PrivateKey) key).getEncoded() );
	 * 
	 * // Format as PEM StringBuilder pemKey = new StringBuilder();
	 * pemKey.append("-----BEGIN ").append(keyType).append("-----\n");
	 * pemKey.append(base64EncodedKey);
	 * pemKey.append("\n-----END ").append(keyType).append("-----");
	 * 
	 * // Write to file try (FileWriter writer = new FileWriter(filePath)) {
	 * writer.write(pemKey.toString()); } }
	 */
	
	
	
	
	
	
	

	
	
	//////////////////////////////////////////////////////////////////////////
	
	    
	    
	    //cer to public key and private 
	    
	    
	/*
	 * @GetMapping("/pass2") public String welcome1() throws CertificateException,
	 * IOException {
	 * 
	 * 
	 * String certificatePath = "D:\\cert\\EndEntity_www.paisalo_in.cer";
	 * 
	 * // Load the .cer file FileInputStream fis=null; try { fis = new
	 * FileInputStream(certificatePath);
	 * 
	 * 
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * // Create a certificate factory CertificateFactory certificateFactory =
	 * CertificateFactory.getInstance("X.509");
	 * 
	 * // Generate an X509 certificate from the .cer file X509Certificate
	 * certificate = (X509Certificate) certificateFactory.generateCertificate(fis);
	 * 
	 * // Extract the public key from the certificate PublicKey publicKey =
	 * certificate.getPublicKey();
	 * 
	 * // Print the public key
	 * 
	 * // Convert the public key to Base64 (PEM format) String publicKeyPEM =
	 * convertToPEMFormat(publicKey);
	 * 
	 * // Write the public key to a .pem file FileWriter pemFileWriter = new
	 * FileWriter("D:\\cert\\EndEntity_www.paisalo_in_public_key.pem");
	 * pemFileWriter.write(publicKeyPEM); pemFileWriter.close();
	 * 
	 * System.out.println("Public key saved to public_key.pem");
	 * 
	 * 
	 * return "Public Key: " + publicKey.toString(); }
	 * 
	 * private static String convertToPEMFormat(PublicKey publicKey) { // Encode the
	 * public key in Base64 String base64PublicKey =
	 * Base64.getEncoder().encodeToString(publicKey.getEncoded());
	 * 
	 * // Format it as a PEM file StringBuilder pemKey = new StringBuilder();
	 * pemKey.append("-----BEGIN PUBLIC KEY-----\n");
	 * pemKey.append(base64PublicKey); pemKey.append("\n-----END PUBLIC KEY-----");
	 * 
	 * return pemKey.toString(); }
	 */
	
	  

}
