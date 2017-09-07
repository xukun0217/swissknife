package test.swissknife;

import java.io.File;

import org.junit.Test;

import com.bitwormhole.swissknife.cli.WorkingDirectoryGetter;

public class PlatformTest {

	@Test
	public void test() {

		WorkingDirectoryGetter getter = new WorkingDirectoryGetter();
		File pwd = getter.getPWD();
		System.out.println("PWD = " + pwd);

	}

}
