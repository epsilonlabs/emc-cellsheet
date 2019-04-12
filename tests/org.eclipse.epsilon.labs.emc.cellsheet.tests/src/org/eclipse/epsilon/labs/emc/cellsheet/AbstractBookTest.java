package org.eclipse.epsilon.labs.emc.cellsheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.labs.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.labs.emc.cellsheet.AstType;
import org.eclipse.epsilon.labs.emc.cellsheet.CoreType;
import org.eclipse.epsilon.labs.emc.cellsheet.ElementType;
import org.eclipse.epsilon.labs.emc.cellsheet.HasId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractBookTest {

	private static final String NAME = "Expected";

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	AbstractBook book;

	@Before
	public void setup() {
		book.setName(NAME);
	}

	@Test
	public void getType_should_return_BOOK() throws Exception {
		assertEquals(CoreType.BOOK, book.getType());
	}

	@Test
	public void getKind_should_return_set_with_BOOK() throws Exception {
		final Set<ElementType> kinds = book.getKinds();
		assertEquals(1, kinds.size());
		assertEquals(CoreType.BOOK, kinds.iterator().next());
	}

	@Test
	public void getBook_should_return_self() throws Exception {
		assertEquals(book, book.getBook());
	}

	@Test
	public void hasType_should_return_true_when_given_valid_typename() throws Exception {
		assertTrue(book.hasType(CoreType.BOOK.getTypename()));
	}

	@Test
	public void hasType_should_return_false_when_given_bad_typename() throws Exception {
		assertFalse(book.hasType("Not a Type"));
	}

	@Test
	public void getTypeOf_should_return_type_when_given_HasType_instance() throws Exception {
		assertEquals(CoreType.BOOK, book.getTypeOf(book));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTypeOf_should_throw_exception_when_given_non_HasType() throws Exception {
		book.getTypeOf(this);
	}

	@Test
	public void getTypeNameOf_should_return_typename_when_given_HasType() throws Exception {
		assertEquals(CoreType.BOOK.getTypename(), book.getTypeNameOf(book));
	}

	@Test
	public void isOfType_should_return_true_when_given_Book_instance_and_type() throws Exception {
		assertTrue(book.isOfType(book, CoreType.BOOK));
		verify(book, atLeastOnce()).isOfType(book, CoreType.BOOK.getTypename());
	}

	@Test
	public void isOfType_should_return_false_when_given_Book_instance_and_Cell_type() throws Exception {
		assertFalse(book.isOfType(book, CoreType.CELL));
		verify(book, atLeastOnce()).isOfType(book, CoreType.CELL.getTypename());
	}

	@Test
	public void isOfKind_should_return_true_when_given_Book_instance_and_type() throws Exception {
		assertTrue(book.isOfKind(book, CoreType.BOOK));
		verify(book, atLeastOnce()).isOfKind(book, CoreType.BOOK.getTypename());
	}

	@Test
	public void isOfKind_should_return_false_when_Book_instance_and_Cell_type() throws Exception {
		assertFalse(book.isOfKind(book, CoreType.CELL));
		verify(book, atLeastOnce()).isOfKind(book, CoreType.CELL.getTypename());
	}

	@Test
	public void getAllTypeNamesOf_should_return_all_kinds_when_given_HasType_with_multiple_types() throws Exception {
		final Set<ElementType> kinds = new HashSet<>();
		kinds.add(CoreType.BOOK);
		kinds.add(AstSupertype.OPERATOR_INFIX);
		kinds.add(AstType.ADDITION);

		final HasId hasType = mock(HasId.class);
		when(hasType.getKinds()).thenReturn(kinds);

		Collection<String> typenames = book.getAllTypeNamesOf(hasType);
		assertTrue(typenames.contains(CoreType.BOOK.getTypename()));
		assertTrue(typenames.contains(AstSupertype.OPERATOR_INFIX.getTypename()));
		assertTrue(typenames.contains(AstType.ADDITION.getTypename()));
	}

	@Test
	public void getAllOfType_should_delegate_when_given_ElementType() throws Exception {
		book.getAllOfType(CoreType.BOOK);
		verify(book, atLeastOnce()).getAllOfType(CoreType.BOOK.getTypename());
	}

	@Test
	public void getAllOfKind_should_delegate_when_given_ElementType() throws Exception {
		book.getAllOfKind(CoreType.BOOK);
		verify(book, atLeastOnce()).getAllOfKind(CoreType.BOOK.getTypename());
	}

	@Test
	public void getCacheKeyForType_should_return_correct_ElementType_when_given_Book() throws Exception {
		assertEquals(CoreType.BOOK, book.getCacheKeyForType("Book"));
	}

	@Test
	public void isLoaded_should_return_false_when_called() throws Exception {
		assertFalse(book.isLoaded());
	}

	@Test
	public void getId_should_return_ID_String() throws Exception {
		assertEquals(NAME + "/", book.getId());
	}

	@Test
	public void getA1_should_return_A1_string() throws Exception {
		assertEquals("[" + NAME + "]", book.getA1());
	}

	@Test
	public void getHasIdOrThrow_should_return_element_when_given_HasType_instance() throws Exception {
		assertEquals(book, book.getHasIdOrThrow(book));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getHasIdOrThrow_should_throw_exception_when_given_non_HasType_instance() throws Exception {
		book.getHasIdOrThrow(this);
	}

	@Test
	public void getElementTypeOrThrow_should_return_ElementType_when_valid_typename() throws Exception {
		assertEquals(CoreType.BOOK, book.getElementTypeOrThrow("Book"));
	}

	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void getElementTypeOrThrow_should_throw_exception_when_given_bad_typename() throws Exception {
		assertEquals(CoreType.BOOK, book.getElementTypeOrThrow("BadName"));
	}

	/*
	 * STUBS - replace with real tests when they are properly implemented
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void getEnumerationValue_should_throw_USO() throws Exception {
		book.getEnumerationValue(null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setElementId_should_throw_USO() throws Exception {
		book.setElementId(null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void isInstantiable_should_throw_USO() throws Exception {
		book.isInstantiable(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void createInstanceInModel_should_throw_USO() throws Exception {
		book.createInstanceInModel(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void deleteElementInModel_should_throw_USO() throws Exception {
		book.deleteElementInModel(null);
	}
}
