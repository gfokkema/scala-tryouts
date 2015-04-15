package scala_tryouts

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Group
import scalafx.scene.PerspectiveCamera
import scalafx.scene.PointLight
import scalafx.scene.Scene
import scalafx.scene.SubScene
import scalafx.scene.control.Button
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.AntiqueWhite
import scalafx.scene.paint.Color.Black
import scalafx.scene.paint.Color.Blue
import scalafx.scene.paint.Color.LightBlue
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.PhongMaterial
import scalafx.scene.shape.Sphere
import scalafx.stage.Stage
import scalafx.scene.SceneAntialiasing
import scalafx.scene.transform.Rotate
import scalafx.scene.shape.Box
import scalafx.scene.paint.Color

class ScalaScene extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  camera = new PerspectiveCamera(false)
  val sphere = new Sphere(100.0) {
    material = new PhongMaterial {
      diffuseColor = Color.Blue
      specularColor = Color.White
    }
    translateX = 512
    translateY = 384
    translateZ = 0.0
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

object ScalaFXHelloWorld extends JFXApp {app=>
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
