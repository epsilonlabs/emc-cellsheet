package org.eclipse.epsilon.labs.emc.cellsheet;

import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Ast<T extends Ast> extends HasId {

    Cell getCell();

    void setCell(Cell cell);

    T getParent();

    Token getToken();

    void setToken(Token token);

    int getPosition();

    void setPosition(int position);

    List<T> getChildren();

    void addChild(int position, T child);

    int addChild(T child);

    T removeChild(int position);

    @Override
    Iterator<T> iterator();

    String evaluate();

    String evaluate(AstEvaluator evaluator);

    void setEvaluator(AstEvaluator evaluator);

    @Override
    default String getId() {
        if (getParent() == null) {
            return getCell().getId() + "/asts/" + 0;
        } else {
            return getParent().getId() + "/" + getPosition();
        }
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.AST;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.AST, CellsheetType.HAS_ID);
    }

    void accept(Visitor<T> visitor);

    interface Visitor<T> {

        void visit(T ast);

    }
}