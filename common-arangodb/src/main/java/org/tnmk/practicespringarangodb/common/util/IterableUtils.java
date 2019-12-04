package org.tnmk.practicespringarangodb.common.util;

import java.util.StringJoiner;

/**
 * When working with ArangoRepository, usually the data type of the result list is {@link Iterable} which is not convenient to work with.
 * That's why we created some helper functions.
 */
public class IterableUtils {

    public static String toStringEachLine(String overviewMessage, Iterable<?> items) {
        String result = overviewMessage + "\n"+ toStringEachLine(items);
        return result;
    }

    public static String toStringEachLine(Iterable<?> items) {
        if (items == null) {
            return null;
        }
        StringJoiner stringJoiner = new StringJoiner(",\n");
        items.forEach(item -> {
            stringJoiner.add(item.toString());
        });
        return stringJoiner.toString();
    }
}
