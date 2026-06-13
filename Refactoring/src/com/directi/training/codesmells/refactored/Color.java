/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored;

public enum Color {
    WHITE("W"),
    BLACK("B");

    private String _colorString;

    private Color(String colorString) {
        this._colorString = colorString;
    }

    public String toString() {
        return this._colorString;
    }
}
