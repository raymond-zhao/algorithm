package com.kuaishou.raymond.reading.design.patterns.creation.builder_pattern.components;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-09-29 09:55
 */
public class GPSNavigator {
    private String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute) {
        this.route = manualRoute;
    }

    public String getRoute() {
        return route;
    }
}
