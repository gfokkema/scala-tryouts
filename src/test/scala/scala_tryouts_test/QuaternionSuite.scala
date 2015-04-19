package scala_tryouts_test

import scala.math.cos
import scala.math.sin

import org.scalatest.FunSuite

import scala_tryouts.Quaternion
import scalafx.scene.transform.Affine.sfxAffine2jfx
import scalafx.scene.transform.MatrixType
import scalafx.scene.transform.MatrixType.sfxEnum2jfx
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Rotate.sfxRotate2jfx

class QuaternionSuite extends FunSuite {
//  test("create a quaternion") {
//    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0))
//    assert(Quaternion(1, 0, 0, 0) == Quaternion(1, 0, 0, 0).normalize)
//  }
//  
//  test("x-axis: rotate 90 degrees") {
//    val f = 90.0 / 360 * Math.PI
//    assert(Quaternion(sin(f / 2), 0, 0, cos(f / 2)) == Quaternion(sin(f / 2), 0, 0, cos(f / 2)).normalize)
//    assert(new Rotate(90, Rotate.XAxis).toArray(MatrixType.MT_3D_4x4) == Quaternion(sin(f / 2), 0, 0, cos(f / 2)).toTransform.toArray(MatrixType.MT_3D_4x4))
//  }
}
