# KeYmaera X as a Library Example Repository

[KeYmaera X](http://keymaerax.org) is an [open-source hybrid systems theorem prover](https://github.com/LS-Lab/KeYmaeraX-release) implemented in Scala. 
This repository demonstrates how to use KeYmaera X as a Scala library.

The easiest way to use KeYmaera X as a library is via the [Scala Build Tool](https://www.scala-sbt.org/).
Simply include the following line in your `build.sbt` file:

    libraryDependencies += "edu.cmu.cs.ls" % "keymaerax" % "4.4.2" from "https://github.com/LS-Lab/KeYmaeraX-release/releases/download/4.4.2/keymaerax.jar"

You may want to update `4.4.2` to the [latest released version](https://github.com/LS-Lab/KeYmaeraX-release/releases), and update the link following "`from`" in a corresponding way. Note that you can use `http://keymaerax.org/keymaerax.jar`, but that JAR file will always contain the latest release.

You may need to initialize printers, parsers, databases, tools, etc. The `Example.scala` file contains an example of how to do this.

# Common Errors

## Scala version mismatch

**Always make sure that the `scalaVersion` used in your project corresponds to the `scalaVersion` used by the KeYmaera X library you're importing.**

If you get the exception message

    java.lang.NoSuchMethodError: scala.Predef$.refArrayOps([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps

then this probably means you're using an older version of KeYmaera X that depends on Scala 2.11. 

One fix is to upgrade to a version of KeYmaera X that uses Scala 2.12 (4.4.3 or later).

Another fix is to force your project to use Scala 2.11. To do this, insert this line in your `build.sbt`:

    scalaVersion := "2.11.4"


## Version / JAR mismatch

If you get a runtime exception like:

    (run-main-0) java.lang.NoSuchMethodError: scala.Function1.$init$(Lscala/Function1;)V

then this probably means there's a mismatch between the version of KeYmaera X you want to use and the version contained in the JAR you're passing to SBT. 

Notice that we update the `keymaerax.org/keymaerax.jar` file with each version release, so if we update from `4.x.y` to  `4.x.z` then the import

    libraryDependencies += "edu.cmu.cs.ls" % "keymaerax" % "4.x.y" from "https://keymaerax.org/keymaerax.jar"

will fail with the above error message because the JAR file contains `4.x.z`.
