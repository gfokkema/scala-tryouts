// Name of the project
name := "ScalaFX Tryouts"

// Project version
version := "8.0.40-R8"

// Version of Scala used by the project
scalaVersion := "2.11.6"

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.40-R8"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true
