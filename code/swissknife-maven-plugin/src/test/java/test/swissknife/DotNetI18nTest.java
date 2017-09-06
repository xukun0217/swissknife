package test.swissknife;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import test.swissknife.helper.TestingHelper;

import com.bitwormhole.tools.swissknife.DotNetI18nFromProperties;
import com.bitwormhole.tools.swissknife.DotNetI18nResxRepair;
import com.bitwormhole.tools.swissknife.DotNetI18nToProperties;
import com.bitwormhole.tools.swissknife.context.KnifeMojo;

public class DotNetI18nTest {

	@Test
	public void test() {

		this.run(new DotNetI18nResxRepair());
		this.run(new DotNetI18nToProperties());
		this.run(new DotNetI18nFromProperties());

	}

	private void run(KnifeMojo mojo) {
		try {
			TestingHelper.initMojo(mojo);
			mojo.execute();
		} catch (MojoExecutionException | MojoFailureException e) {
			throw new RuntimeException(e);
		}
	}
}
