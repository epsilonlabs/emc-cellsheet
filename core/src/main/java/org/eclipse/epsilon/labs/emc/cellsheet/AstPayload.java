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

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

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

    public static final String SCHEME = "cellsheet-payload";

    protected final String token;
    protected final String uuid;

    /**
     * Super constructor for subclasses, will automatically set the ID of this
     * token as well.
     *
     * @param token
     */
    protected AstPayload(String token) {
        checkNotNull(token);
        this.token = token.intern();
        this.uuid = tokenToUUID(token);
    }

    /**
     * Convert a token into a UUID
     *
     * @param token the token to convert, must not be {@code null}
     * @return UUID from given token
     */
    public static String tokenToUUID(String token) {
        checkNotNull(token);
        byte[] bytes = token.getBytes(StandardCharsets.UTF_8);
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        return uuid.toString();
    }

    /**
     * Returns the token value of this payload
     *
     * @return the token value
     */
    public String getToken() {
        return token;
    }

    @Override
    public String getId() {
        return SCHEME + "://" + getType().getTypeName().toLowerCase() + "/" + uuid;
    }

    /**
     * Returns supertype of this Payload
     * <p>Useful for categorising payloads</p>
     *
     * @return the supertype of this payload
     */
    public abstract CellsheetType getSuperType();

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
