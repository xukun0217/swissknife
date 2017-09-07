package com.bitwormhole.tools.swissknife;

import com.bitwormhole.swissknife.KnifeAction;
import com.bitwormhole.swissknife.context.KnifeContext;
import com.bitwormhole.tools.swissknife.impl.DoDotNetI18nProperties;

public class DotNetI18nResxRepair implements KnifeAction {

	@Override
	public void execute(KnifeContext context) {

		DoDotNetI18nProperties.dotNetResxRepair(context).run();

	}

}
