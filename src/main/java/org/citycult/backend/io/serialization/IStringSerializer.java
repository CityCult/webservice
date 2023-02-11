package org.citycult.backend.io.serialization;

import org.citycult.backend.io.Charset;

import java.io.OutputStream;

/**
 * Interface for a serialization of an object to a String, e.g. XML, JSON, HTML.
 *
 * @author cpieloth
 */
public interface IStringSerializer<T> {

    /**
     * Serializes an object to an output stream.
     *
     * @param out Stream to write to
     * @param obj Object to serialize
     * @return Bytes written
     */
    public long serialize(OutputStream out, T obj);

    /**
     * Serialize an object to a String.
     *
     * @param obj Object to serialize
     * @return Serialized object as String
     */
    public String serialize(T obj);

    /**
     * Returns the encoding which is used for output stream.
     *
     * @return Encoding
     */
    public Charset getEncoding();
}
