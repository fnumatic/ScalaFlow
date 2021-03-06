package de.grammarcraft.scala.flow

/**
 * Represents the first input port for a function unit with multiple input ports 
 * at flow design implementations in Scala.
 * Use this trait for function units with two or more ports. 
 * This is an restriction by convention and not checked.
 * 
 * @author kuniss@grammarcraft.de
 */
trait InputPort1[T] {

  /**
   * Implements how input messages received at port <i>input1</i> are processed.
   * Must be implemented at the function unit applying this trait.
   */
  protected def processInput1(msg: T): Unit
  
  /**
   * The function unit's first input port.
   */
  def input1(msg: T) {
	  processInput1(msg)
  }

}