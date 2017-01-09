package com.mrpowergamerbr.droidtale.utils;

import java.io.File;

public class FileUtils {
	public static void deleteFile(File element) {
	    if (element.isDirectory()) {
	        for (File sub : element.listFiles()) {
	            deleteFile(sub);
	        }
	    }
	    element.delete();
	}
}
