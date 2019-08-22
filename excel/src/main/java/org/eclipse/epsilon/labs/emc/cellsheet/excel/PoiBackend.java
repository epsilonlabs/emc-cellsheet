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

import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetBackend;

import java.util.Set;

public class PoiBackend implements CellsheetBackend<PoiBook.Builder> {

    static final Set<String> EXT = Sets.newHashSet("xlsx", "xlsm");


    @Override
    public boolean isApplicable(String modelUri) {
        return EXT.contains(Files.getFileExtension(modelUri));
    }

    @Override
    public PoiBook.Builder getBuilder() {
        return new PoiBook.Builder();
    }
}
