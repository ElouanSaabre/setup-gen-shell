/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.listener.controller;

import com.saabre.setup.console.base.ControllerListener;
import com.saabre.setup.system.base.Module;
import com.saabre.setup.system.base.Operation;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.controller.ConfigController;

/**
 *
 * @author Lifaen
 */
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
