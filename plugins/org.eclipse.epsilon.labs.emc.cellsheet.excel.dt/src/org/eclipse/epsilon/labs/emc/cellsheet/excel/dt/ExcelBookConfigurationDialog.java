package org.eclipse.epsilon.labs.emc.cellsheet.excel.dt;

import org.eclipse.epsilon.common.dt.launching.dialogs.AbstractModelConfigurationDialog;
import org.eclipse.epsilon.common.dt.util.DialogUtil;
import org.eclipse.epsilon.labs.emc.cellsheet.excel.ExcelBook;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExcelBookConfigurationDialog extends AbstractModelConfigurationDialog {

  protected Label fileLabel;
  protected Text fileText;
  protected Button browseFileButton;

  @Override
  protected void createGroups(Composite control) {
    super.createNameAliasGroup(control);
    this.createExcelGroup(control);
  }

  protected void createExcelGroup(final Composite parent) {
    // Create Spreadsheet Configuration
    final Composite groupContent = DialogUtil.createGroupContainer(parent, "Excel Spreadsheet Details", 3);

    this.fileLabel = new Label(groupContent, SWT.NONE);
    this.fileLabel.setText("File: ");

    this.fileText = new Text(groupContent, SWT.BORDER);
    this.fileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    this.browseFileButton = new Button(groupContent, SWT.NONE);
    this.browseFileButton.setText("Browse Workspace...");
    this.browseFileButton.addListener(SWT.Selection, new BrowseWorkspaceForModelsListener(
        this.fileText, "Microsoft Excel File in Workspace", "Select Excel File"));

    groupContent.layout();
    groupContent.pack();
  }

  @Override
  protected void loadProperties() {
    super.loadProperties();
    if (properties != null) {
      this.fileText.setText(this.properties.getProperty(ExcelBook.PROPERTY_FILE));
    }
  }

  @Override
  protected void storeProperties() {
    super.storeProperties();
    super.properties.put(ExcelBook.PROPERTY_FILE, this.fileText.getText());
  }

  @Override
  protected String getModelName() {
    return "Microsoft Excel - Cellsheet";
  }

  @Override
  protected String getModelType() {
    return "ExcelBook";
  }

}
