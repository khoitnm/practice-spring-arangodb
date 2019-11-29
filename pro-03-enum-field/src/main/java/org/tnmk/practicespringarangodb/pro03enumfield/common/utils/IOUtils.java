package org.tnmk.practicespringarangodb.pro03enumfield.common.utils;

import java.io.InputStream;

public class IOUtils {
    private static InputStream validateExistInputStreamFromClassPath(final String path) {
        final InputStream inputStream = loadInputStreamFileInClassPath(path);
        if (inputStream == null) {
            throw new UnexpectedException(String.format("Not found file from '%s'", path));
        }
        return inputStream;
    }

    /**
     * @param path view more in {@link #loadBinaryFileInClassPath(String)}
     * @return
     */
    public static InputStream loadInputStreamFileInClassPath(final String path) {
        return IOUtils.class.getResourceAsStream(path);
    }
}
