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
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.script.ScriptModule;
import com.saabre.setup.module.script.ScriptOperation;

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
