package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PoiAstEvaluatorTest {

    private PoiFormulaCell cell;
    private Cell delegate;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook(WorkbookFactory.create(true));
        delegate = book.getDelegate().createSheet("Sheet 1").createRow(0).createCell(0);

        // Setup values
        Sheet valueSheet = book.getDelegate().createSheet("Values");
        for (int i = 0; i < 5; i++) {
            Row row = valueSheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                row.createCell(j).setCellValue(1);
            }
        }
    }

    @Test
    public void evaluate_given_sumA1E5_should_return_25() {
        delegate.setCellFormula("SUM(Values!A1:E5)");
        cell = (PoiFormulaCell) book.getSheet(0).getRow(0).getCell(0);
        AstEval result = cell.getRoot().evaluate();
        assertThat(result.getNumberValue()).isEqualTo(25);
    }
}
