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

import com.google.common.collect.Iterables;
import com.google.common.io.Resources;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.labs.emc.cellsheet.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PoiWorkspaceTest {

    private Workspace workspace;
    private StringProperties props;

    @Before
    public void setUp() {
        workspace = new Workspace();
        workspace.registerProvider(PoiBookProvider.getInstance());
        props = new StringProperties();
        props.setProperty(Workspace.PROPERTY_NAME, PoiWorkspaceTest.class.getSimpleName());
    }

    @Test
    public void load_should_create_in_memory_book_when_given_no_modelUri() throws Exception {
        assertThat(props.hasProperty(Workspace.PROPERTY_MODEL_URI)).isFalse();
        assertThat(workspace.getBooks()).isEmpty();

        workspace.load(props);
        assertThat(workspace.getBooks()).isNotEmpty().hasSize(1);
        Book book = Iterables.getOnlyElement(workspace.getBooks());
        assertThat(book.getModelUri()).isBlank();
    }

    @Test
    public void load_should_create_single_book_when_given_single_modelUri() throws Exception {
        String filename = "Sales Planning.xlsx";
        props.setProperty(Workspace.PROPERTY_MODEL_URI, getModelUriFromResource(filename));
        assertThat(workspace.getBooks()).isEmpty();

        workspace.load(props);
        assertThat(workspace.getBooks()).isNotEmpty().hasSize(1);
        Book book = Iterables.getOnlyElement(workspace.getBooks());
        assertThat(book.getModelUri()).isEqualTo(getModelUriFromResource(filename));
    }

    @Test
    public void load_should_create_multiple_books_when_given_multiple_modelUri() throws Exception {
        assertThat(workspace.getBooks()).isEmpty();

        Set<String> uris = new HashSet<>();
        uris.add(getModelUriFromResource("Sales Planning.xlsx"));
        uris.add(getModelUriFromResource("Empty.xlsx"));
        props.setProperty(Workspace.PROPERTY_MODEL_URI, uris.stream().collect(Collectors.joining(";")));

        workspace.load(props);
        assertThat(workspace.getBooks())
                .isNotEmpty()
                .hasSize(2)
                .extracting("modelUri")
                .containsExactlyInAnyOrder(uris.toArray());
    }

    @Test
    public void allContents_should_return_all_contents_when_given_real_book() throws Exception {
        props.setProperty(Workspace.PROPERTY_MODEL_URI, getModelUriFromResource("Sales Planning.xlsx"));
        workspace.load(props);
        workspace.allContents();
    }

    @Test
    public void allContents_should_return_all_contents_available() throws Exception {
        // Set up book
        PoiBook book = new PoiBook.Builder().build();
        workspace = new Workspace();
        workspace.setName("AllContents");
        workspace.addBook(book);
        workspace.load();

        Workbook delegate = book.getDelegate();
        delegate.createSheet("Sheet 1")
                .createRow(0)
                .createCell(0)
                .setCellFormula("1+2");

        // Checks
        Collection<CellsheetElement> elements = workspace.allContents();
        Sheet sheet = book.getSheet(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        Ast root = cell.getRoot();
        Ast childA = root.childAt(0);
        Ast childB = root.childAt(1);

        assertThat(elements).hasSize(8)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(workspace,
                        book,
                        sheet,
                        row,
                        cell,
                        root,
                        childA,
                        childB);
    }

    private String getModelUriFromResource(String resourceName) throws UnsupportedEncodingException {
        return new File(URLDecoder.decode(Resources.getResource(resourceName).getFile(), "UTF-8"))
                .getAbsolutePath();
    }
}
