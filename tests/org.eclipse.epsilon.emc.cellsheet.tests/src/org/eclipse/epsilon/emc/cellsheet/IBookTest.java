package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IBookTest {
	
	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();
	
	@Mock(answer = Answers.CALLS_REAL_METHODS)
	IBook book;
	
	@Test
	public void getTypeOf_should_return_Book_when_given_Book() throws Exception {
		assertEquals(Type.BOOK, book.getTypeOf(book));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getTypeOf_should_throw_IllegalArgumentExcpetion_when_given_unsupported_object() throws Exception {
		book.getTypeOf(this);
	}

	@Test
	public void getTypeNameOf_should_return_Book() throws Exception {
		assertEquals(Type.BOOK.getTypeName(), book.getTypeNameOf(book));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getTypeNameOf_should_throw_IllegalArgumentException_when_given_unsupported_object() throws Exception {
		assertEquals(Type.BOOK.getTypeName(), book.getTypeNameOf(this));
	}
	
	@Test
	public void isOfType_should_return_true_when_given_Book_Book_string() throws Exception {
		assertTrue(book.isOfType(book, "Book"));
	}
	
	@Test
	public void isOfType_should_return_false_when_given_Book_Cell_string() throws Exception {
		assertFalse(book.isOfType(book, "Cell"));
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void isOfType_should_throw_EolModelElementTypeNotFoundException_when_given_bad_type() throws Exception {
		book.isOfType(book, "NotAType");
	}
	
	@Test
	public void hasType_should_return_true_for_Book_string() throws Exception {
		assertTrue(book.hasType("Book"));
	}
	
	@Test
	public void hasType_should_return_false_for_invalid_type_string() throws Exception {
		assertFalse(book.hasType("Some string"));
	}
	
	@Test
	public void getType_should_return_TypeBook() throws Exception {
		assertEquals(Type.BOOK, book.getType());
	}
	
	@Test
	public void getKinds_should_contain_TypeBook() throws Exception {
		assertTrue(Arrays.asList(book.getKinds()).contains(Type.BOOK));
	}
	
}