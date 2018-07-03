package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.assertj.core.api.iterable.Extractor;
import org.assertj.core.groups.Tuple;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ExcelRow}.
 * 
 * @author Jonathan Co
 *
 */
public class ExcelRowTest {

	ExcelBook book;
	ExcelRow row;

	@Before
	public void setup() throws Exception {
		this.book = ExcelTestUtil.getBook("ExcelRowTest.xlsx");
		this.row = this.book.getRow("ExcelRowTest", 3);
	}

	@Test
	public void getBook_should_return_correct_instance() throws Exception {
		assertThat(row.getBook()).isEqualTo(book);
	}

	@Test
	public void getIndex_should_return_correct_index() throws Exception {
		assertThat(row.getIndex()).isEqualTo(3);
	}

	@Test
	public void iterator_should_return_iterator_with_all_cells_in_row() throws Exception {
		assertThat(row.iterator()).extracting(
				new Extractor<ICell, Tuple>() {
					@Override
					public Tuple extract(ICell input) {
						return tuple(input.getRowIndex(), input.getColIndex());
						}
				})
		.contains(tuple(3, 0), tuple(3, 1), tuple(3, 2));
	}
	
	@Test
	public void cellIterator_should_return_iterator_with_all_cells_in_row() throws Exception {
		assertThat(row.cellIterator()).extracting(
				new Extractor<ICell, Tuple>() {
					@Override
					public Tuple extract(ICell input) {
						return tuple(input.getRowIndex(), input.getColIndex());
						}
				})
		.contains(tuple(3, 0), tuple(3, 1), tuple(3, 2));
	}
	
	@Test
	public void cells_should_return_List_with_all_cells_in_row() throws Exception {
		assertThat(row.cells()).extracting(
				new Extractor<ICell, Tuple>() {
					@Override
					public Tuple extract(ICell input) {
						return tuple(input.getRowIndex(), input.getColIndex());
						}
				})
		.contains(tuple(3, 0), tuple(3, 1), tuple(3, 2));
	}
	
	@Test
	public void getCell_should_return_cell_when_given_index() throws Exception {
		assertThat(row.getCell(2).getColIndex()).isEqualTo(2);
		assertThat(row.getCell(2).getRowIndex()).isEqualTo(3);
	}
}
