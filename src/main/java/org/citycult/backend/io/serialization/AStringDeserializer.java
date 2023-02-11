package org.citycult.backend.io.serialization;

import org.citycult.backend.io.Charset;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author cpieloth
 */
public abstract class AStringDeserializer<T> implements IStringDeserializer<T> {

    protected final Charset encoding;

    protected AStringDeserializer() {
        this(Charset.UTF8);
    }

    protected AStringDeserializer(Charset encoding) {
        this.encoding = encoding;
    }

    @Override
    public T serialize(InputStream in) {
        Scanner scanner = new Scanner(in, encoding.canonicalName());
        scanner.useDelimiter("\\A");
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext())
            sb.append(scanner.next());
        return serialize(sb.toString());
    }

    @Override
    public Charset getEncoding() {
        return encoding;
    }
}
