package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class SheetTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Sheet sheet;

    @Test
    public void getId_should_return_id() {
        Book book = mock(Book.class);
        when(book.getId()).thenReturn("cellsheet://Workspace%1/Example%20Book%201.xlsx");
        when(sheet.getBook()).thenReturn(book);
        assertThat(sheet.getId()).isEqualTo("cellsheet://Workspace%1/Example%20Book%201.xlsx/0");
    }

    @Test
    public void getA1_should_return_correct_a1() {
        Book book = mock(Book.class);
        when(book.getA1()).thenReturn("[Example Workbook 1.xlsx]");
        when(sheet.getBook()).thenReturn(book);
        when(sheet.getSheetName()).thenReturn("Example Sheet 1");
        assertThat(sheet.getA1()).isEqualTo("[Example Workbook 1.xlsx]'Example Sheet 1'");
    }

    @Test
    public void getType_should_return_correct_type() {
        assertThat(sheet.getType()).isEqualTo(CellsheetType.SHEET);
    }

    @Test
    public void getKinds_should_return_correct_types() {
        assertThat(sheet.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.SHEET,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}