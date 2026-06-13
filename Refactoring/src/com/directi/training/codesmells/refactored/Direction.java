/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored;

public class Direction {
    private final int _rowOffset;
    private final int _columnOffset;

    public Direction(int rowOffset, int columnOffset) {
        this._rowOffset = rowOffset;
        this._columnOffset = columnOffset;
    }

    public int getRowOffset() {
        return this._rowOffset;
    }

    public int getColumnOffset() {
        return this._columnOffset;
    }
}
