package scala_tryouts

import javafx.scene.transform.Scale
import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.handle
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.Includes.jfxScale2sfx
import scalafx.Includes.jfxScene2sfx
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Camera
import scalafx.scene.Group
import scalafx.scene.PerspectiveCamera
import scalafx.scene.Scene
import scalafx.scene.SceneAntialiasing
import scalafx.scene.SubScene
import scalafx.scene.control.Button
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.transform.Translate
import scalafx.stage.Stage

class ScalaScene(camera_node: Camera) extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  val world_node = new Group {
    children = new Water {
        transforms = new Scale(.4, .4, .4) :: new Translate( 500, 0, 0) :: Nil
      } ::
      new Water {
        transforms = new Scale(.4, .4, .4) :: new Translate(-500, 0, 0) :: Nil
      } :: Nil
    transforms = new Translate(0, 0, 1000) :: Nil
  }
  root = new Group(camera_node, world_node)
  camera = camera_node
}

class ScalaHUD(outer : Stage) extends HBox {
  children = Seq(
    new Button {
      text = "Click me to close the dialog"
      onAction = handle { outer.close() }
    },
    new Button {
      text = "Click me to close the dialog"
      onAction = handle { outer.close() }
    }
  )
}

object ScalaFXHelloWorld extends JFXApp {
  private var mousePosX: Double = .0
  private var mousePosY: Double = .0
  private var mouseOldX: Double = .0
  private var mouseOldY: Double = .0
  private var mouseDeltaX: Double = .0
  private var mouseDeltaY: Double = .0
  
  val camera_node = new PerspectiveCamera(true) {
    nearClip = 0.1
    farClip = 2000
    fieldOfView = 35
  }
  
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
    }
    scene.onMouseDragged = (me: MouseEvent) => {
      mouseOldX = mousePosX
      mouseOldY = mousePosY
      mousePosX = me.sceneX
      mousePosY = me.sceneY
      mouseDeltaX = mousePosX - mouseOldX
      mouseDeltaY = mousePosY - mouseOldY
      println("(" + mouseOldX + ", " + mouseOldY + ") -> (" + mousePosX + ", " + mousePosY + ")")
    }
  }
}
