/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import com.google.common.io.Resources;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URLDecoder;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class PoiBookTest {

    private Workbook delegate;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        delegate = WorkbookFactory.create(true);
        delegate.createSheet("Sheet 1");
        delegate.createSheet("Sheet 2");
        delegate.createSheet("Sheet 3");
        book = new PoiBook();
        book.setDelegate(delegate);
    }

    @After
    public void tearDown() throws Exception {
        if (book != null) book.dispose();
        delegate.close();
    }

    @Test
    public void load_should_load_book_given_modelUri_and_name() throws Exception {
        File file = new File(URLDecoder.decode(Resources.getResource("Sales Planning.xlsx").getFile(), "UTF-8"));
        PoiBook book = new PoiBook.Builder().withModelUri(file.getAbsolutePath()).withBookName(PoiBookTest.class.getSimpleName()).build();
        book.load();
        assertThat(book.getDelegate()).isNotNull();
        assertThat(book.getBookName()).isEqualTo(PoiBookTest.class.getSimpleName());
    }

    @Test
    public void load_should_load_book_and_set_default_name_given_modelUri_and_no_set_name() throws Exception {
        File file = new File(URLDecoder.decode(Resources.getResource("Sales Planning.xlsx").getFile(), "UTF-8"));
        PoiBook book = new PoiBook.Builder().withModelUri(file.getAbsolutePath()).build();
        book.load();
        assertThat(book.getDelegate()).isNotNull();
        assertThat(book.getBookName()).isEqualTo("Sales Planning.xlsx");
    }

    @Test
    public void load_should_create_in_memory_book_with_default_name_when_model_uri_and_bookname_not_set() throws Exception {
        PoiBook book = new PoiBook.Builder().build();
        book.load();
        assertThat(book.getDelegate()).isNotNull();
        assertThat(book.getBookName()).isNotBlank();
    }

    @Test
    public void load_should_create_in_memory_book_given_name() throws Exception {
        PoiBook book = new PoiBook.Builder().withBookName(PoiBookTest.class.getSimpleName()).build();
        book.load();
        assertThat(book.getDelegate()).isNotNull();
        assertThat(book.getBookName()).isEqualTo(PoiBookTest.class.getSimpleName());
    }

    @Test
    public void setModelUri_given_a_String_should_set_modelUri() {
        assertThat(book.getModelUri()).isNull();
        book.setModelUri("Hello World.xlsx");
        assertThat(book.getModelUri()).isNotBlank().isEqualTo("Hello World.xlsx");
    }

    @Test
    public void setWorkspace_given_a_Workspace_should_set_workspace() {
        assertThat(book.getWorkspace()).isNull();
        Workspace workspace = mock(Workspace.class);
        book.setWorkspace(workspace);
        assertThat(book.getWorkspace()).isNotNull().isSameAs(workspace);
    }

    @Test
    public void setBookName_given_a_String_should_set_bookName() {
        assertThat(book.getBookName()).isNull();
        book.setBookName("Hello World.xlsx");
        assertThat(book.getBookName()).isNotBlank().isEqualTo("Hello World.xlsx");
    }

    @Test
    public void getSheet_given_valid_sheetName_should_return_sheet() {
        Sheet sheet = book.getSheet("Sheet 2");
        assertThat(book.getSheet("Sheet 2")).isNotNull().isInstanceOf(PoiSheet.class);
        assertThat(sheet.getSheetName()).isEqualTo("Sheet 2");
        assertThat(sheet.getSheetIndex()).isEqualTo(1);
    }

    @Test
    public void getSheet_given_non_existent_sheetName_should_return_null() {
        assertThat(book.getSheet("Not here")).isNull();
    }

    @Test
    public void getSheet_given_valid_sheetIndex_should_return_sheet() {
        Sheet sheet = book.getSheet(2);
        assertThat(book.getSheet("Sheet 3")).isNotNull().isInstanceOf(PoiSheet.class);
        assertThat(sheet.getSheetName()).isEqualTo("Sheet 3");
        assertThat(sheet.getSheetIndex()).isEqualTo(2);
    }

    @Test
    public void getSheet_given_invalid_index_should_return_null() {
        assertThat(book.getSheet(23)).isNull();
    }

    @Test
    public void getSheet_given_negative_index_should_return_null() {
        assertThat(book.getSheet(-1)).isNull();
    }

    @Test
    public void getSheets_should_return_all_sheets() {
        assertThat(book.getSheets()).asList()
                .extracting("sheetIndex", "sheetName")
                .contains(tuple(0, "Sheet 1"),
                        tuple(1, "Sheet 2"),
                        tuple(2, "Sheet 3"));
    }

}