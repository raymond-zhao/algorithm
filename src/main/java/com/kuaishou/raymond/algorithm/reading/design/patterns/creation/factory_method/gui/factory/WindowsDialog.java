package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.factory_method.gui.factory;

import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.factory_method.gui.buttons.Button;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.factory_method.gui.buttons.WindowsButton;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:19
 */
public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
