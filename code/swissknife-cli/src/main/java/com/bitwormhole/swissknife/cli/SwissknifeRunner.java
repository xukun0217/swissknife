package com.bitwormhole.swissknife.cli;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.bitwormhole.swissknife.KnifeAction;
import com.bitwormhole.swissknife.context.KnifeContext;

public class SwissknifeRunner extends SwissknifeParam implements Runnable {

	public SwissknifeRunner() {
	}

	@Override
	public void run() {

		ParamLoader pl = new ParamLoader();
		KnifeContext kc = pl.loadKnifeContext(this);
		String[] args = this.getArguments();

		Logger log = kc.log();
		log.info("PWD    = " + kc.getPWD());
		log.info("Config = " + kc.getKnifeXML());

		for (String cmd : args) {
			this.exec(kc, cmd);
		}
	}

	private void exec(KnifeContext kc, String cmd) {

		Logger log = kc.log();
		log.info("Command = " + cmd);

		ApplicationContext conf = kc.getConfig();
		KnifeAction action = (KnifeAction) conf.getBean(cmd);

		log.info("Bean = " + action.getClass().getName());

		action.execute(kc);
	}

}
