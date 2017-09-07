package com.bitwormhole.swissknife.cli;

import java.io.File;

import org.springframework.context.ApplicationContext;

public class SwissknifeParam {

	private String[] arguments;
	private File knifeXML;
	private File pwd;
	private ApplicationContext config;

	public SwissknifeParam() {
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	public File getKnifeXML() {
		return knifeXML;
	}

	public void setKnifeXML(File knifeXML) {
		this.knifeXML = knifeXML;
	}

	public ApplicationContext getConfig() {
		return config;
	}

	public void setConfig(ApplicationContext config) {
		this.config = config;
	}

	public File getPwd() {
		return pwd;
	}

	public void setPwd(File pwd) {
		this.pwd = pwd;
	}

}
