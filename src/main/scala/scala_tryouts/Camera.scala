package scala_tryouts

import scala.math.Pi
import scala.math.cos
import scala.math.sin

import scalafx.scene.PerspectiveCamera
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Translate

class Camera extends PerspectiveCamera(true) {
  val focal = new Translate(0, 0, -500)  // move 100px backwards after applying rotation to create pivot in front
  val q     = Quaternion(0, 0, 0, 1)
  val t     = new Translate
  
  val rx = new Rotate {
    axis = Rotate.XAxis
  }
  val ry = new Rotate {
    axis = Rotate.YAxis
  }
  val rz = new Rotate {
    axis = Rotate.ZAxis
  }
  
  nearClip = 0.1
  farClip = 2000
  fieldOfView = 35
  
  transforms = q :: focal :: Nil
}