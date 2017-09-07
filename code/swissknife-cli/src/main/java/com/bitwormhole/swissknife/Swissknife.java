package com.bitwormhole.swissknife;

import com.bitwormhole.swissknife.cli.SwissknifeRunner;

public class Swissknife {

	public static void main(String[] args) {

		SwissknifeRunner runner = new SwissknifeRunner();
		runner.setArguments(args);
		runner.run();

	}

}
