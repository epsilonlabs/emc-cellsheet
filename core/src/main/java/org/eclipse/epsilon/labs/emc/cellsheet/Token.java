package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Token implements HasId {

    private Workspace workspace;
    private String value;
    private Set<Ast> usedBy = new HashSet<>();

    public Token(String value) {
        this.value = value;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Ast> getUsedBy() {
        return usedBy;
    }

    public void addUsedBy(Ast ast) {
        usedBy.add(ast);
    }

    @Override
    public Iterator<Ast> iterator() {
        return usedBy.iterator();
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.TOKEN;
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.TOKEN, CellsheetType.HAS_ID);
    }

    @Override
    public String getId() {
        return "TOKEN ID NOT IMPLEMENTED";
    }
}
