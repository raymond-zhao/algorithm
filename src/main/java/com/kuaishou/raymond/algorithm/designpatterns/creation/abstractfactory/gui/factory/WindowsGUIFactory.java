package com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.factory;

import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.buttons.WindowsButton;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.checkbox.CheckBox;
import com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.checkbox.WindowsCheckBox;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:06
 */
@Slf4j
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
