package org.eclipse.epsilon.labs.emc.cellsheet;

import org.eclipse.epsilon.labs.emc.cellsheet.ast.AbstractAst;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Function;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class AbstractAstTest {

    private AbstractAst root;

    @Before
    public void setUp() throws Exception {
        root = new DummyAst();
    }

    @Test
    public void getParent_should_return_null_when_ast_is_root() {
        assertThat(root.getParent()).isNull();
    }

    @Test
    public void getParent_should_return_parent_when_ast_is_a_child() {
        AbstractAst child = new DummyAst();
        root.addChild(child);
        assertThat(child.getParent()).isNotNull().isSameAs(root);
    }

    @Test
    public void addChild_given_ast_should_add_child_when_parent_is_empty() {
        assertThat(root.getParent()).isNull();
        assertThat(root.getChildren()).isEmpty();
        assertThat(root.getPosition()).isEqualTo(-1);

        AbstractAst child = new DummyAst();
        assertThat(child.getParent()).isNull();
        assertThat(root.getChildren()).isEmpty();
        assertThat(child.getPosition()).isEqualTo(-1);

        root.addChild(child);
        assertThat(child.getParent()).isSameAs(root);
        assertThat(child.getPosition()).isEqualTo(0);
        assertThat(child.getChildren()).isEmpty();
        assertThat(root.getChildren()).isNotEmpty().hasSize(1).contains(child);
    }

    @Test
    public void addChild_given_ast_should_throw_exception_when_child_has_existing_parent() {
        AbstractAst parent = new DummyAst();
        AbstractAst child = new DummyAst();
        parent.addChild(child);
        assertThat(child.getParent()).isSameAs(parent);
        assertThatIllegalStateException().isThrownBy(() -> root.addChild(child));
    }

    @Test
    public void addChild_given_ast_and_position_should_shift_existing_children() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        root.addChild(a);
        root.addChild(b);
        assertThat(a.getParent()).isSameAs(root);
        assertThat(b.getParent()).isSameAs(root);
        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(b.getPosition()).isEqualTo(1);

        AbstractAst c = new DummyAst();
        root.addChild(1, c);
        assertThat(c.getParent()).isSameAs(root);
        assertThat(c.getPosition()).isEqualTo(1);
        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(b.getPosition()).isEqualTo(2);
    }

    @Test
    public void removeChild_given_position_should_shift_existing_children_and_delete_successors() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        AbstractAst c = new DummyAst();

        AbstractAst d = new DummyAst();
        AbstractAst e = new DummyAst();

        root.addChild(a);
        root.addChild(b);
        root.addChild(c);
        assertThat(a.getParent()).isSameAs(root);
        assertThat(b.getParent()).isSameAs(root);
        assertThat(c.getParent()).isSameAs(root);
        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(b.getPosition()).isEqualTo(1);
        assertThat(c.getPosition()).isEqualTo(2);

        b.addChild(d);
        b.addChild(e);
        assertThat(d.getParent()).isSameAs(b);
        assertThat(e.getParent()).isSameAs(b);
        assertThat(d.getPosition()).isEqualTo(0);
        assertThat(e.getPosition()).isEqualTo(1);

        root.removeChild(1);

        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(c.getPosition()).isEqualTo(1);

        assertThat(b.getPosition()).isEqualTo(-1);
    }

    private class DummyAst extends AbstractAst {
    }
}
