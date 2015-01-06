/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console;

import com.saabre.setup.console.listener.controller.MainControllerListener;
import com.saabre.setup.helper.FileHelper;

/**
 *
 * @author Lifaen
 */
public class SetupGenConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FileHelper.setRootFolder("../setup-gen/data/");
        
        MainControllerListener controller = new MainControllerListener();
        
        controller.run();
    }
    
}
