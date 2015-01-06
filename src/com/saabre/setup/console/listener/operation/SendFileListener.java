/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.console.listener.operation;

import com.saabre.setup.console.base.OperationListener;
import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.operation.remote.SendFile;
import java.io.File;

/**
 *
 * @author Lifaen
 */
public class SendFileListener extends OperationListener implements SendFile.Listener {

    // -- Constructor --
    
    public SendFileListener(SendFile operation)
    {
        operation.setListener(this);
    }
    
    // -- Methods --
    
    @Override
    public void onFileSent(File file) {
        String size = FileHelper.byteCountToReadable(file.length(), true);
        output.data.println(file.getName() +" sent (" + size +")");
    }
    
}
