package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class PoiCellTest {

    private Cell delegateCell;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook();
        book.setDelegate(WorkbookFactory.create(true));
        delegateCell = book.getDelegate().createSheet("Test Sheet 1").createRow(0).createCell(0);
    }

    @Test
    public void getRoot_given_a_PoiTextCell_should_return_Text_Ast() {
        String value = "This is some value";
        delegateCell.setCellValue(value);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiTextCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull().isInstanceOf(Text.class);
        assertThat(root.getToken().getValue()).isEqualTo(value);
    }

    @Test
    public void getRoot_given_a_PoiBooleanCell_should_return_Logical_Ast() {
        delegateCell.setCellValue(false);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiBooleanCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull().isInstanceOf(Logical.class);
        assertThat(root.getToken().getValue()).isEqualTo("FALSE");
    }

    @Test
    public void getRoot_given_a_PoiBlankCell_should_return_Nothing_Ast() {
        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiBlankCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull().isInstanceOf(Nothing.class);
        assertThat(root.getToken().getValue()).isBlank();
    }

    @Test
    public void getRoot_given_a_PoiNumericCell_should_return_Number_Ast() {
        double value = 123.456;
        delegateCell.setCellValue(value);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiNumericCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull().isInstanceOf(Number.class);
        assertThat(root.getToken().getValue()).isEqualTo(String.valueOf(value));
    }

    @Test
    public void getRoot_given_a_PoiFormulaCell_with_SUM_should_return_Function_Ast() {
        String formula = "SUM(A1:A5)";
        delegateCell.setCellFormula(formula);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiFormulaCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull().isInstanceOf(Function.class);
        assertThat(root.getToken().getValue()).isEqualTo("SUM");
        assertThat(root.getChildren()).hasSize(1);

        Ast child = root.getChildren().get(0);
        assertThat(child).isNotNull().isInstanceOf(Range.class);
        assertThat(child.getToken().getValue()).isEqualTo("A1:A5");
    }
}
