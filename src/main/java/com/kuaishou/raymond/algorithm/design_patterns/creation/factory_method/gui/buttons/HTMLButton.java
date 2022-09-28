package com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method.gui.buttons;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:10
 */
@Slf4j
public class HTMLButton implements Button {
    @Override
    public void render() {
        log.info("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        log.info("Click! Button says - 'Hello World!'");
    }
}
