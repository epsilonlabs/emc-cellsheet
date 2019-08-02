package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Function;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Range;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Union;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PoiAstFactoryTest {

    private Cell delegate;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook(WorkbookFactory.create(true));
        delegate = book.getDelegate().createSheet("Sheet 1").createRow(0).createCell(0);
    }

    @Test
    public void of_given_SUM() {
        delegate.setCellFormula("SUM(A1:A5)");
        Ast root = getAst();
        Ast ast = root;

        assertThat(ast).isInstanceOf(Function.class);
        assertThat(ast.getToken().getValue()).isEqualTo("SUM");
        assertThat(ast.getParent()).isNull();
        assertThat(ast.getChildren()).hasSize(1);

        ast = ast.getChildren().get(0);
        assertThat(ast).isInstanceOf(Range.class);
        assertThat(ast.getToken().getValue()).isEqualTo("A1:A5");
        assertThat(ast.getParent()).isSameAs(root);
        assertThat(ast.getChildren()).isEmpty();
    }

    @Test
    public void of_given_SUM_with_UNION() {
        delegate.setCellFormula("SUM(A1:A5,B1:B5)");
        Ast root = getAst();
        Ast ast = root;

        assertThat(ast).isInstanceOf(Function.class);
        assertThat(ast.getToken().getValue()).isEqualTo("SUM");
        assertThat(ast.getParent()).isNull();
        assertThat(ast.getChildren()).hasSize(1);

        ast = ast.getChildren().get(0);
        assertThat(ast).isInstanceOf(Union.class);
        assertThat(ast.getParent()).isSameAs(root);
        assertThat(ast.getChildren()).isNotEmpty().hasSize(2);
    }

    Ast getAst() {
        return PoiAstFactory.getInstance().of(book.getSheet(0).getRow(0).getCell(0));
    }
}
