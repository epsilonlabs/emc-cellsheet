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

import org.eclipse.epsilon.labs.emc.cellsheet.BookProvider;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PoiBookProviderTest {

    private Workspace workspace;
    private BookProvider provider = PoiBookProvider.getInstance();

    @Test
    public void isApplicable_should_return_true_when_given_file_path_with_xlsx_extension() {
        assertThat(provider.isApplicable("/Some File 1.xlsx")).isTrue();
    }

    @Test
    public void isApplicable_should_return_true_when_given_file_path_with_xlsm_extension() {
        assertThat(provider.isApplicable("/Some File 1.xlsm")).isTrue();
    }

    @Test
    public void isApplicable_should_return_false_when_given_file_path_with_unsupported_extension() {
        assertThat(provider.isApplicable("/Some File 1.ods")).isFalse();
    }

    @Test
    public void isApplicable_should_return_false_when_given_file_with_no_extension() {
        assertThat(provider.isApplicable("random string fromToken stuff")).isFalse();
    }

    @Test
    public void supportsDefault_should_return_true() {
        assertThat(provider.supportsDefault()).isTrue();
    }
}