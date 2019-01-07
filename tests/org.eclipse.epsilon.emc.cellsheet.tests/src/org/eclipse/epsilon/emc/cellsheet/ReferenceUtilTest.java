package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <p>
 * Unit tests for {@link ReferenceUtil} utility class.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class ReferenceUtilTest {

	@Test
	public void convertColStringToIndex_should_return_0_when_given_A() throws Exception {
		assertEquals(0, ReferenceUtil.a1ToIndex("A"));
	}

	@Test
	public void convertColStringToIndex_should_return_25_when_given_Z() throws Exception {
		assertEquals(25, ReferenceUtil.a1ToIndex("Z"));
	}

	@Test
	public void convertColStringToIndex_should_return_26_when_given_AA() throws Exception {
		assertEquals(26, ReferenceUtil.a1ToIndex("AA"));
	}

	@Test
	public void convertColStringToIndex_should_return_702_when_given_AAA() throws Exception {
		assertEquals(702, ReferenceUtil.a1ToIndex("AAA"));
	}

	@Test
	public void convertColStringToIndex_should_return_30_when_given_dollarAE() throws Exception {
		assertEquals(30, ReferenceUtil.a1ToIndex("$AE"));
	}

	@Test(expected = Exception.class)
	public void convertColStringToIndex_should_return_throw_exception_when_given_bad_format() throws Exception {
		ReferenceUtil.a1ToIndex("A$E");
	}

	@Test
	public void colIndexToString_should_return_A_when_given_0() throws Exception {
		assertEquals("A", ReferenceUtil.indexToA1(0));
	}

	@Test
	public void colIndexToString_should_return_Z_when_given_25() throws Exception {
		assertEquals("Z", ReferenceUtil.indexToA1(25));
	}

	@Test
	public void colIndexToString_should_return_AA_when_given_26() throws Exception {
		assertEquals("AA", ReferenceUtil.indexToA1(26));
	}

	@Test
	public void colIndexToString_should_return_AE_when_given_702() throws Exception {
		assertEquals("AAA", ReferenceUtil.indexToA1(702));
	}

}
