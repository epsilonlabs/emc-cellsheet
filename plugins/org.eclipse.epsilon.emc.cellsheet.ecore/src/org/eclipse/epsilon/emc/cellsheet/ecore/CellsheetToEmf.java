package org.eclipse.epsilon.emc.cellsheet.ecore;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.etl.EtlModule;
import org.eclipse.epsilon.etl.trace.TransformationTrace;

public class CellsheetToEmf {

	public static void main(String... args) throws Exception {
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");

		final String filename = getFilename(args);
		final String output = getOutput(filename);

		final ExcelBook book = getBook(filename);
		final EmfModel emf = getEmf(output);
		emf.store();

		final EtlModule etl = new EtlModule();
		etl.getContext().getModelRepository().addModel(book);
		etl.getContext().getModelRepository().addModel(emf);

		etl.parse(Paths.get("./scripts/CellsheetToEmf.etl").toUri());
		if (!etl.getParseProblems().isEmpty()) {
			for (ParseProblem pp : etl.getParseProblems()) {
				System.out.println(pp);
			}
			System.exit(1);
		}

		etl.execute();
		emf.store();
	}

	public static EmfModel getEmf(String output) throws Exception {
		// Load the metamodel
		File file = new File(output);
		if (file.exists()) {
			file.delete();
		}

		URI createFileURI = URI.createFileURI("./models/Cellsheet.ecore");
		final Resource metamodel = EmfUtil.createResource(createFileURI);
		metamodel.load(null);
		final EPackage ePackage = (EPackage) metamodel.getContents().get(0);

		final Resource resource = EmfUtil.createResource(URI.createFileURI(output));
		final InMemoryEmfModel model = new InMemoryEmfModel("Emf", resource, Collections.singleton(ePackage));
		model.setConcurrent(true);
		model.setCachingEnabled(true);
		model.setStoredOnDisposal(true);
		model.setReadOnLoad(false);

		return model;
	}

	public static ExcelBook getBook(String filename) throws Exception {
		final StringProperties props = new StringProperties();
		props.put(ExcelBook.PROPERTY_CACHED, true);
		props.put(ExcelBook.PROPERTY_CONCURRENT, true);
		props.put(ExcelBook.PROPERTY_NAME, "Excel");
		props.put(ExcelBook.PROPERTY_FILE, filename);

		final ExcelBook book = new ExcelBook();
		book.load(props);
		return book;
	}

	public static String getOutput(String filename) throws Exception {
		Path path = Paths.get(filename).getFileName();
		String[] split = path.toString().split(Pattern.quote("."));
		split[split.length - 1] = "model";

		String output = "./out/" + Arrays.stream(split).collect(Collectors.joining("."));
		new File("./out/").mkdirs();
		return output;
	}

	public static String getFilename(String... args) {
		return args.length == 0 ? "./spreadsheets/Spreadsheet Equiv.xlsx" : args[0];
	}

}
