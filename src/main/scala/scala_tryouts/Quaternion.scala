package scala_tryouts

import scalafx.Includes.jfxPoint3D2sfx
import scalafx.geometry.Point3D
import scalafx.geometry.Point3D.sfxPoint3D2jfx
import scalafx.scene.transform.Affine

final class Quaternion (val v: Point3D, val w: Double) {
  // Create a quaternion from componentsx
  def this(x: Double, y: Double, z: Double, w: Double) = this(new Point3D(x, y, z), w)

  // Add two quaternions
  def +(q: Quaternion) = new Quaternion(v.add(q.v), w + q.w)

  // Subtract two quaternions
  def -(q: Quaternion) = new Quaternion(v.subtract(q.v), w - q.w)

  // Scale a quaternion
  def *(f: Double) = new Quaternion(v.multiply(    f), w * f)
  def /(f: Double) = new Quaternion(v.multiply(1 / f), w / f)

  // Dot product
  def *(q: Quaternion) = v.dotProduct(q.v) + w * q.w

  // Length
  def length = math.sqrt(lengthSquared)
  def lengthSquared = this * this

  // Normalize
  def normalize = this / length

  // Rotation angle of this quaternion
  def angle = 2.0 * math.acos(w)

  // Rotation axis of this quaternion
  def axis = v.normalize

  // Convert to a transform
  def toTransform = {
    val xx = v.x * v.x; val yy = v.y * v.y; val zz = v.z * v.z
    val xy = v.x * v.y; val xz = v.x * v.z; val yz = v.y * v.z
    val wx = v.x * w; val wy = v.y * w; val wz = v.z * w

    new Affine {
      mxx = 1.0 - 2.0 * (yy + zz); mxy = 2.0 * (xy + wz); mxz = 2.0 * (xz - wy); tx = 0.0
      myx = 2.0 * (xy - wz); myy = 1.0 - 2.0 * (xx + zz); myz = 2.0 * (yz + wx); ty = 0.0
      mzx = 2.0 * (xz + wy); mzy = 2.0 * (yz - wx); mzz = 1.0 - 2.0 * (xx + yy); tz = 0.0
    }
  }

  // Get an element by index
  def apply(index: Int): Double = index match {
    case 0 => v.x
    case 1 => v.y
    case 2 => v.z
    case 3 => w
    case _ => throw new IndexOutOfBoundsException(index.toString)
  }

  override def equals(that: Any) = that match {
    case Quaternion(v, w) => this.v == v && this.w == w
    case _                => false
  }
  override def toString = "Quaternion(%g, %g, %g, %g)" format (v.x, v.y, v.z, w)
}

object Quaternion {
  // Quaternion constants
  val Identity = new Quaternion(Point3D.Zero, 1.0)
  val Zero     = new Quaternion(Point3D.Zero, 0.0)

  // Create a quaternion
  def apply(v: Point3D, w: Double) = new Quaternion(v, w)

  // Create a quaternion from components
  def apply(x: Double, y: Double, z: Double, w: Double) = new Quaternion(new Point3D(x, y, z), w)

  // Extractor method
  def unapply(q: Quaternion) = Some(q.v, q.w)
}