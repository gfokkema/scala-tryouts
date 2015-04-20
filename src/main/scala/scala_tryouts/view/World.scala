package scala_tryouts.view

import scala_tryouts.Axes
import scala_tryouts.Water
import scala_tryouts.util.Camera
import scalafx.Includes._
import scalafx.scene.Group
import scalafx.scene.SceneAntialiasing
import scalafx.scene.SubScene
import scalafx.scene.transform.Scale
import scalafx.scene.transform.Translate

class World(camera_node: Camera) extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  val world_node = new Group {
    children = new Axes ::
    new Water {
      transforms = new Translate(0,  0,  0) :: new Scale(.1, .1, .1) :: Nil
    } :: Nil
  }
  root   = new Group(camera_node, world_node)
  camera = camera_node
  
  def addWater(x: Double, y: Double, z: Double) {
    world_node.children.add(new Water {
      transforms = new Translate(x,  y,  z) :: new Scale(.1, .1, .1) :: Nil
    }.delegate)
  }
}