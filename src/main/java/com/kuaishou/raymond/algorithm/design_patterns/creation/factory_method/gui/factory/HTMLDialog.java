package com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method.gui.factory;

import com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.design_patterns.creation.factory_method.gui.buttons.HTMLButton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:18
 */
@Slf4j
public class HTMLDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HTMLButton();
    }
}
