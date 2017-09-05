package com.bitwormhole.tools.swissknife.context;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

public abstract class KnifeMojo extends AbstractMojo {

	private KnifeContext knifeContext;

	public KnifeContext getKnifeContext() {
		KnifeContext kfc = this.knifeContext;
		if (kfc == null) {
			KnifeContextLoader loader = new KnifeContextLoader();
			kfc = loader.load();
			this.knifeContext = kfc;
		}
		return kfc;
	}

	public void setKnifeContext(KnifeContext knifeContext) {
		this.knifeContext = knifeContext;
	}

	private class KnifeContextLoader {

		public KnifeContext load() {

			Object project = KnifeMojo.this.getPluginContext().get("project");

			MavenProject mp = (MavenProject) project;
			KnifeContext kc = new KnifeContext();

			File pom = mp.getFile();
			kc.setPom(pom);

			return kc;
		}
	}

}
