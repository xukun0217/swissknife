package com.bitwormhole.swissknife.cli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.bitwormhole.swissknife.context.KnifeContext;

public class ParamLoader {

	final static Logger log = LogManager.getLogger();

	public KnifeContext loadKnifeContext(SwissknifeParam param) {

		if (param.getPwd() == null) {
			param.setPwd(this.loadPWD());
		}

		if (param.getKnifeXML() == null) {
			param.setKnifeXML(this.loadKnifeXML(param));
		}

		if (param.getConfig() == null) {
			param.setConfig(this.loadConfig(param));
		}

		return new KnifeContextImpl(param);
	}

	private File loadPWD() {
		Map<String, String> env = System.getenv();
		PwdGetter pg = new PwdGetter(env);
		String path = pg.get();
		return new File(path);
	}

	private ApplicationContext loadConfig(SwissknifeParam param) {
		Resource res = new ResourceImpl(param);
		return new GenericXmlApplicationContext(res);
	}

	private File loadKnifeXML(SwissknifeParam param) {
		File pwd = param.getPwd();
		return this.lookUpFor("knife.xml", pwd);
	}

	private File lookUpFor(String name, File path) {
		File p = path;
		for (; p != null; p = p.getParentFile()) {
			File file = new File(p, name);
			if (file.exists() && file.isFile()) {
				return file;
			}
		}
		String fmt = "No file [%s] in path [%s] .";
		String msg = String.format(fmt, name, path);
		throw new RuntimeException(msg);
	}

	private class PwdGetter {

		private final Map<String, String> map;

		public PwdGetter(Map<String, String> env) {
			this.map = env;
		}

		public String get() {

			if (this.isUbuntu()) {
				return map.get("PWD");

			} else {
				this.printEnv();
				throw new RuntimeException("unknow system");
			}

		}

		private void printEnv() {
			// for DEBUG
			List<String> keys = new ArrayList<String>(map.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				String value = map.get(key);
				String str = String.format("  %40s = %s", key, value);
				System.out.println(str);
			}
		}

		private boolean isUbuntu() {
			String session = this.map.get("SESSION");
			return "ubuntu".equalsIgnoreCase(session);
		}
	}

}
