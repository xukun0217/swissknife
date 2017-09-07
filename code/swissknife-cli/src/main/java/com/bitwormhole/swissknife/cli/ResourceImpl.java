package com.bitwormhole.swissknife.cli;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.Resource;

import com.bitwormhole.tools.swissknife.utils.IOTools;

public class ResourceImpl implements Resource {

	private final File file;
	private byte[] data;

	public ResourceImpl(SwissknifeParam param) {
		this.file = param.getKnifeXML();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		byte[] ba = this.getData();
		return new ByteArrayInputStream(ba);
	}

	private byte[] getData() {
		byte[] ba = this.data;
		if (ba == null) {
			InputStream in = null;
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				in = new FileInputStream(file);
				IOTools.pump(in, baos, null);
				ba = baos.toByteArray();
				this.data = ba;
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				IOTools.close(in);
			}
		}
		return ba;
	}

	@Override
	public long contentLength() throws IOException {
		return file.length();
	}

	@Override
	public Resource createRelative(String arg0) throws IOException {
		return null;
	}

	@Override
	public boolean exists() {
		return file.exists();
	}

	@Override
	public String getDescription() {
		return file.getName();
	}

	@Override
	public File getFile() throws IOException {
		return file;
	}

	@Override
	public String getFilename() {
		return file.getName();
	}

	@Override
	public URI getURI() throws IOException {
		return file.toURI();
	}

	@Override
	public URL getURL() throws IOException {
		return file.toURI().toURL();
	}

	@Override
	public boolean isOpen() {
		// return (this.data != null);
		return false;
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public long lastModified() throws IOException {
		return file.lastModified();
	}

}
