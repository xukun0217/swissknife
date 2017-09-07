package com.bitwormhole.swissknife.action;

import com.bitwormhole.swissknife.context.KnifeContext;
import com.bitwormhole.swissknife.impl.DoDotNetI18nProperties;

public class DotNetI18nToProperties extends DotNetI18nProperties {

	@Override
	public void execute(KnifeContext context) {

		DoDotNetI18nProperties.dotNetToProp(context, this).run();

	}

}
