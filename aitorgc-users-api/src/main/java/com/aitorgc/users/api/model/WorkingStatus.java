package com.aitorgc.users.api.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Aitor Gil Callejo
 */
public enum WorkingStatus {
    OFFICE("En oficina", "BBBBC2"), TELEWORKING("Teletrabajo", "6f6edb"), HOLIDAYS("Vacaciones", "f04343");

    private final String text;
    private final String color;

    private WorkingStatus(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public static boolean exists(String value) {
        return Arrays.asList(WorkingStatus.values()).stream().map(ws -> ws.name()).collect(Collectors.toList()).contains(value);
    }

}
