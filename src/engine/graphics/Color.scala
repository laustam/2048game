// DO NOT MODIFY FOR BASIC SUBMISSION
// scalastyle:off

package engine.graphics

/** Color in red green blue, where each color value is in the range 0-255 */
case class Color(red: Float, green: Float, blue: Float, alpha: Float) {

  // This is called on new Color(r, g, b)
  def this(red: Float, green: Float, blue: Float) = this(red, green, blue, 255)

}

/** Color companion object */
object Color {

  // This is called on Color(r, g, b) (without new)
  def apply(red: Float, green: Float, blue: Float): Color = new Color(red, green, blue)

  val COLOR_EMPTY: Color = Color(204, 192, 179)
  val COLOR_2: Color = Color(238, 228, 218)
  val COLOR_4: Color = Color(237, 224, 200)
  val COLOR_8: Color = Color(242, 177, 121)
  val COLOR_16: Color = Color(245, 149, 99)
  val COLOR_32: Color = Color(246, 124, 95)
  val COLOR_64: Color = Color(246, 94, 59)
  val COLOR_128: Color = Color(237, 207, 114)
  val COLOR_256: Color = Color(237, 204, 97)
  val COLOR_512: Color = Color(237, 200, 80)
  val COLOR_1024: Color = Color(237, 197, 63)
  val COLOR_2048: Color = Color(237, 194, 46)
  val Black: Color = Color(0, 0, 0)
  val Brown: Color = Color(74, 38, 0)
  val Grey: Color = Color(100, 100, 100)
}
