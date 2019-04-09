package org.eclipse.epsilon.labs.workflow.tasks.cellsheet;

import org.apache.tools.ant.BuildException;
import org.eclipse.epsilon.workflow.tasks.LoadModelTask;

public class LoadCellsheetModelTask extends LoadModelTask {

	@Override
	public void executeImpl() throws BuildException {
		if (type == null || type.isEmpty()) {
			type = "ExcelBook";
			impl = "org.eclipse.epsilon.labs.emc.cellsheet.excel.ExcelBook";
		}

		super.executeImpl();
	}

}
