package com.bitwormhole.swissknife.context;

import java.io.File;
import java.io.PrintStream;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public interface KnifeContext {

	String[] getArguments();

	File getKnifeXML(); // the knife.xml (a spring config file)

	File getPWD(); // path of working directory

	ApplicationContext getConfig();

	PrintStream output();

	Logger log();

}
