package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.common.util.StringUtil;

/**
 * Wrapper for model element IDs in URI form.
 * 
 * IDs take the form of {@code book/sheet/row_index/col_index/} using a
 * zero-based index for row and column identifiers. For example
 * {@code Test Book.xlsx/Test Sheet/54/3/} refers to
 * {@code [Test Book.xlsx]'Test
 * Sheet'!D55}
 * 
 * @author Jonathan Co
 *
 */
public class ElementId {
	static final String DELIMITER = "/";

	static final int BOOK_IDX = 0;
	static final int SHEET_IDX = 1;
	static final int ROW_IDX = 2;
	static final int COL_IDX = 3;
//	static final int VALUE_IDX = 4;
//	static final int TREE_IDX = 5;
//	static final int TOKEN_IDX = 6;

	private String book;
	private String sheet;
	private int row;
	private int col;

	public ElementId() {
		this(null, null, -1, -1);
	}

	public ElementId(String book, String sheet, int row, int col) {
		this.book = book;
		this.sheet = sheet;
		this.row = row;
		this.col = col;
	}

	public static ElementId fromString(String id) {
		ElementId elementId = new ElementId();
		String[] parts = id.split(DELIMITER);

		for (int i = 0; i < parts.length; i++) {
			switch (i) {
			case BOOK_IDX:
				elementId.setBook(parts[BOOK_IDX]);
				break;
			case SHEET_IDX:
				elementId.setSheet(parts[SHEET_IDX]);
				break;
			case ROW_IDX:
				elementId.setRow(Integer.parseInt(parts[ROW_IDX]));
				break;
			case COL_IDX:
				elementId.setCol(Integer.parseInt(parts[COL_IDX]));
				break;
			default:
				throw new UnsupportedOperationException("ID for this element not implemented");
			}
		}

		return elementId;
	}
	
	public static String toString(String book, String sheet, int row, int col) {
		if (book == null && sheet == null) {
			throw new IllegalArgumentException("Must specify a book or a sheet");
		}

		StringBuilder sb = new StringBuilder();
		sb.append(book);
		sb.append(DELIMITER);

		if (sheet != null) {
			sb.append(sheet);
			sb.append(DELIMITER);
		}

		if (row > -1) {
			sb.append(row);
			sb.append(DELIMITER);

			if (col > -1) {
				sb.append(col);
				sb.append(DELIMITER);
			}
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return ElementId.toString(book, sheet, row, col);
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = StringUtil.isEmpty(book) ? null : book;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = StringUtil.isEmpty(sheet) ? null : sheet;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
