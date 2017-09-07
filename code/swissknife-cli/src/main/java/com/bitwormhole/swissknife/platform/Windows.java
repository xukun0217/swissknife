package com.bitwormhole.swissknife.platform;

import java.io.File;

public class Windows extends Platform {

	@Override
	public boolean isCurrentOS() {
		String os = System.getProperty("os.name");
		if (os != null) {
			return os.toLowerCase().contains("windows");
		}
		return false;
	}

	@Override
	public File getPWD() {
		String path = System.getProperty("user.dir");
		if (path == null) {
			return null;
		} else {
			return new File(path);
		}
	}

}
