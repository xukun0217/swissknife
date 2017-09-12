package test.swissknife;

import org.junit.Test;

import com.bitwormhole.swissknife.test.TestingRunner;

public class DotNetI18nTest {

	@Test
	public void test() {

		String[] array = { "dotnet-i18n-resx-repair",
				"dotnet-i18n-to-properties", "dotnet-i18n-from-properties" };

		for (String cmd : array) {
			String[] args = { cmd };
			TestingRunner.run(args);
		}

	}

}
