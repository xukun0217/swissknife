package com.bitwormhole.swissknife.test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.ProtectionDomain;

import com.bitwormhole.swissknife.cli.SwissknifeRunner;

public class TestingRunner {

	public static void run(String[] args) {
		SwissknifeRunner runner = new SwissknifeRunner();
		runner.setArguments(args);
		runner.setPwd(getPwd());
		runner.run();
	}

	private static File getPwd() {
		try {

			Class<?> ref = TestingRunner.class;
			ProtectionDomain pd = ref.getProtectionDomain();
			URL loc = pd.getCodeSource().getLocation();

			return new File(loc.toURI());

		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
