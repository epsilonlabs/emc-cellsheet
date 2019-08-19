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

import com.google.common.base.Objects;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Token implements CellsheetElement {

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

    @Nonnull
    @Override
    public Iterator<Ast> iterator() {
        return usedBy.iterator();
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.TOKEN;
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.TOKEN, CellsheetType.CELLSHEET_ELEMENT);
    }

    @Nonnull
    @Override
    public String getId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equal(getValue(), token.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
