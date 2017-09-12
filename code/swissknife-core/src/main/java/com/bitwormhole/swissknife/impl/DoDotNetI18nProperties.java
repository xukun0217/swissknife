package com.bitwormhole.swissknife.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import com.bitwormhole.swissknife.AbstractAction;
import com.bitwormhole.swissknife.PropertyTable;
import com.bitwormhole.swissknife.context.KnifeContext;
import com.bitwormhole.swissknife.utils.IOTools;

public class DoDotNetI18nProperties {

	static final PrintStream out = System.out;
	static final String enc = "UTF-8";
	static final String newline = "\r\n";

	public static Runnable dotNetToProp(KnifeContext kc, AbstractAction action) {
		Task task = new Task(kc, action);
		task.resxProc = new ResxToPropProc(task);
		return new Walker(task);
	}

	public static Runnable dotNetFromProp(KnifeContext kc, AbstractAction action) {
		Task task = new Task(kc, action);
		task.resxProc = new ResxFromPropProc(task);
		return new Walker(task);
	}

	public static Runnable dotNetResxRepair(KnifeContext kc,
			AbstractAction action) {
		Task task = new Task(kc, action);
		task.resxProc = new ResxRepairProc(task);
		return new Walker(task);
	}

	private static class Task {

		private ArrayList<String> suffix_list;
		private File prop_dir;
		private KnifeContext context;
		private final Map<String, ResxGroup> resxTable;
		private ResxProcessing resxProc;
		private final AbstractAction action;

		public Task(KnifeContext kc, AbstractAction action) {
			this.context = kc;
			this.action = action;
			this.resxTable = new HashMap<String, ResxGroup>();
		}

		private String getProperty(String key) {
			PropertyTable pt = action.getProperties();
			return pt.getRequiredProperty(key);
		}

		public ResxGroup getResx(File raw_resx) {
			String name = raw_resx.getName();
			ResxGroup resx = this.resxTable.get(name);
			if (resx == null) {
				resx = new ResxGroup(raw_resx);
				this.resxTable.put(name, resx);
			}
			return resx;
		}

	}

	private static interface Handler {

		void onFile(File path, Walker walker);
	}

	private static class Walker implements Runnable {

		private final Task task;
		private final Handler handler;

		public Walker(Task task) {
			this.task = task;
			this.handler = new DefaultHandler(task);
		}

		@Override
		public void run() {

			try {

				this.loadConfig();
				this.loadSuffixList();
				this.walk();
				this.processResxFiles();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		private void processResxFiles() {

			Map<String, ResxGroup> map = this.task.resxTable;
			ArrayList<String> keys = new ArrayList<String>(map.keySet());
			Collections.sort(keys);

			for (String key : keys) {
				ResxGroup item = map.get(key);
				this.task.resxProc.process(item);
			}

		}

		private void walk() {
			String project_dir = task.getProperty("project.directory");
			String prop_dir = task.getProperty("properties.directory");
			final File dir = task.context.getKnifeXML().getParentFile();
			File proj_path = new File(dir, project_dir);
			File prop_path = new File(dir, prop_dir);
			out.println("project.directory    = " + proj_path);
			out.println("properties.directory = " + prop_path);
			this.task.prop_dir = prop_path;
			this.walk(proj_path, 64);
		}

		private void walk(File path, int depth) {

			if (depth < 0) {
				return;
			} else if (!path.exists()) {
				return;
			} else if (!this.accept(path)) {
				return;
			}

			if (path.isDirectory()) {
				String[] list = path.list();
				Arrays.sort(list);
				for (String name : list) {
					File ch = new File(path, name);
					this.walk(ch, depth - 1);
				}
			} else {
				this.handler.onFile(path, this);
			}

		}

		private boolean accept(File path) {

			if (path.isDirectory()) {
				final String name = path.getName();
				if (name == null) {
					return false;
				} else if (name.equals("bin")) {
					return false;
				} else if (name.equals("target")) {
					return false;
				}
			}

			return true;
		}

		private void loadSuffixList() {
			String value = this.task.getProperty("suffix");
			String[] array = value.split("\\|");
			ArrayList<String> list = new ArrayList<String>();
			for (String s : array) {
				s = s.trim();
				if (s.length() == 0) {
					continue;
				} else if (s.startsWith(".")) {
					list.add(s);
					out.println("  for suffix: " + s);
				}
			}
			this.task.suffix_list = list;
		}

		private void loadConfig() throws IOException {

		}
	}

	private static class DefaultHandler implements Handler {

		private final Task task;

		public DefaultHandler(Task task) {
			this.task = task;
		}

		@Override
		public void onFile(File path, Walker walker) {

			String name = path.getName();
			for (String suffix : this.task.suffix_list) {
				if (name.endsWith(suffix)) {
					this.addI18nResx(path, suffix);
					out.println(path + "  [accept]");
					return;
				}
			}
			out.println(path);

		}

		private void addI18nResx(File path, String suffix) {

			final String name = path.getName();
			final File dir = path.getParentFile();
			final String raw_name = name.substring(0,
					name.length() - suffix.length())
					+ ".resx";

			File raw_resx = new File(dir, raw_name);
			File prop_file = new File(this.task.prop_dir, name + ".txt"); // ".properties");

			ResxGroup resx_def = this.task.getResx(raw_resx);
			resx_def.put(path, prop_file);
		}
	}

	static class PropertiesBuilder {

		private final Map<String, String> table;

		public PropertiesBuilder() {
			this.table = new HashMap<String, String>();
		}

		public PropertiesBuilder(PropertiesBuilder builder) {
			this.table = new HashMap<String, String>(builder.table);
		}

		public void addAll(Properties prop) {
			Enumeration<Object> keys = prop.keys();
			for (; keys.hasMoreElements();) {
				String key = keys.nextElement().toString();
				String val = prop.getProperty(key);
				this.table.put(key, val);
			}
		}

		public Properties create() {
			Properties prop = new Properties();
			for (String key : table.keySet()) {
				String val = table.get(key);
				prop.setProperty(key, val);
			}
			return prop;
		}

	}

	static class ResxIO {

		public Document loadXML(File file) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				return builder.parse(file);
			} catch (SAXException | IOException e) {
				throw new RuntimeException(e);
			} catch (ParserConfigurationException e) {
				throw new RuntimeException(e);
			} finally {
			}
		}

