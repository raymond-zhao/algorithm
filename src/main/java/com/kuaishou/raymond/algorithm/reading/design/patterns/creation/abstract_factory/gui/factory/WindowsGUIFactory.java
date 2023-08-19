package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.factory;

import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.buttons.WindowsButton;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.checkbox.WindowsCheckBox;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:06
 */
public class WindowsGUIFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
