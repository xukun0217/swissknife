package test.swissknife.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import com.bitwormhole.tools.swissknife.KnifeContext;

public class TestingKnifeContext {

	public static KnifeContext getInstance() {
		TestingKnifeContext tkc = new TestingKnifeContext();
		KnifeContext kc = new KnifeContext();
		kc.setPom(tkc.getPom());
		return kc;
	}

	private File getPom() {

		URI uri = null;

		try {
			uri = this.getClass().getProtectionDomain().getCodeSource()
					.getLocation().toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		final File from = new File(uri);
		final String name = "pom.xml";
		File p = from;
		for (; p != null; p = p.getParentFile()) {
			File pom = new File(p, name);
			if (pom.exists() && pom.isFile()) {
				return pom;
			}
		}

		String fmt = "cannot find %s in path of %s.";
		String msg = String.format(fmt, name, from);
		throw new RuntimeException(msg);
	}

}
