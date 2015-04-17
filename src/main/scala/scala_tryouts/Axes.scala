package scala_tryouts

import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.PhongMaterial
import scalafx.scene.shape.Box
import scalafx.scene.transform.Translate

class Axes extends Group {
  children = new Box(240, 1, 1) {
    material = new PhongMaterial {
      diffuseColor = Color.Red
    }
    transforms = new Translate(120, 0, 0) :: Nil
  } ::
  new Box(1, 240, 1) {
    material = new PhongMaterial {
      diffuseColor = Color.Green
    }
    transforms = new Translate(0, 120, 0) :: Nil // apparently y-axis points down
  } ::
  new Box(1, 1, 240) {
    material = new PhongMaterial {
      diffuseColor = Color.Blue
    }
    transforms = new Translate(0, 0, 120) :: Nil // z-axis points into the screen
  } :: Nil 
}