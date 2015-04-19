package scala_tryouts

import scalafx.scene.PerspectiveCamera
import scalafx.scene.transform.Translate

class Camera extends PerspectiveCamera(true) {
  val focal = new Translate(0, 0, -500)  // move 500px backwards after applying rotation to create pivot in front
  val q     = Quaternion.Identity
  val t     = new Translate
  
  nearClip = 0.1
  farClip = 2000
  fieldOfView = 35
  
  transforms = t :: q :: focal :: Nil
}
