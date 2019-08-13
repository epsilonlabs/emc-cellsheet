package org.eclipse.epsilon.labs.emc.cellsheet;

import org.eclipse.epsilon.labs.emc.cellsheet.ast.AbstractAst;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AbstractAstTest {

    private AbstractAst root;

    @Before
    public void setUp() throws Exception {
        root = new DummyAst();
    }

    @Test
    public void getCell_should_return_null_when_root() {
        assertThat(root.getCell()).isNull();
        assertThat(root.getPosition()).isEqualTo(Ast.UNASSIGNED);
    }

    @Test
    public void getCell_should_return_root_ast_cell_when_child() {
        AbstractAst child = new DummyAst();
        assertThat(child.getParent()).isNull();
        assertThat(child.getCell()).isNull();

        root.addChild(child);
        assertThat(root.getCell()).isNull();
        assertThat(root.getChildren()).contains(child);
        assertThat(child.getCell()).isNull();
        assertThat(child.getParent()).isNotNull().isEqualTo(root);

        Cell cell = mock(Cell.class);
        root.setCell(cell);
        assertThat(root.getCell()).isNotNull().isEqualTo(cell);
        assertThat(child.getCell()).isNotNull().isEqualTo(cell);
    }

    @Test
    public void setCell_should_set_cell() {
        assertThat(root.getCell()).isNull();
        Cell cell = mock(Cell.class);
        root.setCell(cell);
        assertThat(root.getCell()).isNotNull().isEqualTo(cell);
    }

    @Test
    public void getParent_should_return_null_when_ast_is_root() {
        assertThat(root.getParent()).isNull();
    }

    @Test
    public void getParent_should_return_parent_when_ast_is_a_child() {
        AbstractAst child = new DummyAst();
        assertThat(child.getChildren()).isEmpty();
        assertThat(child.getParent()).isNull();

        root.addChild(child);
        assertThat(root.getChildren()).contains(child);
        assertThat(child.getParent()).isNotNull().isEqualTo(root);
    }

    @Test
    public void getToken_should_return_token() {
        assertThat(root.getToken()).isNotNull();
    }

    @Test
    public void getRoot_should_return_self_when_ast_is_root() {
        assertThat(root.getRoot()).isEqualTo(root);
    }

    @Test
    public void getRoot_should_return_root_when_ast_is_child() {
        AbstractAst child = new DummyAst();
        assertThat(child.getRoot()).isEqualTo(child);
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);
        assertThat(root.getChildren()).isEmpty();

        root.addChild(child);
        assertThat(child.getRoot()).isEqualTo(root);
        assertThat(root.getChildren()).contains(child);
        assertThat(root.getRoot()).isEqualTo(child.getRoot());
    }

    @Test
    public void getChildren_should_return_empty_given_defaults() {
        assertThat(root.getChildren()).isEmpty();
    }

    @Test
    public void getChildren_should_return_immutable_list() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> root.getChildren().clear());
    }

    @Test
    public void getChildren_should_return_children_in_order() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        assertThat(root.getChildren()).isEmpty();

        root.addChild(a);
        root.addChild(b);

        List<Ast> children = root.getChildren();
        assertThat(children).containsExactly(a, b);
        assertThat(children).contains(a, atIndex(0));
        assertThat(children).contains(b, atIndex(1));
    }

    @Test
    public void childAt_should_return_child_at_given_index() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        AbstractAst c = new DummyAst();
        root.addChild(a);
        root.addChild(b);
        root.addChild(c);

        assertThat(root.childAt(0)).isEqualTo(a);
        assertThat(root.childAt(1)).isEqualTo(b);
        assertThat(root.childAt(2)).isEqualTo(c);
    }

    @Test
    public void addChild_should_add_ast_to_children_given_ast_and_no_other_children() {
        assertThat(root.getChildren()).isEmpty();

        AbstractAst child = new DummyAst();
        assertThat(child.getParent()).isNull();
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);
        assertThat(child.getRoot()).isEqualTo(child);

        root.addChild(child);
        assertThat(root.getChildren()).contains(child);
        assertThat(root.childAt(0)).isEqualTo(child);
        assertThat(child.getParent()).isEqualTo(root);
        assertThat(child.getRoot()).isEqualTo(root);
    }

    @Test
    public void addChild_should_remove_old_parent() {
        AbstractAst otherRoot = new DummyAst();
        AbstractAst child = new DummyAst();
        otherRoot.addChild(child);
        assertThat(otherRoot.getChildren()).containsExactly(child);
        assertThat(root.getChildren()).isEmpty();
        assertThat(child.getParent()).isEqualTo(otherRoot);

        root.addChild(child);
        assertThat(otherRoot.getChildren()).isEmpty();
        assertThat(root.getChildren()).containsExactly(child);
        assertThat(child.getParent()).isEqualTo(root);
    }

    @Test
    public void addChild_should_shift_siblings_given_insertion_index_and_existing_children() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        AbstractAst c = new DummyAst();

        root.addChild(a);
        root.addChild(b);
        assertThat(root.getChildren()).containsExactly(a, b).doesNotContain(c);
        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(b.getPosition()).isEqualTo(1);

        root.addChild(1, c);
        assertThat(root.getChildren())
                .containsExactly(a, c, b)
                .extracting("position")
                .containsExactly(0, 1, 2);
    }

    @Test
    public void addChild_should_remove_old_parent_when_given_index() {
        AbstractAst otherRoot = new DummyAst();
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        AbstractAst c = new DummyAst();

        root.addChild(a);
        otherRoot.addChild(b);
        otherRoot.addChild(c);
        assertThat(a.getParent()).isEqualTo(root);
        assertThat(b.getParent()).isEqualTo(otherRoot);
        assertThat(c.getParent()).isEqualTo(otherRoot);

        otherRoot.addChild(0, a);
        assertThat(otherRoot.getChildren())
                .containsExactly(a, b, c)
                .extracting("position", "parent")
                .containsExactly(
                        tuple(0, otherRoot),
                        tuple(1, otherRoot),
                        tuple(2, otherRoot));
    }

    @Test
    public void removeChild_should_remove_child_at_given_index() {
        AbstractAst a = new DummyAst();
        AbstractAst b = new DummyAst();
        AbstractAst c = new DummyAst();
        root.addChild(a);
        root.addChild(b);
        root.addChild(c);
        assertThat(root.getChildren())
                .containsExactly(a, b, c)
                .extracting("position")
                .containsExactly(0, 1, 2);

        root.removeChild(1);
        assertThat(root.getChildren())
                .containsExactly(a, c)
                .doesNotContain(b)
                .extracting("position")
                .containsExactly(0, 1);
        assertThat(b.getParent()).isNull();
    }

    @Test
    public void getPosition_should_return_ROOT_value_when_root() {
        assertThat(root.getPosition()).isEqualTo(Ast.UNASSIGNED);
    }

    @Test
    public void getPosition_should_return_same_position_as_contained_in_list() {
        AbstractAst child = new DummyAst();
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);

        root.addChild(child);
        assertThat(child.getPosition()).isEqualTo(0);
        assertThat(root.getChildren().get(0)).isEqualTo(child);
    }

    @Test
    public void getKinds_should_contain_correct_types() {
        assertThat(root.getKinds()).containsExactlyInAnyOrder(CellsheetType.AST, CellsheetType.HAS_ID);
    }

    private class DummyAst extends AbstractAst {

        DummyAst() {
            super(UUID.randomUUID().toString());
        }
    }
}
