package org.citycult.backend.io.serialization;

import org.citycult.backend.io.Charset;

import java.io.InputStream;

/**
 * Interface for a deserialization of an object from a String, e.g. XML, JSON, HTML.
 *
 * @author cpieloth
 */
public interface IStringDeserializer<T> {

    /**
     * Deserializes a String to an object.
     *
     * @param in InputStream to read from.
     * @return Object or null.
     */
    public T serialize(InputStream in);

    /**
     * Deserializes a String to an object.
     *
     * @param str String to deserialize.
     * @return Object or null.
     */
    public T serialize(String str);

    /**
     * Returns the encoding which is used for output stream.
     *
     * @return Encoding
     */
    public Charset getEncoding();
}
