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

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Model Type representing a the payload held within an {@link Ast}
 * <p>
 * Payloads reside outside of a {@link Workspace} to enable reference by
 * multiple workspaces and queries across multiple workspaces
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public abstract class AstPayload implements CellsheetElement {

    protected final String token;

    protected AstPayload(String token) {
        this.token = token.intern();
    }


    public String getToken() {
        return token;
    }

    @Override
    public String getId() {
        return "cellsheet-payloads://"
                + getType().getTypeName().toLowerCase()
                + "/" + token.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstPayload that = (AstPayload) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, getType());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("token", token)
                .add("id", getId())
                .toString();
    }

}
