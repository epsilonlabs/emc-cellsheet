/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

   Modifications copyright (C) 2019 University of York
==================================================================== */
package org.eclipse.epsilon.labs.emc.cellsheet.poi;

/**
 * This exception thrown when a supplied formula has incorrect syntax (or syntax
 * currently not supported by POI). It is primarily used by test code to confirm
 * specific parsing exceptions. Application code should also handle this
 * exception if it potentially supplies formula of that is not guaranteed to
 * be well-formed.
 * <p>
 * This is a modification of the original {@link org.apache.poi.ss.formula.FormulaParseException}
 * that removes private and final modifiers
 *
 * @author Josh Micich
 */
@SuppressWarnings("serial")
public final class FormulaParseException extends RuntimeException {

    public FormulaParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormulaParseException(Throwable cause) {
        super(cause);
    }

    public FormulaParseException(String msg) {
        super(msg);
    }
}
