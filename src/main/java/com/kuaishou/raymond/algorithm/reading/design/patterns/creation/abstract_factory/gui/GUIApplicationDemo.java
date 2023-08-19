package com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui;

import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.factory.GUIFactory;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.factory.MacOSGUIFactory;
import com.kuaishou.raymond.algorithm.reading.design.patterns.creation.abstract_factory.gui.factory.WindowsGUIFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-28 11:14
 */
@Slf4j
public class GUIApplicationDemo {

    private static GUIApplication configure() {
        GUIFactory guiFactory;

        String osName = System.getProperty("os.name").toLowerCase();
        log.info("os = {}", osName);
        if (osName.contains("mac")) {
            guiFactory = new MacOSGUIFactory();
        } else if (osName.contains("win")) {
            guiFactory = new WindowsGUIFactory();
        } else {
            throw new UnsupportedOperationException("Your system is not supported.");
        }

        return new GUIApplication(guiFactory);
    }

    public static void main(String[] args) {
        GUIApplication configuration = configure();
        configuration.paint();
    }

}
