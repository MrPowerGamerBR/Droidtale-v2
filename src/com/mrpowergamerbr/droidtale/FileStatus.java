package com.mrpowergamerbr.droidtale;

public enum FileStatus {
	VALID, INVALID_CHECKSUM, MISSING_ASSETS, ERROR, NOT_SET;
	
	public boolean go() {
		return (this == VALID || this == INVALID_CHECKSUM);
	}
}
