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
  val xaxis = Quaternion(sin(f / 2) *: Vector3D(1, 0, 0), cos(f / 2))
  val yaxis = Quaternion(sin(f / 2) *: Vector3D(0, 1, 0), cos(f / 2))
  val zaxis = Quaternion(sin(f / 2) *: Vector3D(0, 0, 1), cos(f / 2))
  
  test("create a quaternion") {
    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0))
    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0).normalize)
    assert(xaxis == xaxis.normalize)
  }
  test("x-axis: rotate 90 degrees") {
    assert(xaxis == new Rotate(90, Rotate.XAxis))
  }
  test("y-axis: rotate 90 degrees") {
    assert(yaxis == new Rotate(90, Rotate.YAxis))
  }
  test("z-axis: rotate 90 degrees") {
    assert(zaxis == new Rotate(90, Rotate.ZAxis))
  }
  test("x-axis: rotate vector 90 degrees") {
    assert(xaxis * Vector3D(1, 0, 0) == Vector3D(1, 0, 0))
  }
  test("y-axis: rotate vector 90 degrees") {
    assert(yaxis * Vector3D(1, 0, 0) == Vector3D(0, 0, -1))
  }
  test("z-axis: rotate vector 90 degrees") {
    assert(zaxis * Vector3D(1, 0, 0) == Vector3D(0, 1, 0))
  }
  test("yx-axis: rotate vector 90 degrees") {
    assert(xaxis * yaxis * Vector3D(1, 0, 0) == Vector3D(0, 1, 0))
  }
}
