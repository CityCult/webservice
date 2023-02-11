package org.citycult.backend.io.serialization;

import org.citycult.backend.io.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author cpieloth
 */
public abstract class AStringSerializer<T> implements IStringSerializer<T> {

    private static final Logger log = LoggerFactory.getLogger(AStringSerializer.class);

    protected final Charset encoding;

    protected AStringSerializer() {
        this(Charset.UTF8);
    }

    protected AStringSerializer(Charset encoding) {
        this.encoding = encoding;
    }

    @Override
    public Charset getEncoding() {
        return encoding;
    }

    @Override
    public long serialize(OutputStream out, T obj) {
        final String s = serialize(obj);
        if (s == null)
            return 0;
        try {
            final byte[] b;
            b = s.getBytes(encoding.canonicalName());
            out.write(b);
            return b.length;
        } catch (UnsupportedEncodingException e) {
            log.error("Could not encode string, encoding: " + encoding, e);
            return 0;
        } catch (IOException e) {
            log.error("Error on writing bytes to stream.", e);
            return 0;
        }
    }

}
