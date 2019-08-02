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
