package test.swissknife;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import test.swissknife.helper.TestingHelper;

import com.bitwormhole.tools.swissknife.mojo.DotNetI18nFromProperties;
import com.bitwormhole.tools.swissknife.mojo.DotNetI18nToProperties;

public class DotNetI18nTest {

	@Test
	public void test() {

		try {

			DotNetI18nToProperties mojo1 = new DotNetI18nToProperties();
			TestingHelper.initMojo(mojo1);
			mojo1.execute();

			DotNetI18nFromProperties mojo2 = new DotNetI18nFromProperties();
			TestingHelper.initMojo(mojo2);
			mojo2.execute();

		} catch (MojoExecutionException | MojoFailureException e) {
			throw new RuntimeException(e);
		}

	}
}
