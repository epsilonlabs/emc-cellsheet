package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PoiSheetTest {

    private Workbook delegate;
    private PoiSheet sheet;

    @Before
    public void setUp() throws Exception {
        delegate = WorkbookFactory.create(true);
        delegate.createSheet("Sheet 1");

        PoiBook book = new PoiBook();
        book.setDelegate(delegate);

        sheet = book.getSheet(0);
    }

    @Test
    public void getRows_should_return_all_rows() {
        IntStream.range(0, 5).forEach(i -> delegate.getSheetAt(0).createRow(i));
        assertThat(sheet.getRows())
                .asList()
                .hasSize(5)
                .extracting("rowIndex")
                .contains(0, 1, 2, 3, 4);
    }
}
