/*

The MIT License (MIT)

Copyright (c) 2015 Saabre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

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
