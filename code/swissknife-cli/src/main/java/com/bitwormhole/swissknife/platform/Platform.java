package com.bitwormhole.swissknife.platform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Platform {

	public static Platform getInstance() {

		List<Platform> list = new ArrayList<Platform>();
		list.add(new Linux());
		list.add(new Windows());

		for (Platform p : list) {
			if (p.isCurrentOS()) {
				return p;
			}
		}

		return null;
	}

	public abstract boolean isCurrentOS();

	public abstract File getPWD();

}
