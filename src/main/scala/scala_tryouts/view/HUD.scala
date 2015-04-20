package scala_tryouts.view

import scalafx.Includes.handle
import scalafx.beans.property.DoubleProperty
import scalafx.beans.property.DoubleProperty.sfxDoubleProperty2jfx
import scalafx.beans.property.StringProperty.sfxStringProperty2jfx
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.layout.Priority
import scalafx.scene.layout.Region
import scalafx.stage.Stage
import scalafx.util.converter.NumberStringConverter
import scalafx.util.converter.NumberStringConverter.sfxNumberStringConverter2jfx

class HUD(world: World) extends HBox {
  var x, y, z = new DoubleProperty
  
  children = new HBox(10) {
    hgrow     = Priority.Always
    padding   = Insets(15, 12, 15, 12)
    style     = "-fx-background-color: #336699"
    
    children = new TextField {
      prefWidth = 100
      text.bindBidirectional(x, new NumberStringConverter)
    } ::
    new TextField {
      prefWidth = 100
      text.bindBidirectional(y, new NumberStringConverter)
    } ::
    new TextField {
      prefWidth = 100
      text.bindBidirectional(z, new NumberStringConverter)
    } ::
    new Button {
      text = "Add molecule"
      onAction = handle { world.addWater(x.value, y.value, z.value) }
    } ::
    new Region {
      hgrow = Priority.Always
    } :: Nil
  }
}