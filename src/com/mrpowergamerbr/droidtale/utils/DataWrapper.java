package com.mrpowergamerbr.droidtale.utils;

import java.io.File;

import com.mrpowergamerbr.droidtale.FileStatus;

public class DataWrapper {
	public File data;
	public String md5;
	public FileStatus fs;

	public DataWrapper() {
		
	}
	
	public DataWrapper(File data, String md5, FileStatus fs) {
		this.data = data;
		this.md5 = md5;
		this.fs = fs;
	}
}
