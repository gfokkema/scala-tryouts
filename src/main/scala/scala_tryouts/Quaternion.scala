package scala_tryouts

import scalafx.Includes._
import scalafx.geometry.Point3D
import scalafx.geometry.Point3D._
import scalafx.scene.transform.Affine
import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.DoubleProperty
import scalafx.scene.transform.MatrixType

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

final class Quaternion(var v: Point3D, var w: Double) extends Affine {
  update
  
  // Create a quaternion from components
  def this(x: Double, y: Double, z: Double, w: Double) = this(new Point3D(x, y, z), w)

  // Multiply two quaternions
  def *=(q: Quaternion) = {
    w = w * q.w - v.dotProduct(q.v)
    v = new Point3D(w * q.v.x  +  v.x * q.w    +  v.y * q.v.z  -  v.z * q.v.y,
                    w * q.v.y  -  v.x * q.v.z  +  v.y * q.w    +  v.z * q.v.x,
                    w * q.v.z  +  v.x * q.v.y  -  v.y * q.v.x  +  v.z * q.w)
    update
  }
  
  // Scale a quaternion
  def *(f: Double) = new Quaternion(v.multiply(    f), w * f)
  def /(f: Double) = new Quaternion(v.multiply(1 / f), w / f)
  
  // Length
  def length             = math.sqrt(lengthSquared)
  def lengthSquared      = this dot this
  def normalize          = this / length
  def dot(q: Quaternion) = v.dotProduct(q.v) + w * q.w

//  // Rotation angle of this quaternion
//  def angle = 2.0 * math.acos(w)
//
//  // Rotation axis of this quaternion
//  def axis = v.normalize

  def update = {
    val length = this.length;
    v = v.multiply(1 / length)
    w = w / length
    
    // http://www.cprogramming.com/tutorial/3d/quaternions.html
    val xx = v.x * v.x; val yy = v.y * v.y; val zz = v.z * v.z
    val xy = v.x * v.y; val xz = v.x * v.z; val yz = v.y * v.z
    val wx = v.x * w; val wy = v.y * w; val wz = v.z * w
    
    mxx = 1 - 2 * yy - 2 * zz;  mxy =     2 * xy - 2 * wz;  mxz =     2 * xz + 2 * wy; tx = 0.0
    myx =     2 * xy + 2 * wz;  myy = 1 - 2 * xx - 2 * zz;  myz =     2 * yz - 2 * wx; ty = 0.0
    mzx =     2 * xz - 2 * wy;  mzy =     2 * yz + 2 * wx;  mzz = 1 - 2 * xx - 2 * yy; tz = 0.0

    println(this)
    println(super.toString())
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