package scala_tryouts

import scalafx.scene.Group
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.paint.PhongMaterial
import scalafx.scene.shape.Cylinder
import scalafx.scene.shape.Sphere
import scalafx.scene.transform.Rotate
import scalafx.scene.transform.Translate

class Hydrogen extends Group {
  val conn_mat = new PhongMaterial {
    diffuseColor = Color.LightBlue
    specularColor = Color.White
  }
  val hydro_mat = new PhongMaterial {
    diffuseColor = Color.Red
    specularColor = Color.White
  }
  
  children = {
    new Sphere(100) {
      material = hydro_mat
    } ::
    new Cylinder(25, 200) {
      material = conn_mat
      transforms = new Translate(200, 0, 0) :: new Rotate(90, Rotate.ZAxis) :: Nil
    } :: Nil
  }
}

class Water extends Group {
  val water_mat = new PhongMaterial {
    diffuseColor = Color.Blue
    specularColor = Color.White
  }
  
  val hydrogen1 = new Hydrogen
  val hydrogen2 = new Hydrogen
  val water = new Sphere(200) {
    material = water_mat
  }
  children = water :: hydrogen1 :: hydrogen2 :: Nil
  
  hydrogen1.transforms = new Rotate( -45, Rotate.ZAxis) :: new Translate(-500, 0, 0) :: Nil
  hydrogen2.transforms = new Rotate(-135, Rotate.ZAxis) :: new Translate(-500, 0, 0) :: Nil
}