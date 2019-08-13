package org.eclipse.epsilon.labs.emc.cellsheet;

import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Ast extends HasId {

    int UNASSIGNED = -1;

    Cell getCell();

    void setCell(Cell cell);

    Ast getParent();

    void setParent(Ast parent);

    Token getToken();

    void setToken(Token token);

    default void setToken(String token) {
        setToken(TokenFactory.getInstance().getToken(token));
    }

    default String getTokenValue() {
        return getToken().getValue();
    }

    Ast getRoot();

    int getPosition();

    void setPosition(int position);

    List<Ast> getChildren();

    Ast childAt(int position);

    void addChild(int position, Ast child);

    void addChild(Ast child);

    Ast removeChild(int position);

    Ast removeChild(Ast child);

    @Override
    Iterator<Ast> iterator();

    AstEval evaluate();

    void setEvaluator(AstEvaluator evaluator);

    String getFormula();

    default boolean isRoot() {
        return getPosition() == UNASSIGNED;
    }

    default boolean isLeaf() {
        return getChildren().isEmpty();
    }

    @Override
    default String getId() {
        if (getParent() == null) {
            return getCell().getId() + "/asts/" + getPosition();
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

    void accept(Visitor visitor);

    interface Visitor {

        void visit(Ast ast);

    }
}