// DO NOT MODIFY FOR BASIC SUBMISSION
// scalastyle:off

package engine.graphics

/** Color in red green blue, where each color value is in the range 0-255 */
case class Color(red: Float, green: Float, blue: Float, alpha: Float) {

  // This is called on new Color(r, g, b)
  def this(red: Float, green: Float, blue: Float) = this(red, green, blue, 255)

  def linearInterpolation(l: Float, r: Float, t: Float): Float = (1 - t) * l + t * r

  def interpolate(fraction: Float, rhs: Color): Color =
    Color(linearInterpolation(red,   rhs.red,   fraction),
          linearInterpolation(green, rhs.green, fraction),
          linearInterpolation(blue,  rhs.blue,  fraction),
          linearInterpolation(alpha, rhs.alpha, fraction)
    )

}

/** Color companion object */
object Color {

  // This is called on Color(r, g, b) (without new)
  def apply(red: Float, green: Float, blue: Float): Color = new Color(red, green, blue)

  val COLOR_EMPTY = Color(204, 192, 179)
  val COLOR_2 = Color(238, 228, 218)
  val COLOR_4 = Color(237, 224, 200)
  val COLOR_8 = Color(242, 177, 121)
  val COLOR_16 = Color(245, 149, 99)
  val COLOR_32 = Color(246, 124, 95)
  val COLOR_64 = Color(246, 94, 59)
  val COLOR_128 = Color(237, 207, 114)
  val COLOR_256 = Color(237, 204, 97)
  val COLOR_512 = Color(237, 200, 80)
  val COLOR_1024 = Color(237, 197, 63)
  val COLOR_2048 = Color(237, 194, 46)
  val Black = Color(0,0,0)
  val Red = Color(255,0,0)
  val Brown = Color(74, 38, 0)
  val Grey = Color(100,100,100)
  val White = Color(0,0,0)

}
