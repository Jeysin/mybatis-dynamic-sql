/*
 *    Copyright 2016-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql.where.condition;

import java.util.Collection;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mybatis.dynamic.sql.AbstractListValueCondition;
import org.mybatis.dynamic.sql.Callback;
import org.mybatis.dynamic.sql.util.StringUtilities;

public class IsNotInCaseInsensitive extends AbstractListValueCondition<String, IsNotInCaseInsensitive> {

    protected IsNotInCaseInsensitive(Collection<String> values) {
        super(values, s -> s.map(StringUtilities::safelyUpperCase));
    }

    protected IsNotInCaseInsensitive(Collection<String> values, UnaryOperator<Stream<String>> valueStreamTransformer) {
        super(values, valueStreamTransformer);
    }

    protected IsNotInCaseInsensitive(Collection<String> values, UnaryOperator<Stream<String>> valueStreamTransformer,
            Callback emptyCallback) {
        super(values, valueStreamTransformer, emptyCallback);
    }

    @Override
    public String renderCondition(String columnName, Stream<String> placeholders) {
        return "upper(" + columnName + ") " + //$NON-NLS-1$ //$NON-NLS-2$
                placeholders.collect(
                        Collectors.joining(",", "not in (", ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public IsNotInCaseInsensitive withListEmptyCallback(Callback callback) {
        return new IsNotInCaseInsensitive(values, valueStreamTransformer, callback);
    }

    public static IsNotInCaseInsensitive of(Collection<String> values) {
        return new IsNotInCaseInsensitive(values);
    }
}
