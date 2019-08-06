package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellsheetTypeTest {

    @Test
    public void fromTypeName_should_return_GT_when_given_GT() {
        assertThat(CellsheetType.fromTypeName("GT")).isEqualTo(CellsheetType.GT);
    }

    @Test
    public void fromTypeName_should_return_null_when_given_string_with_no_corresponding_value() {
        assertThat(CellsheetType.fromTypeName("RandomString123Foo")).isNull();
    }
}
