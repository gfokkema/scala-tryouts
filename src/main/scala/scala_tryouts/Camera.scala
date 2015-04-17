package scala_tryouts

import scalafx.scene.PerspectiveCamera
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Translate

class Camera extends PerspectiveCamera(true) {
  val focal = new Translate(0, 0, -1000)  // move 100px backwards after applying rotation to create pivot in front
  val t = new Translate
  val rx = new Rotate {
    axis = Rotate.XAxis
  }
  val ry = new Rotate {
    axis = Rotate.YAxis
  }
  
  nearClip = 0.1
  farClip = 2000
  fieldOfView = 35
  
  transforms = t :: rx :: ry :: focal :: Nil
}