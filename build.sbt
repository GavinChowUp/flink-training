ThisBuild / resolvers ++= Seq(
  "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
  Resolver.mavenLocal,
)

name := "flink-training"

version := "0.1-SNAPSHOT"

organization := "com.training"

ThisBuild / scalaVersion := "2.12.12"

val flinkVersion = "1.13.1"

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala"           % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided",
)

lazy val root = (project in file(".")).settings(
  libraryDependencies ++= flinkDependencies,
)

assembly / mainClass := Some("com.training")

//https://docs.scala-lang.org/overviews/compiler-options/index.html
//https://tpolecat.github.io/
scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8",                         // Specify character encoding used by source files.
  "-explaintypes",                 // Explain type errors in more detail.
  "-feature",                      // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros", // Allow macro definition (besides implementation and application)
  "-language:higherKinds",         // Allow higher-kinded types
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-language:postfixOps",
  "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",                   // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfatal-warnings",              // Fail the compilation if there are any warnings.
  "-Xlint:adapted-args",           // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant",               // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select",     // Selecting member of DelayedInit.
  "-Xlint:doc-detached",           // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",           // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",              // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",   // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-unit",           // Warn when nullary methods return Unit.
  "-Xlint:option-implicit",        // Option.apply used implicit view.
  "-Xlint:package-object-classes", // Class or object defined in package object.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",         // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",            // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",  // A local type parameter shadows a type already in scope.
  "-Xlint:nonlocal-return",        // A return statement used an exception for flow control.
  "-Xlint:implicit-not-found",     // Check @implicitNotFound and @implicitAmbiguous messages.
  "-Xlint:serial",                 // @SerialVersionUID on traits and non-serializable classes.
  "-Xlint:valpattern",             // Enable pattern checks in val definitions.
  "-Xlint:eta-zero",               // Warn on eta-expansion (rather than auto-application) of zero-ary method.
  "-Xlint:eta-sam",                // Warn on eta-expansion to meet a Java-defined functional interface that is not explicitly annotated with @FunctionalInterface.
  "-Xlint:deprecation",            // Enable linted deprecations.
  "-Xfatal-warnings",              // Fail the compilation if there are any warnings.
  "-Ywarn-macros:after",           // Only inspect expanded trees when generating unused symbol warnings.
  "-Ywarn-octal-literal",          // Warn on obsolete octal syntax.
  "-Ywarn-unused:explicits",       // Warn if an explicit parameter is unused.
  "-Ywarn-dead-code",              // Warn when dead code is identified.
  "-Ywarn-extra-implicit",         // Warn when more than one implicit parameter section is defined.
  "-Ywarn-numeric-widen",          // Warn when numerics are widened.
  "-Ywarn-unused:implicits",       // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",         // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",          // Warn if a local definition is unused.
  "-Ywarn-unused:params",          // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars",         // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",        // Warn if a private member is unused.
  "-Ywarn-value-discard",          // Warn when non-Unit expression results are unused.
)

// make run command include the provided dependencies
Compile / run := Defaults.runTask(Compile / fullClasspath, Compile / run / mainClass, Compile / run / runner).evaluated

// stays inside the sbt console when we press "ctrl-c" while a Flink programme executes with "run" or "runMain"
Compile / run / fork := true
Global / cancelable := true

// exclude Scala library from assembly
assembly / assemblyOption := (assembly / assemblyOption).value.copy(includeScala = false)
