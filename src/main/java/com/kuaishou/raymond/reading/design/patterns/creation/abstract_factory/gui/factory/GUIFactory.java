package com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.factory;

import com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.reading.design.patterns.creation.abstract_factory.gui.buttons.Button;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:05
 */
public interface GUIFactory {

    Button createButton();

    CheckBox createCheckBox();

}
