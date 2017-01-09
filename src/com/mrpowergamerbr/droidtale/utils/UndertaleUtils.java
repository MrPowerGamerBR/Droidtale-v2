package com.mrpowergamerbr.droidtale.utils;

import java.io.File;
import java.io.FileInputStream;

import com.mrpowergamerbr.droidtale.FileStatus;

public class UndertaleUtils {
	public static final String ver1001Checksum = "cd48b89b6ac6b2d3977f2f82726e5f12"; // Undertale v1.001 data.win checksum
	
	/**
	 * Checks the data.win file
	 * 
	 * @param file
	 * @return FileStatus
	 */
	public static DataWrapper checkData(File file) {
		DataWrapper dw = new DataWrapper();
		dw.data = file;
		
		File assetCheck = new File(file.getParent() + "/credits.txt");

		System.out.println("Checking for " + assetCheck.getPath());
		if (!assetCheck.exists()) {
			dw.fs = FileStatus.MISSING_ASSETS;
			return dw;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
			fis.close();
			System.out.println("File checksum: " + md5);
			dw.md5 = md5;
			if (md5.equals(ver1001Checksum)) {
				dw.fs = FileStatus.VALID;
				return dw;
			} else {
				dw.fs = FileStatus.MISSING_ASSETS;
				return dw;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			dw.fs = FileStatus.ERROR;
			return dw;
		}
	}
}
