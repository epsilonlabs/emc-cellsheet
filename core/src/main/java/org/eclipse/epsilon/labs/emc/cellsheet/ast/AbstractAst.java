package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;
import org.eclipse.epsilon.labs.emc.cellsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractAst implements Ast {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAst.class);

    protected Cell cell;
    protected Token token;
    protected Ast parent = null;
    protected List<Ast> children = new InternalAstList();
    protected int position = UNASSIGNED;
    protected AstEvaluator evaluator;

    protected AbstractAst() {
    }

    protected AbstractAst(Token token) {
        this.token = token;
    }

    protected AbstractAst(String token) {
        this(TokenFactory.getInstance().getToken(token));
    }

    @Override
    public Cell getCell() {
        return parent == null ? cell : parent.getCell();
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Ast getParent() {
        return parent;
    }

    @Override
    public void setParent(Ast parent) {
        this.parent = parent;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public Ast getRoot() {
        return parent == null ? this : parent.getRoot();
    }

    @Override
    public List<Ast> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public Ast childAt(int position) {
        return children.get(position);
    }

    @Override
    public void addChild(Ast child) {
        children.add(child);
    }

    @Override
    public void addChild(int position, Ast child) {
        children.add(position, child);
    }

    @Override
    public Ast removeChild(int position) {
        return children.remove(position);
    }

    @Override
    public Ast removeChild(Ast child) {
        return children.remove(child.getPosition());
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Iterator<Ast> iterator() {
        return children.iterator();
    }

    @Override
    public AstEval evaluate() {
        return evaluator.evaluate(this);
    }

    @Override
    public void setEvaluator(AstEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public String getFormula() {
        if (getChildren().isEmpty()) return token.getValue();
        try {
            ToFormulaAstVisitor toFormulaAstVisitor = new ToFormulaAstVisitor();
            toFormulaAstVisitor.visit(this);
            return toFormulaAstVisitor.toString();
        } catch (Exception e) {
            logger.error("Error reconstructing formula in AST {}", this);
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("cell", getCell())
                .add("parent", getParent())
                .add("isRoot", isRoot())
                .add("position", position)
                .add("token", getTokenValue())
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    class InternalAstList extends ForwardingList<Ast> {

        final List<Ast> delegate = new ArrayList<>(0);

        @Override
        public boolean add(Ast ast) {
            checkArgument(!delegate.contains(ast), "AST already present at index {}", ast.getPosition());
            if (ast.getParent() != null && !ast.getParent().equals(AbstractAst.this))
                ast.getParent().removeChild(ast);
            reindex();
            ast.setParent(AbstractAst.this);
            ast.setPosition(delegate.size());
            return super.add(ast);
        }

        @Override
        public void add(int index, Ast ast) {
            checkArgument(!delegate.contains(ast), "AST already present at index {}", ast.getPosition());
            super.add(index, ast);
            if (ast.getParent() != null && !ast.getParent().equals(AbstractAst.this))
                ast.getParent().removeChild(ast);
            ast.setParent(AbstractAst.this);
            reindex();
        }

        @Override
        public Ast remove(int index) {
            Ast removed = super.remove(index);
            resetParent(removed);
            reindex();
            return removed;
        }

        @Override
        protected List<Ast> delegate() {
            return delegate;
        }

        void reindex() {
            if (delegate.isEmpty()) return;
            for (int i = 0, n = delegate.size(); i < n; i++) delegate.get(i).setPosition(i);
        }

        void resetParent(Ast ast) {
            ast.setParent(null);
            ast.setPosition(Ast.UNASSIGNED);
        }
    }

}
