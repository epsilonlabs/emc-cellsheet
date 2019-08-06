package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.net.UrlEscapers;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Book extends HasA1 {

    Workspace getWorkspace();

    void setWorkspace(Workspace workspace);

    List<CellFormat> getCellFormats();

    Sheet getSheet(int sheetIndex);

    Sheet getSheet(String sheetName);

    String getBookName();

    void setBookName(String bookName);

    String getModelUri();

    void setModelUri(String modelUri);

    void load() throws EolModelLoadingException;

    void dispose();

    List<Sheet> getSheets();

    @Override
    Iterator<Sheet> iterator();

    @Override
    default String getA1() {
        return "[" + getBookName() +"]";
    }

    @Override
    default String getId() {
        return getWorkspace().getId() + UrlEscapers.urlPathSegmentEscaper().escape(getBookName());
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.BOOK;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.BOOK, CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }

    interface Builder<T extends Book, B extends Builder<T, B>> extends CellsheetBuilder {

        B self();

        B withWorkspace(Workspace workspace);

        B withModelUri(String modelUri);

        T build();
    }
}