organization in ThisBuild := "com.fdelosrios"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `logistics` = (project in file("."))
  .aggregate(`containers-api`, `containers-impl`)

lazy val `containers-api` = (project in file("containers-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `containers-impl` = (project in file("containers-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`containers-api`)

//lazy val `ships-api` = (project in file("ships-api"))
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslApi
//    )
//  )
//
//lazy val `ships-impl` = (project in file("ships-impl"))
//  .enablePlugins(LagomScala)
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomScaladslPersistenceCassandra,
//      lagomScaladslKafkaBroker,
//      lagomScaladslTestKit,
//      macwire,
//      scalaTest
//    )
//  )
//  .settings(lagomForkedTestSettings)
//  .dependsOn(`ships-api`)
