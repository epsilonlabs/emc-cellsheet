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

import com.google.common.io.Files;

import java.util.Set;

/**
 * A provider for loading {@link Book} implementations by {@link Workspace}.
 * <p>
 * Each provider specifies a number of file extensions that it can load books for.
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface BookProvider<T extends Book> {

    /**
     * Returns whether this backend can be used as a default provider.
     *
     * @return {@code true} if can be used as a default
     */
    default boolean supportsDefault() {
        return true;
    }

    /**
     * Returns whether the given model URI is supported by this provider
     * <p>
     * If the given URI is null then will delegate to {@link #supportsDefault()}.
     * </p>
     *
     * @param modelUri the URI to check
     * @return {@code true} if this provider supports the URI with the given extension
     */
    default boolean isApplicable(String modelUri) {
        return modelUri == null
                ? supportsDefault()
                : getExtensions().contains(Files.getFileExtension(modelUri));
    }

    /**
     * Returns the set of file extensions that this provider can support
     *
     * @return file extensions supported by this provider
     */
    Set<String> getExtensions();

    /**
     * Returns a Book for the given URI
     * <p>The name of the book will be derived from the URI given</p>
     *
     * @param modelUri  URI of the actual book
     * @param workspace Workspace to assign created book to
     * @return the book pointed at the given URI
     */
    T buildBook(String modelUri, Workspace workspace);
}
