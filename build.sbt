name := "Fungus"

organization := "com.rumblesan"

version := "0.1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.specs2"    %% "specs2" % "2.3.3" % "test",
  "org.scalaz"    %% "scalaz-core" % "7.0.6",
  "org.scalaz"    %% "scalaz-effect" % "7.0.6",
  "org.scalaz"    %% "scalaz-typelevel" % "7.0.6",
  "org.scalaz"    %% "scalaz-scalacheck-binding" % "7.0.6" % "test",
  "com.rumblesan" %% "reaktor" % "0.1.0"
)

initialCommands := "import com.rumblesan.fungus._;import scalaz._, Scalaz._"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:_")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
