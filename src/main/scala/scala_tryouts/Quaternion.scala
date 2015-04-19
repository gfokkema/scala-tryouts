package scala_tryouts

import scala.math.abs

import scalafx.geometry.Point3D.sfxPoint3D2jfx
import scalafx.scene.transform.Affine
import scalafx.scene.transform.Affine.sfxAffine2jfx
import scalafx.scene.transform.MatrixType
import scalafx.scene.transform.MatrixType.sfxEnum2jfx
import scalafx.scene.transform.Transform
import scalafx.scene.transform.Transform.sfxTransform2jfx

object Quaternion {
  // Quaternion constants
  val Identity = new Quaternion(Vector3D.Zero, 1.0)
  val Zero     = new Quaternion(Vector3D.Zero, 0.0)

  // Create a quaternion
  def apply(v: Vector3D, w: Double)                     = new Quaternion(v, w)
  def apply(x: Double, y: Double, z: Double, w: Double) = new Quaternion(new Vector3D(x, y, z), w)

  // Extractor method
  def unapply(q: Quaternion) = Some(q.v, q.w)
}

final class Quaternion(var v: Vector3D, var w: Double) extends Affine {
  update
  
  // Create a quaternion from components
  def this(x: Double, y: Double, z: Double, w: Double) = this(new Vector3D(x, y, z), w)

  // Multiply two quaternions
  def *=(q: Quaternion) = {
    w = w * q.w - (v dot q.v)
    v = q.v * w + v * q.w + v % q.v
    update
  }
  
  // Scale a quaternion
  def *(f: Double) = new Quaternion(v * f, w * f)
  def /(f: Double) = new Quaternion(v / f, w / f)
  
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
    v = v / length
    w = w / length
    
    // http://www.cprogramming.com/tutorial/3d/quaternions.html
    val xx = v.x * v.x; val yy = v.y * v.y; val zz = v.z * v.z
    val xy = v.x * v.y; val xz = v.x * v.z; val yz = v.y * v.z
    val wx = v.x * w;   val wy = v.y * w;   val wz = v.z * w
    
    mxx = 1 - 2 * yy - 2 * zz;  mxy =     2 * xy - 2 * wz;  mxz =     2 * xz + 2 * wy; tx = 0.0
    myx =     2 * xy + 2 * wz;  myy = 1 - 2 * xx - 2 * zz;  myz =     2 * yz - 2 * wx; ty = 0.0
    mzx =     2 * xz - 2 * wy;  mzy =     2 * yz + 2 * wx;  mzz = 1 - 2 * xx - 2 * yy; tz = 0.0
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
    case tran: Transform  => ( this.toArray(MatrixType.MT_3D_4x4)
                               zip
                               tran.toArray(MatrixType.MT_3D_4x4)
                             ).foldLeft(true)((x, y) => x && abs(y._1 - y._2) < 1E-5)
    case _                => false
  }
  override def toString = "Quaternion(%g, %g, %g, %g)" format (v.x, v.y, v.z, w)
}
