package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.Book;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetBackend;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PoiBackendTest {

    private CellsheetBackend backend = new PoiBackend();

    @Test
    public void isApplicable_should_return_true_when_given_file_path_with_xlsx_extension() {
        assertThat(backend.isApplicable("/Some File 1.xlsx")).isTrue();
    }

    @Test
    public void isApplicable_should_return_true_when_given_file_path_with_xlsm_extension() {
        assertThat(backend.isApplicable("/Some File 1.xlsm")).isTrue();
    }

    @Test
    public void isApplicable_should_return_false_when_given_file_path_with_unsupported_extension() {
        assertThat(backend.isApplicable("/Some File 1.ods")).isFalse();
    }

    @Test
    public void isApplicable_should_return_false_when_given_file_with_no_extension() {
        assertThat(backend.isApplicable("random string of stuff")).isFalse();
    }

    @Test
    public void getBuilder_should_return_new_BookBuilder_instance() {
        Book.Builder b1 = backend.getBuilder();
        Book.Builder b2 = backend.getBuilder();
        assertThat(b1).isNotSameAs(b2);
    }
}