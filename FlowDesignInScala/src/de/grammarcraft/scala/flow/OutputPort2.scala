package de.grammarcraft.scala.flow

/**
 * Represents the one and only output port of function unit at flow design implementations in Scala.
 * Use this trait for function units with only one output port.
 * This is an restriction by convention an not checked.
 * 
 * @author kuniss@grammarcraft.de
 *
 */
trait OutputPort2[T] extends FunctionUnit { port =>
  
  private[this] var outputOperations: List[T => Unit] = List()
  
  /**
   * Lets the function unit output be processed by the given function closure.  
   */
  private def output2IsProcessedBy(operation: T => Unit) {
	  outputOperations = operation :: outputOperations
  }

  /**
   * Represents the function unit's second output port.
   * Helper object for syntactic sugar allowing to write connection down as
   * <i>fu.output2</i> -> <i>receiver</i>. See definition of value <i>output2</i>.
   */  
  val output2 = new Object {
	  def -> (operation: T => Unit) = port.output2IsProcessedBy(operation)
	  def isProcessedBy(operation: T => Unit) = port.output2IsProcessedBy(operation)
  }

  /**
   * The human readable name of this trait output port.
   * May be overridden by the function unit mixing in this trait. Default is "output2"
   */
  protected val OutputPort2Name = "output2"

  /**
   * Forwards the given message over the function units second output port to 
   * the function units connected to this port.
   */
  protected def forwardOutput2(msg: T) {
	  if (!outputOperations.isEmpty) {
	    outputOperations.foreach(operation => operation(msg))
	  }
	  else
	    forwardIntegrationError("nothing connected to port " + OutputPort2Name + " of functino unit " + this + ": '" + 
	        msg + "' could not be delivered") 
  }

}