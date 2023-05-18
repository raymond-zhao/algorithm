package com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method.gui;

import com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method.gui.factory.Dialog;
import com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method.gui.factory.HTMLDialog;
import com.kuaishou.raymond.algorithm.design.patterns.creation.factory_method.gui.factory.WindowsDialog;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:19
 */
public class DialogDemo {

    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    private static void runBusinessLogic() {
        dialog.renderWindow();
    }

    private static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HTMLDialog();
        }
    }

}
