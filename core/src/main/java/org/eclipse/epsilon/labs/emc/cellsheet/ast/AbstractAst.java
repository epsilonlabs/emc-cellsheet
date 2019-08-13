package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import com.google.common.collect.ForwardingList;
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractAst implements Ast {

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
        ToFormulaAstVisitor toFormulaAstVisitor = new ToFormulaAstVisitor();
        toFormulaAstVisitor.visit(this);
        return toFormulaAstVisitor.toString();
    }

    public void accept(Visitor visitor) {
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
