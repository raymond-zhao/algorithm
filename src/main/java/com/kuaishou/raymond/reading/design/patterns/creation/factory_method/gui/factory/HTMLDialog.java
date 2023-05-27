package com.kuaishou.raymond.reading.design.patterns.creation.factory_method.gui.factory;

import com.kuaishou.raymond.reading.design.patterns.creation.factory_method.gui.buttons.Button;
import com.kuaishou.raymond.reading.design.patterns.creation.factory_method.gui.buttons.HTMLButton;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:18
 */
public class HTMLDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HTMLButton();
    }
}
