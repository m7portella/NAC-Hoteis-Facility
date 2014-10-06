package br.com.fiap.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtils {

	public static String criptografar(String senha) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		//Criar uma instancia do algoritmo de criptografia
		MessageDigest md = MessageDigest.getInstance("MD5");
		//Adiciona a informação que queremos criptografar 
		md.update(senha.getBytes("ISO-8859-1"));
		//Gera a senha criptografada
		BigInteger hash = new BigInteger(1,md.digest());
		String senhaCript = hash.toString(16);
		return senhaCript;
	}
}
