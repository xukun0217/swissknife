package test.swissknife;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import com.bitwormhole.tools.swissknife.VersionStringify;

import test.swissknife.helper.TestingHelper;

public class VersionStringifyTest {

	@Test
	public void test() {

		try {

			VersionStringify mojo = new VersionStringify();
			TestingHelper.initMojo(mojo);
			mojo.execute();

		} catch (MojoExecutionException | MojoFailureException e) {
			throw new RuntimeException(e);
		}

	}

}
