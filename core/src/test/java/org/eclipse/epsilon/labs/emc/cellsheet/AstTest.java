/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;


import org.eclipse.epsilon.labs.emc.cellsheet.ast.Addition;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Text;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyAst;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyBook;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AstTest {

    private Ast ast;

    @Before
    public void setUp() {
        Workspace workspace = new Workspace();
        workspace.setName(AstTest.class.getSimpleName() + " Workspace");
        workspace.addBook(new DummyBook());
        Cell cell = workspace.getBooks().get(0).getSheet(0).getRow(0).getCell(0);
        ast = cell.getRoot();
    }

    @Test
    public void constructor_should_return_set_default_payload() {
        Ast ast = new Ast();
        AstPayload payload = ast.getPayload();
        assertThat(payload).isEqualTo(AstPayloads.empty());
    }

    @Test
    public void getId_should_return_id_when_is_root() {
        assertThat(ast.getId()).isEqualTo("cellsheet://AstTest%20Workspace/Default%20Dummy%20Book%201.xlsx/0/0/0/asts/root");
    }

    @Test
    public void getId_should_return_unassigned_when_dangling() {
        Ast ast = new Ast();
        assertThat(ast.getId()).isEqualTo(CellsheetElement.UNASSIGNED);
    }

    @Test
    public void getCell_should_return_root_cell_when_child() {
        Ast child = new Ast();
        ast.addChild(child);
        assertThat(child.getCell()).isSameAs(ast.getCell());
    }

    @Test
    public void getParent_should_return_null_when_ast_is_root() {
        assertThat(ast.getParent()).isNull();
    }

    @Test
    public void getParent_should_return_parent_when_ast_is_a_child() {
        Ast child = new Ast();

        ast.addChild(child);
        assertThat(ast.getChildren()).contains(child);
        assertThat(child.getParent()).isNotNull().isEqualTo(ast);
    }

    @Test
    public void getToken_should_return_payload_token() {
        ast.setPayload(new Text("Hello World"));
        assertThat(ast.getToken()).isEqualTo("Hello World");
    }

    @Test
    public void getRoot_should_return_self_when_ast_is_root() {
        assertThat(ast.getRoot()).isEqualTo(ast).isSameAs(ast);
    }

    @Test
    public void getRoot_should_return_root_when_ast_is_child() {
        Ast child = new DummyAst();
        assertThat(child.getRoot()).isEqualTo(child);
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);
        assertThat(ast.getChildren()).isEmpty();

        ast.addChild(child);
        assertThat(child.getRoot()).isEqualTo(ast);
        assertThat(ast.getChildren()).contains(child);
        assertThat(ast.getRoot()).isEqualTo(child.getRoot());
    }

    @Test
    public void getChildren_should_return_empty_given_defaults() {
        assertThat(ast.getChildren()).isEmpty();
    }

    @Test
    public void getChildren_should_return_immutable_list() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> ast.getChildren().clear());
    }

    @Test
    public void getChildren_should_return_children_in_order() {
        Ast a = new DummyAst();
        Ast b = new DummyAst();
        assertThat(ast.getChildren()).isEmpty();

        ast.addChild(a);
        ast.addChild(b);

        List<Ast> children = ast.getChildren();
        assertThat(children).containsExactly(a, b);
        assertThat(children).contains(a, atIndex(0));
        assertThat(children).contains(b, atIndex(1));
    }

    @Test
    public void childAt_should_return_child_at_given_index() {
        Ast a = new DummyAst();
        Ast b = new DummyAst();
        Ast c = new DummyAst();
        ast.addChild(a);
        ast.addChild(b);
        ast.addChild(c);

        assertThat(ast.childAt(0)).isEqualTo(a);
        assertThat(ast.childAt(1)).isEqualTo(b);
        assertThat(ast.childAt(2)).isEqualTo(c);
    }

    @Test
    public void addChild_should_add_ast_to_children_given_ast_and_no_other_children() {
        assertThat(ast.getChildren()).isEmpty();

        Ast child = new DummyAst();
        assertThat(child.getParent()).isNull();
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);
        assertThat(child.getRoot()).isEqualTo(child);

        ast.addChild(child);
        assertThat(ast.getChildren()).contains(child);
        assertThat(ast.childAt(0)).isEqualTo(child);
        assertThat(child.getParent()).isEqualTo(ast);
        assertThat(child.getRoot()).isEqualTo(ast);
    }

    @Test
    public void addChild_should_remove_old_parent() {
        Ast otherRoot = new DummyAst();
        Ast child = new DummyAst();
        otherRoot.addChild(child);
        assertThat(otherRoot.getChildren()).containsExactly(child);
        assertThat(ast.getChildren()).isEmpty();
        assertThat(child.getParent()).isEqualTo(otherRoot);

        ast.addChild(child);
        assertThat(otherRoot.getChildren()).isEmpty();
        assertThat(ast.getChildren()).containsExactly(child);
        assertThat(child.getParent()).isEqualTo(ast);
    }

    @Test
    public void addChild_should_shift_siblings_given_insertion_index_and_existing_children() {
        Ast a = new DummyAst();
        Ast b = new DummyAst();
        Ast c = new DummyAst();

        ast.addChild(a);
        ast.addChild(b);
        assertThat(ast.getChildren()).containsExactly(a, b).doesNotContain(c);
        assertThat(a.getPosition()).isEqualTo(0);
        assertThat(b.getPosition()).isEqualTo(1);

        ast.addChild(1, c);
        assertThat(ast.getChildren())
                .containsExactly(a, c, b)
                .extracting("position")
                .containsExactly(0, 1, 2);
    }

    @Test
    public void addChild_should_remove_old_parent_when_given_index() {
        Ast other = new Ast();
        Ast a = new Ast();
        Ast b = new Ast();
        Ast c = new Ast();

        ast.addChild(a);
        other.addChild(b);
        other.addChild(c);
        assertThat(a.getParent()).isEqualTo(ast);
        assertThat(b.getParent()).isEqualTo(other);
        assertThat(c.getParent()).isEqualTo(other);

        other.addChild(0, a);
        assertThat(other.getChildren())
                .containsExactly(a, b, c)
                .extracting("position", "parent")
                .containsExactly(
                        tuple(0, other),
                        tuple(1, other),
                        tuple(2, other));
        assertThat(a.getParent()).isNotEqualTo(ast);
    }

    @Test
    public void removeChild_should_remove_child_at_given_index() {
        Ast a = new DummyAst();
        Ast b = new DummyAst();
        Ast c = new DummyAst();
        ast.addChild(a);
        ast.addChild(b);
        ast.addChild(c);
        assertThat(ast.getChildren())
                .containsExactly(a, b, c)
                .extracting("position")
                .containsExactly(0, 1, 2);

        ast.removeChild(1);
        assertThat(ast.getChildren())
                .containsExactly(a, c)
                .doesNotContain(b)
                .extracting("position")
                .containsExactly(0, 1);
        assertThat(b.getParent()).isNull();
    }

    @Test
    public void getPosition_should_return_cell_position_value_when_root() {
        assertThat(ast.getPosition()).isEqualTo(0);
    }

    @Test
    public void getPosition_should_return_same_position_as_contained_in_list() {
        Ast child = new DummyAst();
        assertThat(child.getPosition()).isEqualTo(Ast.UNASSIGNED);

        ast.addChild(child);
        assertThat(child.getPosition()).isEqualTo(0);
        assertThat(ast.getChildren().get(0)).isEqualTo(child);
    }

    @Test
    public void isRoot_should_return_true_when_root() {
        assertThat(ast.isRoot()).isTrue();
    }

    @Test
    public void isRoot_should_return_false_when_is_child() {
        Ast a = new DummyAst();
        ast.addChild(a);
        assertThat(a.isRoot()).isFalse();
    }

    @Test
    public void isRoot_should_return_false_when_has_children() {
        ast.addChild(new DummyAst());
        assertThat(ast.isLeaf()).isFalse();
    }

    @Test
    public void isLeaf_should_return_true_when_no_children() {
        assertThat(ast.isLeaf()).isTrue();
    }

    @Test
    public void accept_should_execute_visitor() throws Exception {
        assertThat(ast.getToken()).isNotEqualTo("Visited");
        ast.accept(a -> {
            a.setPayload(new Text("Visited"));
            return Optional.empty();
        });
        assertThat(ast.getToken()).isEqualTo("Visited");
    }

    @Test
    public void evaluate_should_throw_exception_when_cell_is_null() {
        Ast ast = new Ast();
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ast.evaluate(mock(AstEvaluator.class)));
    }

    @Test
    public void evaluate_should_call_evaluator() {
        AstEvaluator evaluator = mock(AstEvaluator.class);
        ast.evaluate(evaluator);
        verify(evaluator, atLeast(1)).evaluate(ast);
    }

    @Test
    public void getPayloadType_should_return_payload_type() {
        ast.setPayload(new Addition());
        assertThat(ast.getPayloadType()).isEqualTo(CellsheetType.ADDITION);
    }
}
