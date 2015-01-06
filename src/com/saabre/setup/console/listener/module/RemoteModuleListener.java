/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.listener.module;

import com.saabre.setup.console.base.ControllerListener;
import com.saabre.setup.console.base.OperationListener;
import com.saabre.setup.console.listener.operation.RunCommandListener;
import com.saabre.setup.console.listener.operation.SendFileListener;
import com.saabre.setup.operation.remote.RunCommand;
import com.saabre.setup.operation.remote.SendFile;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.remote.RemoteModule;
import com.saabre.setup.module.remote.RemoteOperation;

/**
 *
 * @author Lifaen
 */
public class RemoteModuleListener extends ControllerListener implements RemoteModule.Listener {
    
    // -- Attributes --
    
    private RemoteModule module;
    private Profile profile;
    private RemoteOperation operation;
    private OperationListener operationListener;
    
    // -- Constructor --
    
    public RemoteModuleListener(RemoteModule module)
    {
        this.module = module;
        this.module.setListener(this);
    }
    
    // -- Methods --

    @Override
    public void onTryingConnection() {
        output.op.println("Trying to connect to " 
                + module.getHost() + ":" + module.getPort() + "..."); 
    }

    @Override
    public void onConnected() {
        output.op.println("Connected.\n"); 
    }

    @Override
    public void onDisconnected() {
        output.op.println("Disconnected.\n");
    }

    @Override
    public void onProfileStart(Profile profile) {
        this.profile = profile;
        output.op.println("Process "+ profile.getName() +":");
    }

    @Override
    public void onProfileEnd() {
        output.op.println("Processed !\n");
    }

    @Override
    public void onOperationStart(RemoteOperation operation) {
        this.operation = operation;
        
        if(operation instanceof SendFile)
            operationListener = new SendFileListener((SendFile) operation);
        if(operation instanceof RunCommand)
            operationListener = new RunCommandListener((RunCommand) operation);
        
        try {
            output.subOp.println(operation.getType() + ": " + operation.getTitle());
        } catch (Exception ex) {
            // TODO Improve title system --
        }
    }

    @Override
    public void onOperationEnd() {
    }
    
}
