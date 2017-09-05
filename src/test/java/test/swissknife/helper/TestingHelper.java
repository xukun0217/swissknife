package test.swissknife.helper;

import org.apache.maven.plugin.logging.Log;

import com.bitwormhole.tools.swissknife.context.KnifeContext;
import com.bitwormhole.tools.swissknife.context.KnifeMojo;

public class TestingHelper {

	public static void initMojo(KnifeMojo mojo) {

		KnifeContext kc = TestingKnifeContext.getInstance();
		Log log = TestingLog.getInstance();
		mojo.setKnifeContext(kc);
		mojo.setLog(log);

	}

}
