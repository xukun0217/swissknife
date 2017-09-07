package com.bitwormhole.swissknife.platform;

import java.io.File;

public class Linux extends Platform {

	public boolean isCurrentOS() {
		String os = System.getProperty("os.name");
		if (os == null) {
			return false;
		} else {
			return os.toLowerCase().contains("linux");
		}
	}

	@Override
	public File getPWD() {
		String path = System.getenv("PWD");
		return new File(path);
	}

}
