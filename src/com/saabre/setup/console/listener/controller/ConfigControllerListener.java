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

package com.saabre.setup.console.listener.controller;

import com.saabre.setup.console.base.ControllerListener;
import com.saabre.setup.system.base.Module;
import com.saabre.setup.system.base.Operation;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.controller.ConfigController;

public class ConfigControllerListener extends ControllerListener implements ConfigController.Listener{

    // -- Attributes --
    
    private ConfigController controller;

    // -- Constructor --
    
    public ConfigControllerListener(ConfigController controller) {
        this.controller = controller;
        this.controller.setListener(this);
    }
    
    // -- Methods --
    
    @Override
    public void onRootConfigStart() {
        output.op.print("Load config.yaml: ");
    }

    @Override
    public void onRootConfigEnd(int profileCount, int moduleCount) {
        output.op.append("Done, "+ profileCount +" profile(s) "
                + "and "+ moduleCount +" module(s) found.\n\n");
    }

    @Override
    public void onProfileConfigStart(String name) {
        output.op.println("Load profile."+ name +".yaml: ");
    }

    @Override
    public void onProfileConfigEnd(Profile profile) {
        output.op.println("Profile loaded !\n");
    }

    @Override
    public void onModuleConfigStart(String name) {
        output.op.print("Load module "+ name +": ");
    }
    
    @Override
    public void onModuleConfigMissingFile(String path) {
        output.op.append(path + ".yaml not found, but ");
    }

    @Override
    public void onModuleConfigEnd(Module module) {
        output.op.println("OK !");
    }

    @Override
    public void onOperationConfigStart(String type) {
        output.subOp.print(type +": ");
    }

    @Override
    public void onOperationConfigEnd(Operation operation) {
        output.subOp.append("OK !\n");
    }


    
    
}
