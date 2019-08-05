package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PoiBookBuilderTest {

    @Test
    public void build_should_return_PoiBook_with_bookName_derived_from_modelUri() {
        PoiBook book = new PoiBook.Builder().withModelUri("/a/path/to/Hello World.xlsx").build();
        assertThat(book.getBookName()).isEqualTo("Hello World.xlsx");
    }
}
