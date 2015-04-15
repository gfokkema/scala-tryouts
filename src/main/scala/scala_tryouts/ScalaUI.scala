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
import scalafx.scene.shape.Cylinder
import scalafx.scene.shape.Sphere
import scalafx.scene.transform.Rotate
import scalafx.stage.Stage
import scalafx.scene.transform.Translate

class ScalaScene extends SubScene(1024, 768, true, SceneAntialiasing.Balanced) {
  val hydro_mat = new PhongMaterial {
    diffuseColor = Color.Red
    specularColor = Color.White
  }
  val conn_mat = new PhongMaterial {
    diffuseColor = Color.LightBlue
    specularColor = Color.White
  }
  val water_mat = new PhongMaterial {
    diffuseColor = Color.Blue
    specularColor = Color.White
  }
  
  def hydro() = {
    val hydro_sphere1 = new Sphere(100) {
      material = hydro_mat
    }
    val hydro_cylinder1 = new Cylinder(25, 200) {
      material = conn_mat
      translateX = 200
      rotationAxis = Rotate.ZAxis
      rotate = 90
    }
    new Group(hydro_sphere1, hydro_cylinder1)
  }
  
  def molecule() = {
    val water = new Sphere(200) {
      material = water_mat
    }
    
    val hydrogen1 = hydro()
    val hydrogen2 = hydro()
    val molecule  = new Group(water, hydrogen1, hydrogen2)
    
    hydrogen1.transforms = new Rotate( -45, Rotate.ZAxis) :: new Translate(-500, 0, 0) :: Nil
    hydrogen2.transforms = new Rotate(-135, Rotate.ZAxis) :: new Translate(-500, 0, 0) :: Nil
    molecule.transforms  = new Translate(0, -100, 500) :: Nil
    molecule
  }
  
  camera = new PerspectiveCamera(true) {
    translateZ = -1000
    nearClip = 0.1
    farClip = 2000
    fieldOfView = 35
  }
  root = molecule
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
