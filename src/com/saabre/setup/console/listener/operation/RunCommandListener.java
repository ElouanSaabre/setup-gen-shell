/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.listener.operation;

import com.saabre.setup.console.base.OperationListener;
import com.saabre.setup.operation.remote.RunCommand;

/**
 *
 * @author Lifaen
 */
public class RunCommandListener extends OperationListener implements RunCommand.Listener 
{
    // -- Constructor --
    
    public RunCommandListener(RunCommand operation)
    {
        operation.setListener(this);
    }
    
    // -- Methods --
    
    @Override
    public void onOutputNewLine(String line) {
        output.data.println(line);
    }

    @Override
    public void onExit(int exitStatus) {
        if(exitStatus < 0)
            output.data.println("Done, but exit status not set!");
        else if(exitStatus > 0)
            output.data.println("Done, but with error!");
        else
            output.data.println("Done!");
    }
    
}
