package scala_tryouts

import scala.math.Pi
import scala.math.cos
import scala.math.sin
import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes._
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.Includes.jfxScene2sfx
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Group
import scalafx.scene.Scene
import scalafx.scene.SceneAntialiasing
import scalafx.scene.SubScene
import scalafx.scene.control.Button
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.transform.Affine.sfxAffine2jfx
import scalafx.scene.transform.Scale
import scalafx.scene.transform.Translate
import scalafx.stage.Stage
import scalafx.geometry.Point3D

class ScalaScene(camera_node: Camera) extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  val world_node = new Group {
    children = new Axes ::
    new Water {
      transforms = new Translate(0,  0,  0) :: new Scale(.1, .1, .1) :: Nil
    } :: Nil
//    new Water {
//      transforms = new Scale(.4, .4, .4) :: new Translate(0,  0, -1000) :: Nil
//    } ::
//    new Water {
//      transforms = new Scale(.4, .4, .4) :: new Translate(0,  1000,  0) :: Nil
//    } ::
//    new Water {
//      transforms = new Scale(.4, .4, .4) :: new Translate(0, -1000,  0) :: Nil
//    } :: Nil
  }
  root = new Group(camera_node, world_node)
  camera = camera_node
}

class ScalaHUD(outer : Stage) extends HBox {
  children = new Button {
    text = "Click me to close the dialog"
    onAction = handle { outer.close() }
  } ::
  new Button {
    text = "Click me to close the dialog"
    onAction = handle { outer.close() }
  } :: Nil
}

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
        center = new ScalaScene(camera_node)
        bottom = new ScalaHUD(outer)
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
      val mouse = new Point3D(1, 1, 0)
      val axis = mouse.normalize.crossProduct(0, 0, 1)
      camera_node.q *= new Quaternion(axis.multiply(sin(mouse.magnitude * .1)), cos(mouse.magnitude * .1))
    }
    scene.onMouseDragged = (me: MouseEvent) => {
      mouseOldX = mousePosX
      mouseOldY = mousePosY
      mousePosX = me.sceneX
      mousePosY = me.sceneY
      val mouse = new Point3D(mousePosX - mouseOldX, mousePosY - mouseOldY, 0)
      val axis = mouse.normalize.crossProduct(0, 0, 1)
      camera_node.q *= new Quaternion(axis.multiply(sin(mouse.magnitude * .01)), cos(mouse.magnitude * .01))
//      camera_node.rx.angle() -= mouseDeltaY
//      camera_node.ry.angle() += mouseDeltaX
    }
  }
}
