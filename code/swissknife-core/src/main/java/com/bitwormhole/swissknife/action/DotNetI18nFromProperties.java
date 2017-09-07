package com.bitwormhole.swissknife.action;

import com.bitwormhole.swissknife.context.KnifeContext;
import com.bitwormhole.swissknife.impl.DoDotNetI18nProperties;

public class DotNetI18nFromProperties extends DotNetI18nProperties {

	@Override
	public void execute(KnifeContext context) {

		DoDotNetI18nProperties.dotNetFromProp(context, this).run();

	}

}
