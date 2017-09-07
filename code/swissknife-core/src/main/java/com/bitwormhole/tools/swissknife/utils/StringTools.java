package com.bitwormhole.tools.swissknife.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StringTools {

	public static void save(String str, File file, String enc)
			throws IOException {
		FileOutputStream out = null;
		try {

			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}

			out = new FileOutputStream(file);
			save(str, out, enc);
		} finally {
			IOTools.close(out);
		}
	}

	public static void save(String str, OutputStream out, String enc)
			throws IOException {
		Writer wtr = null;
		try {
			if (enc == null) {
				enc = "UTF-8";
			}
			wtr = new OutputStreamWriter(out, enc);
			save(str, wtr);
		} finally {
			IOTools.close(wtr);
		}
	}

	public static void save(String str, Writer out) throws IOException {
		out.write(str);
		out.flush();
	}

}
