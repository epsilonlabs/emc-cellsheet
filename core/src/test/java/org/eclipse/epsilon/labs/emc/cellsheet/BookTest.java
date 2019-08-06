package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class BookTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    Book book;

    @Test
    public void getA1_should_return_correct_a1_id() {
        String bookName = "Example Book 1.xlsx";
        when(book.getBookName()).thenReturn(bookName);
        assertThat(book.getA1()).isEqualTo("[Example Book 1.xlsx]");
    }

    @Test
    public void getType_should_return_BOOK() {
        assertThat(book.getType()).isEqualTo(CellsheetType.BOOK);
    }

    @Test
    public void getKinds_should_return_BOOK_HASA1_HASID() {
        assertThat(book.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BOOK,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}
