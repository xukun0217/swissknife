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

		Exception error = null;
		try {
			ParamLoader pl = new ParamLoader();
			KnifeContext kc = pl.loadKnifeContext(this);
			String[] args = this.getArguments();

			Logger log = kc.log();
			log.info("PWD    = " + kc.getPWD());
			log.info("Config = " + kc.getKnifeXML());

			this.exec(kc, args[0]);

		} catch (Exception e) {
			error = e;
		} finally {
			this.printResult(error);
		}

	}

	private void printResult(Exception error) {
		if (error == null) {
			System.out.println("==============================");
			System.out.println("= SUCCESS                    =");
			System.out.println("==============================");
		} else {
			// error.printStackTrace();
			throw new RuntimeException(error);
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
