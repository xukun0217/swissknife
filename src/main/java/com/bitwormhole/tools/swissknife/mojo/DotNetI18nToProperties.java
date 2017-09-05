package com.bitwormhole.tools.swissknife.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import com.bitwormhole.tools.swissknife.KnifeContext;
import com.bitwormhole.tools.swissknife.KnifeMojo;
import com.bitwormhole.tools.swissknife.impl.DoDotNetI18nProperties;

@Mojo(name = "dotnet-i18n-to-properties")
public class DotNetI18nToProperties extends KnifeMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		KnifeContext kc = this.getKnifeContext();
		DoDotNetI18nProperties.dotNetToProp(kc).run();

	}

}
