package com.bitwormhole.tools.swissknife;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Calendar;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectDatabase;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefDatabase;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import com.bitwormhole.tools.swissknife.context.KnifeContext;
import com.bitwormhole.tools.swissknife.context.KnifeMojo;
import com.bitwormhole.tools.swissknife.utils.IOTools;
import com.bitwormhole.tools.swissknife.utils.StringTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Mojo(name = "version-stringify")
public class VersionStringify extends KnifeMojo {

	interface PropKey {

		String branch________x = "branch";
		String build_time____x = "build.time";
		String build_time_strx = "build.time.string";
		String commit_id_____x = "commit.id";
		String commit_id_short = "commit.id.short";
		String commit_time___x = "commit.time";
		String commit_time_str = "commit.time.string";
		String version_str___x = "version.string";

	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		try {

			KnifeContext kfc = this.getKnifeContext();
			Log log = this.getLog();
			Task task = new Task(kfc, log);

			task.init();
			task.loadConfig();
			task.makeVersion();
			task.save();

		} catch (IOException e) {

			throw new RuntimeException(e);

		} finally {

		}

	}

	public static class Config {

		public String outputPropertiesFile;
		public String outputVersionFile;

		public String getOutputPropertiesFile() {
			return outputPropertiesFile;
		}

		public void setOutputPropertiesFile(String outputPropertiesFile) {
			this.outputPropertiesFile = outputPropertiesFile;
		}

		public String getOutputVersionFile() {
			return outputVersionFile;
		}

		public void setOutputVersionFile(String outputVersionFile) {
			this.outputVersionFile = outputVersionFile;
		}

	}

	public static class VersionInfo {

		String branch;
		String commitId;
		String commitShortId;

		long buildTime;
		long commitTime;

		String buildTimeString;
		String commitTimeString;

		String versionString; // the result like 'branch-YYYYMMDD_hhmmss-hashid'

	}

	private static class Helper {

		private final Log log;
		private final Gson gson;

		private Helper(Log log) {

			GsonBuilder gb = new GsonBuilder();
			gb.setPrettyPrinting();

			this.gson = gb.create();
			this.log = log;

		}

		public void makeConfigFileIfNeed(File file) {

			if (file.exists()) {
				return;
			} else {
				log.warn("create new config in " + file);
			}

			Config conf = new Config();
			conf.setOutputPropertiesFile("version.properties");
			conf.setOutputVersionFile("version.txt");

			try {
				String str = gson.toJson(conf);
				StringTools.save(str, file, null);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String makeVersionString(VersionInfo vi) {
			vi.buildTimeString = this.timeToString(vi.buildTime);
			vi.commitTimeString = this.timeToString(vi.commitTime);
			String fmt = "%s-%s-%s";
			return String.format(fmt, vi.branch, vi.commitTimeString, vi.commitShortId);
		}

		private String timeToString(long ms) {

			Calendar cale = java.util.Calendar.getInstance();
			cale.setTimeInMillis(ms);

			int YY = cale.get(Calendar.YEAR);
			int MM = cale.get(Calendar.MONTH) + 1;
			int DD = cale.get(Calendar.DAY_OF_MONTH);

			int hh = cale.get(Calendar.HOUR_OF_DAY);
			int mm = cale.get(Calendar.MINUTE);
			int ss = cale.get(Calendar.SECOND);

			String fmt = "%04d%02d%02d_%02d%02d%02d";
			return String.format(fmt, YY, MM, DD, hh, mm, ss);
		}

		public File findGitRepo(File pos) {
			final String name = ".git";
			File p = pos;
			for (; p != null; p = p.getParentFile()) {
				File file = new File(p, name);
				if (file.isDirectory() && file.exists()) {
					return file;
				}
			}
			return null;
		}
	}

	private static class Task {

		private final File base;
		private final File pom;
		private final Log log;
		private final Helper helper;
		private VersionInfo version;
		private Config config;

		public Task(KnifeContext kfc, Log log) {
			File pom = kfc.getPom();
			this.pom = pom;
			this.base = pom.getParentFile();
			this.log = log;
			this.helper = new Helper(log);
		}

		public void init() {
			log.info("version-stringify: " + this.pom);
		}

		public void loadConfig() throws IOException {

			InputStream in = null;
			Reader rdr = null;

			try {

				String enc = "UTF-8";
				String name = VersionStringify.class.getName() + ".json";
				File file = new File(this.base, name);

				helper.makeConfigFileIfNeed(file);

				in = new FileInputStream(file);
				rdr = new InputStreamReader(in, enc);

				Config conf = helper.gson.fromJson(rdr, Config.class);
				this.config = conf;
			} finally {
				IOTools.close(rdr);
				IOTools.close(in);
			}

		}

		public void makeVersion() throws IOException {

			Git git = null;
			Repository repo = null;

			try {
				git = Git.open(this.helper.findGitRepo(this.base));
				repo = git.getRepository();
				RefDatabase refs = repo.getRefDatabase();
				ObjectDatabase objects = repo.getObjectDatabase();

				String branch = repo.getBranch();
				Ref ref = refs.getRef(branch);
				ObjectId commit_id = ref.getObjectId();

				ObjectLoader obj = objects.open(commit_id);
				byte[] ba = obj.getBytes();
				RevCommit commit_info = RevCommit.parse(ba);
				final long time = commit_info.getCommitTime() * 1000L;

				VersionInfo vi = new VersionInfo();
				vi.branch = branch;
				vi.commitId = commit_id.getName();
				vi.commitTime = time;
				vi.buildTime = System.currentTimeMillis();
				vi.commitShortId = vi.commitId.substring(0, 7);
				vi.versionString = this.helper.makeVersionString(vi);
				this.version = vi;

				refs.close();
				objects.close();

			} finally {
				if (repo != null) {
					repo.close();
				}
				if (git != null) {
					git.close();
				}
			}

		}

		public void save() throws IOException {

			final File out_version, out_prop, workspace;
			workspace = this.base;
			out_prop = new File(workspace, this.config.outputPropertiesFile);
			out_version = new File(workspace, this.config.outputVersionFile);

			log.info("write version info to " + out_prop);
			log.info("write version info to " + out_version);

			Properties prop = new Properties();
			VersionInfo vi = this.version;
			OutputStream out = null;

			prop.setProperty(PropKey.branch________x, vi.branch);
			prop.setProperty(PropKey.build_time____x, vi.buildTime + "");
			prop.setProperty(PropKey.build_time_strx, vi.buildTimeString);
			prop.setProperty(PropKey.commit_time___x, vi.commitTime + "");
			prop.setProperty(PropKey.commit_time_str, vi.commitTimeString);
			prop.setProperty(PropKey.commit_id_____x, vi.commitId);
			prop.setProperty(PropKey.commit_id_short, vi.commitShortId);
			prop.setProperty(PropKey.version_str___x, vi.versionString);

			try {
				File dir = out_prop.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileOutputStream(out_prop);
				prop.store(out, "auto-generate by " + this);
			} finally {
				IOTools.close(out);
				out = null;
			}

			StringTools.save(vi.versionString, out_version, null);

		}
	}

}
