package test.swissknife.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.plugin.logging.Log;

public class TestingLog implements Log {

	final static Logger log = LogManager.getLogger();

	private TestingLog() {
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public void debug(CharSequence content) {
		log.debug(content);
	}

	@Override
	public void debug(CharSequence content, Throwable error) {
		log.debug(content, error);
	}

	@Override
	public void debug(Throwable error) {
		log.debug(error);
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public void info(CharSequence content) {
		log.info(content);
	}

	@Override
	public void info(CharSequence content, Throwable error) {
		log.info(content, error);
	}

	@Override
	public void info(Throwable error) {
		log.info(error);
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public void warn(CharSequence content) {
		log.warn(content);
	}

	@Override
	public void warn(CharSequence content, Throwable error) {
		log.warn(content, error);
	}

	@Override
	public void warn(Throwable error) {
		log.warn(error);
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public void error(CharSequence content) {
		log.error(content);
	}

	@Override
	public void error(CharSequence content, Throwable error) {
		log.error(content, error);
	}

	@Override
	public void error(Throwable error) {
		log.error(error);
	}

	public static Log getInstance() {
		return new TestingLog();
	}

}
