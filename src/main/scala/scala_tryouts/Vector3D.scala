package scala_tryouts

import scalafx.Includes._
import scalafx.geometry.Point3D
import javafx.{geometry => jfxg}
import scalafx.delegate.SFXDelegate

object Vector3D {
  implicit def sfxPoint3D2jfx(p: Point3D): jfxg.Point3D = if (p != null) p.delegate else null
  
  val Zero = new Vector3D(jfxg.Point3D.ZERO)
}

class Vector3D(override val delegate: jfxg.Point3D) extends Point3D(delegate) {
  def this(x: Double, y: Double, z: Double) = this(new jfxg.Point3D(x, y, z))
  def +(r: Vector3D) = new Vector3D(this.add(r))
  def -(r: Vector3D) = new Vector3D(this.subtract(r))
  def %(r: Vector3D) = new Vector3D(this.crossProduct(r))
  def *(l: Double)   = new Vector3D(this.multiply(l))
  def *:(r: Double)  = new Vector3D(this.multiply(r))
  def /(r: Double)   = new Vector3D(this.multiply(1 / r))
  def dot(r: Vector3D) = this.dotProduct(r)
  def norm           = new Vector3D(this.normalize())
}