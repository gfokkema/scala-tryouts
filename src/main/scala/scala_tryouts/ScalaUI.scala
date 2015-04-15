package scala_tryouts

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.stage.Stage

class ScalaScene extends HBox {
  children = new Text {
    padding = Insets(20)
    text = "Hello"
    style = "-fx-font-size: 100pt"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(PaleGreen, SeaGreen))
  }
}

class ScalaHUD(outer: Stage) extends HBox {
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
  stage = new PrimaryStage {
    outer =>
    title = "ScalaFX Hello World"
    scene = new Scene {
      root = new BorderPane {
        style = "-fx-background-color: #000000"
        center = new ScalaScene
        bottom = new ScalaHUD(outer)
      }
    }
  }
}
