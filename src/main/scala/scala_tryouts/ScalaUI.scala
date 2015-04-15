package scala_tryouts

import scalafx.Includes.handle
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Group
import scalafx.scene.PerspectiveCamera
import scalafx.scene.Scene
import scalafx.scene.SceneAntialiasing
import scalafx.scene.SubScene
import scalafx.scene.control.Button
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.PhongMaterial
import scalafx.scene.shape.Sphere
import scalafx.stage.Stage

class ScalaScene extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  camera = new PerspectiveCamera(true) {
    translateZ = -1000
    nearClip = 0.1
    farClip = 2000
    fieldOfView = 35
  }
  
  val sphere = new Sphere(100) {
    material = new PhongMaterial {
      diffuseColor = Color.Blue
      specularColor = Color.White
    }
    translateX = 0
    translateY = 0
    translateZ = 100
  }
  
  root = new Group(sphere)
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
  private final val root = new Group
  private final val world = new Group
  
  stage = new PrimaryStage {
    outer =>
    scene = new Scene {
      root = new BorderPane {
        title = "ScalaFX Tryouts"
        style = "-fx-background-color: #000000"
        center = new ScalaScene
        bottom = new ScalaHUD(outer)
      }
    }
  }
}
