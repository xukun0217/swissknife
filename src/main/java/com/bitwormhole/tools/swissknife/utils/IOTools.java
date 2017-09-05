package com.bitwormhole.tools.swissknife.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOTools {

	public static void close(Closeable s) {

		if (s != null) {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
