/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.listener.module;

import com.saabre.setup.console.base.ControllerListener;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.script.ScriptModule;
import com.saabre.setup.module.script.ScriptOperation;

/**
 *
 * @author Lifaen
 */
public class ScriptModuleListener extends ControllerListener implements ScriptModule.Listener {

    // -- Attributes --
    
    private ScriptModule module;
    private Profile profile;
    private ScriptOperation operation;
    
    // -- Constructor --
    
    public ScriptModuleListener(ScriptModule module)
    {
        module.setListener(this);
    }
    
    // -- Methods --
    
    @Override
    public void onProfileStart(Profile profile) {
        output.op.println("Generate "+ profile.getName() +": ");
    }

    @Override
    public void onProfileEnd() {
        output.op.println("Profile generated !\n");
    }

    @Override
    public void onOperationStart(ScriptOperation operation) {
        this.operation = operation;
        output.subOp.print(operation.getType() + ": ");
    }

    @Override
    public void onOperationEnd() {
        output.subOp.append("OK !\n");
    }
    
}
