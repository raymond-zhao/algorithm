package com.kuaishou.raymond.algorithm.designpatterns.creation.abstractfactory.gui.checkbox;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:09
 */
@Slf4j
public class MacOSCheckBox implements CheckBox {
    @Override
    public void paint() {
        log.info("You have created one MacOSCheckBox.");
    }
}
