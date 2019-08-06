package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class BlankCellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BlankCell cell;

    @Test
    public void getValue_should_return_null() {
        assertThat(cell.getValue()).isNull();
    }

    @Test
    public void getType_should_return_BLANKCELL() {
        assertThat(cell.getType()).isEqualTo(CellsheetType.BLANK_CELL);
    }

    @Test
    public void getKinds_should_return_BLANKCELL_CELL_HASA1_HASID() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BLANK_CELL,
                CellsheetType.CELL,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}
