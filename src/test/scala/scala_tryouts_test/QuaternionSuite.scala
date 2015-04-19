package scala_tryouts_test

import scala.math.cos
import scala.math.sin
import scala.math.Pi
import org.scalatest.FunSuite
import scala_tryouts.Quaternion
import scalafx.scene.transform.Affine.sfxAffine2jfx
import scalafx.scene.transform.MatrixType
import scalafx.scene.transform.MatrixType.sfxEnum2jfx
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Rotate.sfxRotate2jfx
import scala_tryouts.Vector3D

class QuaternionSuite extends FunSuite {
  val f = 90.0 / 180 * Pi
  
  test("create a quaternion") {
    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0))
    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0).normalize)
    assert(
      Quaternion(sin(f / 2) *: Vector3D(1, 0, 0), cos(f / 2))
      ==
      Quaternion(sin(f / 2) *: Vector3D(1, 0, 0), cos(f / 2)).normalize
    )
  }
  
  test("x-axis: rotate 90 degrees") {
    assert(
      Quaternion(sin(f / 2) *: Vector3D(1, 0, 0), cos(f / 2))
      ==
      new Rotate(90, Rotate.XAxis)
    )
  }
  
  test("y-axis: rotate 90 degrees") {
    assert(
      Quaternion(sin(f / 2) *: Vector3D(0, 1, 0), cos(f / 2))
      ==
      new Rotate(90, Rotate.YAxis)
    )
  }
  
  test("z-axis: rotate 90 degrees") {
    assert(
      Quaternion(sin(f / 2) *: Vector3D(0, 0, 1), cos(f / 2))
      ==
      new Rotate(90, Rotate.ZAxis)
    )
  }
}
