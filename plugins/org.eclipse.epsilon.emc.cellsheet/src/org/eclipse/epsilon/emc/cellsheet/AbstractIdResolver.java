package org.eclipse.epsilon.emc.cellsheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractIdResolver implements IIdResolver {

	public static final char BOOK_START = '[';
	public static final char BOOK_END = ']';
	public static final char LOCK = '$';
	public static final char SHEET_SEPERATOR = '!';

	Pattern p = Pattern.compile("([a-zA-Z]*)([0-9]*)");

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
		if (book == null) throw new IllegalArgumentException();
		
		// Get Book Part
		final int bookEnd = id.indexOf(BOOK_END);
		final String bookPart = id.substring(1, id.indexOf(BOOK_END));
		
		// Check if there is a sheet, return if there isn't
		final int sheetEnd = id.indexOf(SHEET_SEPERATOR);
		if (sheetEnd < 0) return book.getName().equals(bookPart) ? book : null;
		
		// Has a sheet part, check if it exists in current book
		final String sheetPart = id.substring(bookEnd + 1, sheetEnd);
		final ISheet sheet = book.getSheet(sheetPart);
		if (sheet == null) return null;
		
		// Get Row Part
		final int rowStart = id.indexOf(LOCK);
		if (rowStart > -1) {
			return sheet.getRow(Integer.parseInt(id.substring(rowStart)) - 1);
		}

		// Case the ID is not a row but is a cell 
		if (rowStart < 0 && id.length() > sheetEnd) {
			Matcher matcher = p.matcher(id.substring(sheetEnd + 1));
			if (matcher.matches()) {
				String column = matcher.group(1);
				int index = Integer.parseInt(matcher.group(2)) - 1;
				return sheet.getRow(index).getCell(column);
			}
		}
		
		return null;
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
		return sb.append(sheet.getName());
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
