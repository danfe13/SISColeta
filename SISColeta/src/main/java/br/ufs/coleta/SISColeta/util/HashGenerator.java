package br.ufs.coleta.SISColeta.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashGenerator {
	
	private static MessageDigest messageDigest;

	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(HashGenerator.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public static String gerar(String texto) {
		messageDigest.reset();
		try {
			messageDigest.update(texto.getBytes("UTF-8"));
			BigInteger hash = new BigInteger(1, messageDigest.digest());
			String stringHash = hash.toString(16);
			while (stringHash.length() < 32) {
				stringHash = "0" + stringHash;
			}
			return stringHash;
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(HashGenerator.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		}
	}
}
