package org.eclipse.epsilon.labs.emc.cellsheet;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyBook;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkspaceTest {

    private static final String modelName = "Workspace 1";

    private Workspace workspace;
    private DummyBook book;

    @Before
    public void setUp() throws Exception {
        workspace = new Workspace();
        workspace.setName(modelName);

        book = new DummyBook();
        book.setBookName("Dummy Book 1.xlsx");
        workspace.addBook(book);
    }

    @Test
    public void getId() {
        assertThat(workspace.getId()).isNotBlank().isEqualTo("cellsheet://Workspace%201");
    }

    @Test
    public void getElementById_should_return_self_when_given_workspace_id() {
        System.out.println(workspace.getId());
        Object element = workspace.getElementById(workspace.getId());
        assertThat(element).isInstanceOf(Workspace.class).isSameAs(workspace);
    }

    @Test
    public void getElementById_should_return_Book_when_given_book_id() {
        Object element = workspace.getElementById(book.getId());
        assertThat(element).isInstanceOf(Book.class).isEqualToComparingFieldByFieldRecursively(book);
    }

    @Test
    public void getElementById_should_return_Sheet_when_given_sheet_id() {
        Sheet sheet = book.getSheet(52);
        Object element = workspace.getElementById(sheet.getId());
        assertThat(element).isInstanceOf(Sheet.class).isEqualToComparingFieldByFieldRecursively(sheet);
    }

    @Test
    public void getElementById_should_return_Row_when_given_row_id() {
        Row row = book.getSheet(52).getRow(100);
        Object element = workspace.getElementById(row.getId());
        assertThat(element).isInstanceOf(Row.class).isEqualToComparingFieldByFieldRecursively(row);
    }

    @Test
    public void getElementById_should_return_Cell_when_given_cell_id() {
        Cell cell = book.getSheet(52).getRow(100).getCell(34);
        Object element = workspace.getElementById(cell.getId());
        assertThat(element).isInstanceOf(Cell.class).isEqualToComparingFieldByFieldRecursively(cell);
    }

    @Test
    public void getElementById_should_return_Ast_when_given_ast_id() {
        Ast ast = (Ast) book.getSheet(52).getRow(100).getCell(34).getAsts().get(45);
        Object element = workspace.getElementById(ast.getId());
        assertThat(element).isInstanceOf(Ast.class).isEqualToComparingFieldByFieldRecursively(ast);
    }

    @Test
    public void getElementById_should_return_Ast_when_given_ast_child_id() {
        Ast ast = ((Ast) book.getSheet(52).getRow(100).getCell(34).getAsts().get(45)).childAt(23);
        Object element = workspace.getElementById(ast.getId());
        assertThat(element).isInstanceOf(Ast.class).isEqualToComparingFieldByFieldRecursively(ast);
    }
}
