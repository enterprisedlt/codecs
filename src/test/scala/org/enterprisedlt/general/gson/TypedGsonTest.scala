package org.enterprisedlt.general.gson

import com.google.gson.{Gson, GsonBuilder}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner



/**
 * @author Alexey Polubelov
 */
@RunWith(classOf[JUnitRunner])
class TypedGsonTest extends FunSuite {

    test("library should determine leaf type") {
        val codec: Gson = (new GsonBuilder).encodeTypes().create()
        val encoded = codec.toJson(Dummy("Hey", 1, 2))
        val decoded = codec.fromJson(encoded, classOf[DummyBase]) // check the type determined by adapter
        assert(decoded.isInstanceOf[Dummy])
        val dummy = decoded.asInstanceOf[Dummy]
        assert(dummy.someString == "Hey")
        assert(dummy.someInt == 1)
        assert(dummy.someFloat == 2)
    }

    test("library should determine Unit type") {
        val codec: Gson = (new GsonBuilder).encodeTypes().create()
        val x: Unit = ()
        val encoded = codec.toJson(x)
        println(encoded)
        val decoded: Any = codec.fromJson(encoded, classOf[Unit]) // check the type determined by adapter
//        assert(decoded.isInstanceOf[Unit])
        assert(decoded == ())
    }

    test("library should determine Boolean type") {
        val codec: Gson = (new GsonBuilder).encodeTypes().create()
        val x = true
        val encoded = codec.toJson(x)
        println(encoded)
        val decoded: Any = codec.fromJson(encoded, classOf[Boolean]) // check the type determined by adapter
        assert(decoded.isInstanceOf[Boolean])
        assert(decoded == true)
    }

}
