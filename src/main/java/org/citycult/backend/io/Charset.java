package org.citycult.backend.io;

/**
 * @author cpieloth
 */
public enum Charset {

    ISO88591("ISO-8859-1", new String[]{"iso-8859-1"}), UTF8("UTF-8", new String[]{"utf-8"}), UTF16(
            "UTF-16", new String[]{"utf-16"}), DEFAULT(java.nio.charset.Charset.defaultCharset().name(), new String[]{java.nio.charset.Charset.defaultCharset().name()});

    private final String canonicalName;
    private final String[] aliases;

    private Charset(String canonicalName, String[] aliases) {
        this.canonicalName = canonicalName;
        this.aliases = aliases;
    }

    public String canonicalName() {
        return canonicalName;
    }

    public String[] aliases() {
        return aliases;
    }

    @Override
    public String toString() {
        return canonicalName;
    }
}
