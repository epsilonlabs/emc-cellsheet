package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractIdResolver implements IdResolver {

	public static final String BOOK_START = "[";
	public static final String BOOK_END = "]";
	public static final String LOCK = "$";
	public static final String SHEET_SEPERATOR = "!";

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.emc.cellsheet.IdResolver#getId(org.eclipse.epsilon.emc.cellsheet.IBook)
	 */
	@Override
	public String getId(IBook book) {
		return getId(book, null, null, null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.emc.cellsheet.IdResolver#getId(org.eclipse.epsilon.emc.cellsheet.ISheet)
	 */
	@Override
	public String getId(ISheet sheet) {
		return getId(sheet.getBook(), sheet, null, null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.emc.cellsheet.IdResolver#getId(org.eclipse.epsilon.emc.cellsheet.IRow)
	 */
	@Override
	public String getId(IRow row) {
		return getId(row.getBook(), row.getSheet(), row, null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epsilon.emc.cellsheet.IdResolver#getId(org.eclipse.epsilon.emc.cellsheet.ICell)
	 */
	@Override
	public String getId(ICell cell) {
		return getId(cell.getBook(), cell.getSheet(), cell.getRow(), cell);
	}

	protected String getId(IBook book, ISheet sheet, IRow row, ICell cell) {
		final StringBuilder sb = new StringBuilder();

		startBook(sb);
		addBook(book, sb);
		endBook(sb);

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
		if (book == null)
			throw new IllegalArgumentException("All IDs must have at least the book");
		return sb.append("[").append(book.getName()).append("]");
	}

	protected StringBuilder addSheet(ISheet sheet, StringBuilder sb) {
		if (sheet == null)
			return sb;
		return sb.append(sheet.getName());
	}

	protected StringBuilder addRow(IRow row, StringBuilder sb) {
		if (row == null)
			return sb;
		sb.append("A");
		addLock(sb);
		return sb.append(row.getIndex() + 1);
	}

	protected StringBuilder addCell(ICell cell, StringBuilder sb) {
		if (cell == null)
			return sb;
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