		public Properties loadPropertiesFromResx(File file) {
			Document dom = this.loadXML(file);
			Properties prop = new Properties();
			Element root = dom.getDocumentElement();
			NodeList nlist = root.getElementsByTagName("data");
			for (int i = nlist.getLength() - 1; i >= 0; --i) {
				Element data = (Element) nlist.item(i);
				String name = this.getAttributeSafe(data, "name");
				String type = this.getAttributeSafe(data, "type");
				String value = data.getTextContent().trim();
				if (name.startsWith(">>") || (type.length() != 0)) {
					continue;
				}
				// out.println(" " + name + " = " + value);
				prop.setProperty(name, value);
			}
			return prop;
		}

		private String getAttributeSafe(Element node, String key) {
			String value = node.getAttribute(key);
			if (value == null) {
				return "";
			} else {
				return value.trim();
			}
		}

		public Properties loadProperties(File file) {
			InputStream in = null;
			Reader reader = null;
			try {
				Properties prop = new Properties();
				if (!file.exists()) {
					return prop;
				}
				in = new FileInputStream(file);
				reader = new InputStreamReader(in, enc);
				prop.load(reader);
				// out.println(" load prop = " + prop);
				return prop;
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				IOTools.close(in);
			}
		}

		public void save(Properties prop, File file) {
			OutputStream output = null;
			Writer writer = null;
			try {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				output = new FileOutputStream(file);
				writer = new OutputStreamWriter(output, enc);
				writer.append("# ").append(file.getName()).append(newline)
						.append(newline);
				List<String> keys = new ArrayList<String>();
				Set<Object> okeys = prop.keySet();
				for (Object ok : okeys) {
					keys.add(ok.toString());
				}
				Collections.sort(keys);
				for (String key : keys) {
					String value = prop.getProperty(key);
					writer.append(key).append('=').append(value)
							.append(newline);
					// out.println(key + " = " + value);
				}
				writer.append(newline);
				writer.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				IOTools.close(writer);
				IOTools.close(output);
			}
		}

		public void save(Document dom, File file) {
			OutputStream os = null;
			try {
				out.println("write XML to " + file);
				final DOMImplementationLS ls;
				final DOMImplementation impl = dom.getImplementation();
				ls = (DOMImplementationLS) impl.getFeature("LS", "3.0");
				final LSSerializer ser = ls.createLSSerializer();
				final LSOutput output = ls.createLSOutput();

				os = new FileOutputStream(file);
				// os = System.out;

				output.setEncoding(enc);
				output.setByteStream(os);
				ser.write(dom, output);

			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				IOTools.close(os);
			}
		}

	}

	interface ResxProcessing {

		void process(ResxGroup resx);

	}

	static class ResxGroup {

		final String key;
		final File theDefault;
		// map.key = pure file name without suffix
		final Map<String, ResxPair> pairs;

		ResxGroup(File def) {
			this.key = def.getName();
			this.theDefault = def;
			this.pairs = new HashMap<String, ResxPair>();
		}

		public void put(File def, File i18n) {
			ResxPair r18 = new ResxPair(def, i18n);
			String k = r18.key;
			this.pairs.put(k, r18);
		}

	}

	private static class ResxPair {

		final String key;
		final File i18nResx;
		final File properties;

		public ResxPair(File resx, File prop) {
			this.i18nResx = resx;
			this.properties = prop;
			this.key = resx.getName();
		}
	}

