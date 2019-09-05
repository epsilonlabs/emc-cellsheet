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

import com.google.common.collect.ImmutableSet;
import org.eclipse.epsilon.labs.emc.cellsheet.BookProvider;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;

import java.util.Set;

public class PoiBookProvider implements BookProvider<PoiBook> {

    static final Set<String> EXTENSIONS = ImmutableSet.of("xlsx", "xlsm");

    static final PoiBookProvider INSTANCE = new PoiBookProvider();

    private PoiBookProvider() {
    }

    public static PoiBookProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public Set<String> getExtensions() {
        return EXTENSIONS;
    }

    @Override
    public PoiBook buildBook(String modelUri, Workspace workspace) {
        return new PoiBook.Builder()
                .withModelUri(modelUri)
                .withWorkspace(workspace)
                .build();
    }
}
