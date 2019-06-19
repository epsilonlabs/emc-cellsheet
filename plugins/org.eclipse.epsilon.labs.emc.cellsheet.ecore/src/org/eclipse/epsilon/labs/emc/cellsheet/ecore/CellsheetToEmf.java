package org.eclipse.epsilon.labs.emc.cellsheet.ecore;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.labs.emc.cellsheet.Profiler;
import org.eclipse.epsilon.labs.emc.cellsheet.excel.ExcelBook;

public class CellsheetToEmf {

	public static void main(String... args) throws Exception {
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
		System.setProperty("profile", "false");

		final String filename = getFilename(args);
		System.out.println("Transformation Test: " + filename);

		final ExcelBook book = getBook(filename);
		final EmfModel emf = getEmf(getOutput(filename));
		emf.store();

		final EolModule eol = new EolModule();

		eol.getContext().getModelRepository().addModel(book);
		eol.getContext().getModelRepository().addModel(emf);

		eol.parse(Paths.get("./scripts/CellsheetToEmf.eol").toUri());
		if (!eol.getParseProblems().isEmpty()) {
			for (ParseProblem pp : eol.getParseProblems()) {
				System.out.println(pp);
			}
			System.exit(1);
		}

		Profiler.profileStart(eol, "transform");
		eol.execute();
		Profiler.profileStop(eol, "transform");
		Profiler.profileStart(eol, "store");
		emf.store();
		Profiler.profileStop(eol, "store");

		Profiler.profileCounts();
	}

	public static EmfModel getEmf(String output) throws Exception {
		// Load the metamodel
		File file = new File(output);
		if (file.exists()) {
			file.delete();
		}

		StringProperties props = new StringProperties();
		props.put(EmfModel.PROPERTY_NAME, "Emf");
		props.put(EmfModel.PROPERTY_CACHED, false);
		props.put(EmfModel.PROPERTY_CONCURRENT, true);
		props.put(EmfModel.PROPERTY_STOREONDISPOSAL, true);
		props.put(EmfModel.PROPERTY_READONLOAD, false);

		props.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, EmfUtil.createFileBasedURI("./models/Cellsheet.ecore"));
		props.put(EmfModel.PROPERTY_MODEL_URI, EmfUtil.createFileBasedURI(output));

		EmfModel model = new EmfModel();
		model.load(props);
		model.store();

		return model;
	}

	public static ExcelBook getBook(String filename) throws Exception {
		final StringProperties props = new StringProperties();
		props.put(ExcelBook.PROPERTY_CACHED, true);
		props.put(ExcelBook.PROPERTY_CONCURRENT, true);
		props.put(ExcelBook.PROPERTY_PRE_CACHE, true);
		props.put(ExcelBook.PROPERTY_NAME, "Excel");
		props.put(ExcelBook.PROPERTY_FILE, filename);

		for (Entry<Object, Object> e : props.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
		System.out.println();

		final ExcelBook book = new ExcelBook();
		book.load(props);
		return book;
	}

	public static String getOutput(String filename) throws Exception {
		Path path = Paths.get(filename).getFileName();
		String[] split = path.toString().split(Pattern.quote("."));
		new File("out/").mkdirs();
		return "out/" + split[0].replaceAll("\\s+", "_") + ".model";
	}

	public static String getFilename(String... args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("No filename");
		}
		return args[0];
	}

	public static boolean doDebug(String... args) {
		if (args.length < 2) {
			return true;
		}
		return Boolean.getBoolean(args[1]);
	}

}
