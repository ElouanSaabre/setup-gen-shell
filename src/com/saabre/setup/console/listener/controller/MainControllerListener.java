/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Lifaen
 */
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
