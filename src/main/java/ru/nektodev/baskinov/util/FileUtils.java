package ru.nektodev.baskinov.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Slava Tsykin ts.slawa@gmail.com
 */
public class FileUtils {

	public static String calculateFileHash(File file) throws IOException, NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(file);
		byte[] dataBytes = new byte[1024];
		int nread;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		};
		byte[] mdbytes = md.digest();

		StringBuilder sb = new StringBuilder("");
		for (byte mdbyte : mdbytes) {
			sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
