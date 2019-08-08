package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class TextCellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    TextCell cell;

    @Test
    public void getType_should_return_TEXTCELL() {
        assertThat(cell.getType()).isEqualTo(CellsheetType.TEXT_CELL);
    }

    @Test
    public void getKinds_should_return_TEXTCELL_CELL_HASA1_HASID() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.TEXT_CELL,
                CellsheetType.CELL,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}
