package com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.factory;

import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.checkbox.CheckBox;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:05
 */
public interface GUIFactory {

    Button createButton();

    CheckBox createCheckBox();

}
