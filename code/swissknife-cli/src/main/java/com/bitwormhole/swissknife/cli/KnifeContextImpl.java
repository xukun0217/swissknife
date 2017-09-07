package com.bitwormhole.swissknife.cli;

import java.io.File;
import java.io.PrintStream;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.bitwormhole.swissknife.context.KnifeContext;

public class KnifeContextImpl implements KnifeContext {

	private File _knife_xml;
	private File _pwd;
	private ApplicationContext _config;
	private String[] _args;
	private Logger _logger;
	private PrintStream _out;

	public KnifeContextImpl(SwissknifeParam param) {

		this._args = param.getArguments();
		this._config = param.getConfig();
		this._knife_xml = param.getKnifeXML();
		this._pwd = param.getPwd();

		PrintStream out = System.out;
		this._logger = new LoggerImpl(out);
		this._out = out;

	}

	@Override
	public File getKnifeXML() {
		return this._knife_xml;
	}

	@Override
	public ApplicationContext getConfig() {
		return this._config;
	}

	@Override
	public String[] getArguments() {
		return this._args;
	}

	@Override
	public PrintStream output() {
		return this._out;
	}

	@Override
	public Logger log() {
		return this._logger;
	}

	@Override
	public File getPWD() {
		return this._pwd;
	}

}
