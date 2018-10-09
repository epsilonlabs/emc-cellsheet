package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;

public class Scratch {

	private ExcelBook book;
	private static final Logger LOG = Logger.getLogger(Scratch.class.getName());

	public static void main(String[] args) throws Exception {
		(new Scratch()).run();
	}

	public void run() throws Exception {
		book = ExcelTestUtil.getBook("labels.xlsx");
		ICell cell = book.getCell("Florida", 4, 2);
		IFormulaCellValue value = (ExcelFormulaCellValue) cell.getValue();
		LOG.log(Level.INFO, value.getValue());

		IFormulaTree formulaTree = value.getFormulaTree();
		print(formulaTree);

	}

	private void print(IFormulaTree tree, String indent) {
		System.out.println(indent + " " + tree.getToken());
		if (!tree.getChildren().isEmpty()) {
			for (IFormulaTree child : tree.getChildren()) {
				print(child, "-" + indent);
			}

		}
	}

	private void print(IFormulaTree tree) {
		print(tree, "");
	}
}
