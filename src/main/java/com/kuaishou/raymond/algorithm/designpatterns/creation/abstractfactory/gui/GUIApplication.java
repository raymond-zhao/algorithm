package com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui;

import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.factory.GUIFactory;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:12
 */
public class GUIApplication {

    private Button button;

    private CheckBox checkBox;

    public GUIApplication(GUIFactory guiFactory) {
        this.button = guiFactory.createButton();
        this.checkBox = guiFactory.createCheckBox();
    }

    public void paint() {
        button.paint();
        checkBox.paint();
    }

}
