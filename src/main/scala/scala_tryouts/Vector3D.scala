package scala_tryouts

import scala.math.abs

import javafx.{geometry => jfxg}
import scalafx.geometry.Point3D
import scalafx.geometry.Point3D.sfxPoint3D2jfx

object Vector3D {
  def apply(x: Double, y: Double, z: Double) = new Vector3D(x, y, z)
  
  val Zero = new Vector3D(jfxg.Point3D.ZERO)
}

class Vector3D(override val delegate: jfxg.Point3D) extends Point3D(delegate) {
  def this(x: Double, y: Double, z: Double) = this(new jfxg.Point3D(x, y, z))
  def +(r: Vector3D)   = new Vector3D(this.add(r))
  def -(r: Vector3D)   = new Vector3D(this.subtract(r))
  def %(r: Vector3D)   = new Vector3D(this.crossProduct(r))
  def *(l: Double)     = new Vector3D(this.multiply(l))
  def *:(r: Double)    = new Vector3D(this.multiply(r))
  def /(r: Double)     = new Vector3D(this.multiply(1 / r))
  def dot(r: Vector3D) = this.dotProduct(r)
  def norm             = new Vector3D(this.normalize())
  
  override def equals(that: Any) = that match {
    case p: Point3D => List((x, p.x), (y, p.y), (z, p.z)).foldLeft(true)((x, y) => x && abs(y._1 - y._2) < 1E-5)
    case _          => false
  }
}