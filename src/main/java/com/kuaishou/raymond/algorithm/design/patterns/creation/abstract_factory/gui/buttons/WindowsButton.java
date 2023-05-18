package com.kuaishou.raymond.algorithm.design.patterns.creation.abstract_factory.gui.buttons;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:06
 */
@Slf4j
public class WindowsButton implements Button {
    @Override
    public void paint() {
        log.info("You have created one WindowsButton.");
    }
}
