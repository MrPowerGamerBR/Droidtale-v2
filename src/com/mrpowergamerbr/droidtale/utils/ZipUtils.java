package com.mrpowergamerbr.droidtale.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	public static void addFilesToExistingZip(File zipFile,
	         File[] files) throws IOException {
	        // get a temp file
	    File tempFile = File.createTempFile(zipFile.getName(), null);
	        // delete it, otherwise you cannot rename your existing zip to it.
	    tempFile.delete();

	    boolean renameOk=zipFile.renameTo(tempFile);
	    if (!renameOk)
	    {
	        throw new RuntimeException("could not rename the file "+zipFile.getAbsolutePath()+" to "+tempFile.getAbsolutePath());
	    }
	    byte[] buf = new byte[1024];

	    ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

	    ZipEntry entry = zin.getNextEntry();
	    while (entry != null) {
	        String name = entry.getName();
	        if (name.contains("game.droid")) { // Do not move the old game.droid to the new APK
	        	entry = zin.getNextEntry();
	        	continue;
	        }
	        boolean notInFiles = true;
	        for (File f : files) {
	            if (f.getName().equals(name)) {
	                notInFiles = false;
	                break;
	            }
	        }
	        if (notInFiles) {
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(name));
	            // Transfer bytes from the ZIP file to the output file
	            int len;
	            while ((len = zin.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	        }
	        entry = zin.getNextEntry();
	    }
	    // Close the streams        
	    zin.close();
	    // Compress the files
	    for (int i = 0; i < files.length; i++) {
	        InputStream in = new FileInputStream(files[i]);
	        // Add ZIP entry to output stream.
	        out.putNextEntry(new ZipEntry("assets/" + files[i].getName()));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry();
	        in.close();
	    }
	    // Complete the ZIP file
	    out.close();
	    tempFile.delete();
	}
}
