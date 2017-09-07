package com.bitwormhole.swissknife.action;

import com.bitwormhole.swissknife.AbstractAction;
import com.bitwormhole.swissknife.context.KnifeContext;
import com.bitwormhole.swissknife.impl.DoVersionStringify;

public class VersionStringify extends AbstractAction {

	@Override
	public void execute(KnifeContext context) {
		DoVersionStringify todo = new DoVersionStringify();
		todo.execute(context, this);
	}

}
