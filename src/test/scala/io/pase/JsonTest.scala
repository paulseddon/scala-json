package io.pase

import org.scalatest._

class JsonTest extends FlatSpec with ShouldMatchers {

  "Json" should "create valid json string" in {

    val json = JObj(Map(
      "firstName" -> JStr("Paul"),
      "lastName" -> JStr("Seddon"),
      "address" -> JObj(Map(
        "town" -> JStr("London"),
        "postcode" -> JStr("SW18")
      )),
      "phone" -> JSeq(List(
        JObj(Map(
          "home" -> JStr("123-456")
        )),
        JObj(Map(
          "mob" -> JStr("987-654")
        ))
      )),
      "iq" -> JNum(99),
      "employed" -> JBool(false),
      "job" -> JNull
    ))

    Json.toString(json) should be === "{\"job\": null, \"employed\": false, \"lastName\": \"Seddon\", \"firstName\": \"Paul\", \"address\": {\"town\": \"London\", \"postcode\": \"SW18\"}, \"iq\": 99.0, \"phone\": [{\"home\": \"123-456\"}, {\"mob\": \"987-654\"}]}"
  }

}
