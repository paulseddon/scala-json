package io.pase

sealed trait Json

case class JSeq(elems: List[Json]) extends Json

case class JObj(bindings: Map[String, Json]) extends Json

case class JNum(num: Double) extends Json

case class JStr(str: String) extends Json

case class JBool(b: Boolean) extends Json

case object JNull extends Json

object Json {

  def toString(json: Json): String = json match {
    case JSeq(elems) => "[" + elems.map(toString(_)).mkString(", ") + "]"
    case JObj(bindings) =>
      val assocs = bindings.map { case (key, value) => "\"" + key + "\": " + toString(value)}
      "{" + (assocs mkString (", ")) + "}"
    case JStr(str) => "\"" + str + "\""
    case JNum(num) => num.toString
    case JBool(b) => b.toString
    case JNull => "null"
  }
}

object Start extends App {

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

  println(Json.toString(json))
}