/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.base;

/**
 *
 * @author Lifaen
 */
public class OperationListener {
  
    // -- Attributes --
    
    protected OperationOutput output = new OperationOutput();
    
    // -- Output class --
    
    
    public static class OperationOutput extends Output
    {
        public Output.Channel data = new Output.Data();
        public Output.Channel op = new Output.Operation();
        public Output.Channel error = new Output.Error();
    }
}
