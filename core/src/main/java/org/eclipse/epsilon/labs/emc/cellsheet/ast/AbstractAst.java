package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import com.google.common.collect.ForwardingList;
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractAst implements Ast<AbstractAst> {

    protected Cell cell;
    protected Token token;
    protected AbstractAst parent = null;
    protected List<AbstractAst> children = new InternalAstList();
    protected int position = ROOT;
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
    public AbstractAst getParent() {
        return parent;
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
    public List<AbstractAst> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public AbstractAst childAt(int position) {
        return children.get(position);
    }

    @Override
    public void addChild(AbstractAst child) {
        children.add(child);
    }

    @Override
    public void addChild(int position, AbstractAst child) {
        children.add(position, child);
    }

    @Override
    public AbstractAst removeChild(int position) {
        return children.remove(position);
    }

    @Override
    public AbstractAst removeChild(AbstractAst child) {
        return children.remove(child.position);
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Iterator<AbstractAst> iterator() {
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

    class InternalAstList extends ForwardingList<AbstractAst> {

        final List<AbstractAst> delegate = new ArrayList<>(0);

        @Override
        public boolean add(AbstractAst ast) {
            checkArgument(!delegate.contains(ast), "AST already present at index {}", ast.getPosition());
            if (ast.getParent() != null && !ast.getParent().equals(AbstractAst.this))
                ast.getParent().removeChild(ast);
            reindex();
            ast.parent = AbstractAst.this;
            ast.position = delegate.size();
            return super.add(ast);
        }

        @Override
        public void add(int index, AbstractAst ast) {
            checkArgument(!delegate.contains(ast), "AST already present at index {}", ast.getPosition());
            super.add(index, ast);
            if (ast.getParent() != null && !ast.getParent().equals(AbstractAst.this))
                ast.getParent().removeChild(ast);
            ast.parent = AbstractAst.this;
            reindex();
        }

        @Override
        public AbstractAst remove(int index) {
            AbstractAst removed = super.remove(index);
            resetParent(removed);
            reindex();
            return removed;
        }

        @Override
        protected List<AbstractAst> delegate() {
            return delegate;
        }

        void reindex() {
            if (delegate.isEmpty()) return;
            for (int i = 0, n = delegate.size(); i < n; i++) delegate.get(i).position = i;
        }

        void resetParent(AbstractAst ast) {
            ast.parent = null;
            ast.position = Ast.ROOT;
        }
    }

}
