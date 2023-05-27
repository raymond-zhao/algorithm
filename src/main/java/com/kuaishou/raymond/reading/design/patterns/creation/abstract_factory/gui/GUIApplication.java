package com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui;

import com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.buttons.Button;
import com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.factory.GUIFactory;

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