	static class ResxFromPropProc implements ResxProcessing {

		public ResxFromPropProc(Task task) {
		}

		@Override
		public void process(ResxGroup resx) {
			Map<String, ResxPair> map = resx.pairs;
			for (String key : map.keySet()) {
				ResxPair pair = map.get(key);
				this.updateXML(pair);
			}
		}

		private void updateXML(ResxPair pair) {
			final File f_xml = pair.i18nResx;
			final File f_prop = pair.properties;
			if (!f_xml.exists()) {
				throw new RuntimeException("file not exists" + f_xml);
			} else if (!f_prop.exists()) {
				throw new RuntimeException("file not exists" + f_prop);
			}
			final ResxIO io = new ResxIO();
			final Document dom = io.loadXML(f_xml);
			final Properties prop = io.loadProperties(f_prop);
			final Enumeration<Object> keys = prop.keys();
			int dirty = 0;
			for (; keys.hasMoreElements();) {
				String key = keys.nextElement().toString();
				String value = prop.getProperty(key);
				if (this.putData(key, value, dom)) {
					dirty++;
				}
			}
			if (dirty > 0) {
				io.save(dom, f_xml);
			}
		}

		private boolean putData(String key, String value, Document dom) {
			Element tag_data = this.findData(key, dom);
			if (tag_data == null) {
				tag_data = dom.createElement("data");
				tag_data.setAttribute("name", key);
				tag_data.setAttribute("xml:space", "preserve");
				dom.getDocumentElement().appendChild(tag_data);
			} else {
				final String v1 = value.trim();
				final String v2 = tag_data.getTextContent().trim();
				if (v1.equals(v2)) {
					return false;
				} else {
					this.clean(tag_data);
				}
			}
			Element tag_value = dom.createElement("value");
			tag_data.appendChild(tag_value);
			tag_value.setTextContent(value);
			return true;
		}

		private void clean(Element ele) {
			for (int tout = 100; tout > 0; --tout) {
				final Node ch = ele.getFirstChild();
				if (ch == null) {
					break;
				} else {
					ele.removeChild(ch);
				}
			}
		}

		private Element findData(String name, Document dom) {
			NodeList nlist = dom.getElementsByTagName("data");
			for (int i = nlist.getLength() - 1; i >= 0; --i) {
				Element ele = (Element) nlist.item(i);
				String n2 = ele.getAttribute("name");
				if (name.equals(n2)) {
					return ele;
				}
			}
			return null;
		}
	}

	static class ResxRepairProc implements ResxProcessing {

		private final Task task;

		public ResxRepairProc(Task task) {
			this.task = task;
		}

		@Override
		public void process(ResxGroup resx) {
			final File def_file = resx.theDefault;
			final File ref_file = this.forFile(".en-US.resx", def_file);
			final List<String> slist = task.suffix_list;
			for (String suffix : slist) {
				File target = this.forFile(suffix, def_file);
				if (!target.exists()) {
					out.println("create file " + target);
					this.copyFile(ref_file, target);
				}
			}
		}

		private void copyFile(File src, File dst) {
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new FileInputStream(src);
				output = new FileOutputStream(dst);
				IOTools.pump(input, output, null);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				IOTools.close(output);
				IOTools.close(input);
			}
		}

		private File forFile(String suffix, File theDefault) {
			final File dir = theDefault.getParentFile();
			final String name = theDefault.getName();
			final int i = name.lastIndexOf('.');
			final String p1 = name.substring(0, i);
			final String p2 = name.substring(i);
			if (!p2.equals(".resx")) {
				throw new RuntimeException("bad default.resx name: " + name);
			}
			return new File(dir, p1 + suffix);
		}

	}

	static class ResxToPropProc implements ResxProcessing {

		public ResxToPropProc(Task task) {
		}

		@Override
		public void process(ResxGroup resx) {

			out.println("default: " + resx.theDefault);

			final PropertiesBuilder builder = new PropertiesBuilder();
			final ResxIO io = new ResxIO();

			Map<String, ResxPair> pairs = resx.pairs;
			ArrayList<String> keys = new ArrayList<String>(pairs.keySet());
			Collections.sort(keys);

			// load

			for (String key : keys) {
				ResxPair pair = pairs.get(key);
				// out.println("resx: " + pair.i18nResx);
				// out.println("prop: " + pair.properties);
				Properties prop = io.loadPropertiesFromResx(pair.i18nResx);
				builder.addAll(prop);
			}
			Properties prop = io.loadPropertiesFromResx(resx.theDefault);
			builder.addAll(prop);

			// save

			for (String key : keys) {
				ResxPair pair = pairs.get(key);
				File dest = pair.properties;

				PropertiesBuilder b2 = new PropertiesBuilder(builder);
				prop = io.loadProperties(dest);
				b2.addAll(prop);
				prop = b2.create();

				out.println("write to " + dest);
				io.save(prop, dest);

			}

		}
	}
}
