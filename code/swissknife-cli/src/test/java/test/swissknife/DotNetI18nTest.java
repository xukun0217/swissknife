package test.swissknife;

import org.junit.Test;

import com.bitwormhole.swissknife.test.TestingRunner;

public class DotNetI18nTest {

	@Test
	public void test() {

		String[] args = { "dotnet-i18n-resx-repair",
				"dotnet-i18n-to-properties", "dotnet-i18n-from-properties" };

		TestingRunner.run(args);

	}

}
