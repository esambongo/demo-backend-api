package ao.gov.minfin.critografia.criptografia_cliente_servidor.model;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CriptografiaClienteServidor {
	
	
	public static KeyPair gerarChavesPublicasPrivadas() throws NoSuchAlgorithmException {
		KeyPairGenerator geradorChave = KeyPairGenerator.getInstance("RSA");
		
		geradorChave.initialize(2048);
		
		KeyPair par = geradorChave.generateKeyPair();
		
		return par;
	}
	
	public static String cifrar(String mensagem,PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		byte[] messageToBytes= mensagem.getBytes();
		
		cifrador.init(Cipher.ENCRYPT_MODE, publicKey);
		
		byte[] bytesCripto = cifrador.doFinal(messageToBytes);
		
		return Base64.getEncoder().encodeToString(bytesCripto);
	}
	
	
	public static String decifrar(String mensagem, PrivateKey privateKey) throws Exception {
		byte[] bytesCifrados= Base64.getDecoder().decode(mensagem);
		
		
		Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		cifrador.init(Cipher.DECRYPT_MODE, privateKey);
		
		
		
		System.out.println("ANTES DE RETORNAR: "+privateKey);
		byte[] mensagemDecifrada = cifrador.doFinal(bytesCifrados);
		
		
		return new String(mensagemDecifrada,"UTF8");
	}
	
	
	public static PublicKey byteParaChave(byte[] bytesChave) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytesChave);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		return keyFactory.generatePublic(keySpec);
	}

}
