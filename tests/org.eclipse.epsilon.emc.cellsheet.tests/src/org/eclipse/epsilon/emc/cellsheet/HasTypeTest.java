package org.eclipse.epsilon.emc.cellsheet;

import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link HasType}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class HasTypeTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	HasType hasType;

	@Test
	public void getKinds_should_call_getType() throws Exception {
		hasType.getKinds();
		verify(hasType).getType();
	}
}
