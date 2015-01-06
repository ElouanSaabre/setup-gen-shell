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
import com.saabre.setup.console.listener.module.RemoteModuleListener;
import com.saabre.setup.console.listener.module.ScriptModuleListener;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Module;
import com.saabre.setup.system.controller.ConfigController;
import com.saabre.setup.system.controller.MainController;
import com.saabre.setup.module.remote.RemoteModule;
import com.saabre.setup.module.script.ScriptModule;
import java.util.LinkedList;
import java.util.List;

public class MainControllerListener extends ControllerListener implements MainController.Listener {

    // -- Attributes --
    
    MainController mainController;
    ConfigController configController;
    
    List<ControllerListener> controllerListenerList = new LinkedList<>();
    
    // -- Methods --
            
    public void run()
    {
        try 
        {
            output.title.println("  [ Server Setup Utility ]  ");
        
            mainController = new MainController();
            mainController.setListener(this);
            mainController.load();
            
            output.op.println("");
        
            mainController.run();
            
            output.title.println("  [ End ]  ");
        } 
        catch (Exception ex) 
        {
            output.error.println(ex.toString());
            ex.printStackTrace();
        }
    }
    
    @Override
    public void onConfigLoading(ConfigController controller) {
        configController = controller;
        controllerListenerList.add(new ConfigControllerListener(controller));
    }

    @Override
    public void onModuleStart(Module module) {
        
        output.title.println("Module : "+ NameHelper.upper(module.getName()));
        
        if(module instanceof RemoteModule)
            controllerListenerList.add(new RemoteModuleListener((RemoteModule) module));
        if(module instanceof ScriptModule)
            controllerListenerList.add(new ScriptModuleListener((ScriptModule) module));
    }

    @Override
    public void onModuleEnd() {
        //output.title.println("");
    }
    
}
