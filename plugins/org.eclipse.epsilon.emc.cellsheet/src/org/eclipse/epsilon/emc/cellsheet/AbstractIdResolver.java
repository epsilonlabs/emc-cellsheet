package org.eclipse.epsilon.emc.cellsheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractIdResolver implements IIdResolver {

  public static final char BOOK_START = '[';
  public static final char BOOK_END = ']';
  public static final char LOCK = '$';
  public static final char SHEET_SEPERATOR = '!';

  static final Pattern BOOK_PATTERN = Pattern.compile("\\[(.+)\\]");
  static final Pattern SHEET_PATTERN = Pattern.compile("'(.+)'");

  static final String COL_GRP = "col";
  static final String ROW_GRP = "row";
  static final String LOCK_GRP = "lock";
  static final Pattern CELL_PATTERN = Pattern
      .compile("(?<" + COL_GRP + ">[a-zA-Z]+)(?<" + LOCK_GRP + ">[$]?)(?<" + ROW_GRP + ">\\d+)");



  @Override
  public String getId(IBook book) {
    return getId(book, null, null, null);
  }

  @Override
  public String getId(ISheet sheet) {
    return getId(sheet.getBook(), sheet, null, null);
  }

  @Override
  public String getId(IRow row) {
    return getId(row.getBook(), row.getSheet(), row, null);
  }

  @Override
  public String getId(ICell cell) {
    return getId(cell.getBook(), cell.getSheet(), cell.getRow(), cell);
  }

  @Override
  public HasType getElementById(IBook book, String id) {
    if (book == null) {
      throw new IllegalArgumentException();
    }

    // Book id
    String bookName = null;
    final Matcher bookMatcher = BOOK_PATTERN.matcher(id);
    final boolean hasBook = bookMatcher.find();
    if (hasBook) {
      bookName = bookMatcher.group(1);
      // Case where this is an id for a different book
      if (!bookName.equals(book.getName())) {
        return null; // TODO: we may want to change this once we support inter-workbook references
      }
      if (id.length() == bookMatcher.end()) {
        return book;
      }
    }

    // Sheet id
    String sheetName = null;
    final Matcher sheetMatcher = SHEET_PATTERN.matcher(id);
    if (sheetMatcher.find(hasBook ? bookMatcher.end() : 0)) {
      sheetName = sheetMatcher.group(1);
      if (id.length() == sheetMatcher.end()) {
        return book.getSheet(sheetName);
      }
    }

    // Cell or Row id
    if (sheetName != null) {
      final Matcher cellMatcher = CELL_PATTERN.matcher(id);
      if (cellMatcher.find()) {
        final String col = cellMatcher.group(COL_GRP);
        final int row = Integer.parseInt(cellMatcher.group(ROW_GRP)) - 1;
        if (cellMatcher.group(LOCK_GRP).isEmpty()) {
          return book.getCell(sheetName, row, col);
        } else {
          return book.getRow(sheetName, row);
        }
      }
    }

    throw new IllegalArgumentException("Bad ID format given: " + id);
  }

  protected String getId(IBook book, ISheet sheet, IRow row, ICell cell) {
    if (book == null)
      throw new IllegalArgumentException("All IDs must have at least the book");

    final StringBuilder sb = new StringBuilder();

    addBook(book, sb);

    if (sheet != null) {
      addSheet(sheet, sb);
    }

    if (cell != null) {
      addSheetSeperator(sb);
      addCell(cell, sb);
    } else if (row != null) {
      addSheetSeperator(sb);
      addRow(row, sb);
    }

    return sb.toString();
  }

  protected StringBuilder addBook(IBook book, StringBuilder sb) {
    startBook(sb);
    sb.append(book.getName());
    endBook(sb);
    return sb;
  }

  protected StringBuilder addSheet(ISheet sheet, StringBuilder sb) {
    return sb.append('\'').append(sheet.getName()).append('\'');
  }

  protected StringBuilder addRow(IRow row, StringBuilder sb) {
    sb.append("A");
    addLock(sb);
    return sb.append(row.getIndex() + 1);
  }

  protected StringBuilder addCell(ICell cell, StringBuilder sb) {
    return sb.append(cell.getCol()).append(cell.getRowIndex() + 1);
  }

  protected StringBuilder startBook(StringBuilder sb) {
    return sb.append(BOOK_START);
  }

  protected StringBuilder endBook(StringBuilder sb) {
    return sb.append(BOOK_END);
  }

  protected StringBuilder addSheetSeperator(StringBuilder sb) {
    return sb.append(SHEET_SEPERATOR);
  }

  protected StringBuilder addLock(StringBuilder sb) {
    return sb.append(LOCK);
  }
}
