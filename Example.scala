import edu.cmu.cs.ls.keymaerax._
import edu.cmu.cs.ls.keymaerax.core._
import edu.cmu.cs.ls.keymaerax.bellerophon.{BelleExpr, BelleInterpreter, BelleProvable, SequentialInterpreter}
import edu.cmu.cs.ls.keymaerax.btactics.{MathematicaToolProvider, ToolProvider}
import edu.cmu.cs.ls.keymaerax.hydra.HyDRAInitializer
import edu.cmu.cs.ls.keymaerax.launcher.DefaultConfiguration
import edu.cmu.cs.ls.keymaerax.pt.ProvableSig

import scala.collection.immutable.IndexedSeq

object Example {
  //Sets up the pretty printer
  PrettyPrinter.setPrinter(parser.KeYmaeraXPrettyPrinter.pp)

  //Configure Mathematica.
  val config = HyDRAInitializer.mathematicaConfig
  val mathLinkTcp = System.getProperty("MATH_LINK_TCPIP", "true") // JVM parameter -DMATH_LINK_TCPIP=[true,false]
  Configuration.set("MATH_LINK_TCPIP", mathLinkTcp, saveToFile = false)
  val provider = new MathematicaToolProvider(DefaultConfiguration.defaultMathematicaConfig)
  ToolProvider.setProvider(provider)

  //Setup the Bellerophon interpreter.
  BelleInterpreter.setInterpreter(SequentialInterpreter(Nil))

  def runTactic(f : Formula, e : BelleExpr) = {
    val v = BelleProvable(ProvableSig.startProof(f))
    BelleInterpreter.interpreter(e, v)
  }

  def main(argv : Array[String]) : Unit = {
    val interestingTheorem = Equal(Plus(Number(1),Number(1)), Number(2))
    val result = runTactic(interestingTheorem, btactics.TactixLibrary.QE)
    println(s"Calling quantifier elimination (QE) on ${interestingTheorem.prettyString} results in ${result.prettyString}")

    //Make sure to shutdown the tools or the system will hang.
    ToolProvider.shutdown()
  }


}
