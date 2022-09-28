package com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod.gui.factory;

import com.kuaishou.raymond.algorithm.designpatterns.creation.factorymethod.gui.buttons.Button;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 15:15
 */
public abstract class Dialog {

    public void renderWindow() {
        // ... other code ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Button createButton();
}
