package scala_tryouts

import scala.math.cos
import scala.math.sin

import scala_tryouts.util.Camera
import scala_tryouts.view.HUD
import scala_tryouts.view.World
import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.Includes.jfxScene2sfx
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Point3D.sfxPoint3D2jfx
import scalafx.scene.Scene
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.MouseEvent.sfxMouseEvent2jfx
import scalafx.scene.layout.BorderPane

object ScalaUI extends JFXApp {
  private var mousePosX: Double = .0
  private var mousePosY: Double = .0
  private var mouseOldX: Double = .0
  private var mouseOldY: Double = .0
  private var mouseDeltaX: Double = .0
  private var mouseDeltaY: Double = .0
  private val camera_node = new Camera
  
  stage = new PrimaryStage {
    outer =>
    scene = new Scene {
      root = new BorderPane {
        title = "ScalaFX Tryouts"
        style = "-fx-background-color: #000000"
        center = new World(camera_node)
        bottom = new HUD(outer)
      }
    }
    handlemouse(scene())
  }
  
  def handlemouse(scene: Scene) = {
    scene.onMousePressed = (me: MouseEvent) => {
      mousePosX = me.sceneX
      mousePosY = me.sceneY
      mouseOldX = me.sceneX
      mouseOldY = me.sceneY
      println("(" + mousePosX + ", " + mousePosY + ")")
    }
    scene.onMouseDragged = (me: MouseEvent) => {
      mouseOldX = mousePosX
      mouseOldY = mousePosY
      mousePosX = me.sceneX
      mousePosY = me.sceneY
      val mouse = new Vector3D(mouseOldX - mousePosX, mouseOldY - mousePosY, 0)
      val axis = mouse.norm % new Vector3D(0, 0, 1)
      if (me.isPrimaryButtonDown)
        camera_node.q *= Quaternion(axis * sin(mouse.magnitude * .01), cos(mouse.magnitude * .01))
      if (me.isMiddleButtonDown) {
        val translation = camera_node.q * Vector3D(0, 0, 1) * mouse.y
        camera_node.t.x.value = camera_node.t.x.value + translation.x
        camera_node.t.y.value = camera_node.t.y.value + translation.y
        camera_node.t.z.value = camera_node.t.z.value + translation.z
      }
    }
  }
}
