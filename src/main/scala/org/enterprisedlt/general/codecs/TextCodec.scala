package org.enterprisedlt.general.codecs

/**
 * @author Alexey Polubelov
 */
trait TextCodec {

    def encode[T](value: T): String

    def decode[T](value: String, clz: Class[T]): T
}
