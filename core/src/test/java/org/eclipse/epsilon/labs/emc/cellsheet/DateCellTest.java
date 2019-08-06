package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class DateCellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    DateCell cell;

    @Test
    public void getType_should_return_DATECELL() {
        assertThat(cell.getType()).isEqualTo(CellsheetType.DATE_CELL);
    }

    @Test
    public void getKinds_should_return_DATECELL_CELL_HASA1_HASID() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.DATE_CELL,
                CellsheetType.CELL,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}
