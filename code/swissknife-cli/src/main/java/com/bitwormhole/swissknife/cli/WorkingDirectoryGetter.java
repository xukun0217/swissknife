package com.bitwormhole.swissknife.cli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bitwormhole.swissknife.platform.Platform;

public class WorkingDirectoryGetter {

	public File getPWD() {
		Platform p = Platform.getInstance();
		if (p == null) {
			this.printEnv();
			throw new RuntimeException("unknow system");
		} else {
			return p.getPWD();
		}
	}

	private void printEnv() {

		System.out.println("List of System.env:");
		Map<String, String> map = System.getenv();
		this.printTable(map);

		System.out.println("List of System.properties:");
		map = this.convert(System.getProperties());
		this.printTable(map);
	}

	private Map<String, String> convert(Properties pro) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<Object> keys = pro.keys();
		for (; keys.hasMoreElements();) {
			String key = keys.nextElement().toString();
			String val = pro.getProperty(key);
			map.put(key, val);
		}
		return map;
	}

	private void printTable(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			String value = map.get(key);
			String str = String.format("  %40s = %s", key, value);
			System.out.println(str);
		}
	}

}
