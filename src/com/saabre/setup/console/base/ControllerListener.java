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
public class ControllerListener {
    
    // -- Attributes --
    
    protected ControllerOutput output = new ControllerOutput();
    
    // -- Output class --
    
    public static class ControllerOutput extends Output
    {
        public Output.Channel op = new Output.Info();
        public Output.Channel subOp = new Output.Operation();
        public Output.Channel title = new Output.Title();
        public Output.Channel error = new Output.Error();
    }    
}
