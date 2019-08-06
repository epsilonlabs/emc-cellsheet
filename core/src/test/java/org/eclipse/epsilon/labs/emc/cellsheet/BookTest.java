package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class BookTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    Book book;

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
