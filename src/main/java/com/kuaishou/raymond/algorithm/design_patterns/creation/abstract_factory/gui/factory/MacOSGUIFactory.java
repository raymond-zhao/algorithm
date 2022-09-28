package com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory.gui.factory;

import com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory.gui.buttons.MacOSButton;
import com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.algorithm.design_patterns.creation.abstract_factory.gui.checkbox.MacOSCheckBox;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:05
 */
public class MacOSGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacOSCheckBox();
    }
}
