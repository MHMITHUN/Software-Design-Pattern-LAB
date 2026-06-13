/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored;

import com.directi.training.codesmells.refactored.Direction;

public class Position {
    private final int _row;
    private final int _column;

    public Position(int row, int column) {
        this._row = row;
        this._column = column;
    }

    public int getRow() {
        return this._row;
    }

    public int getColumn() {
        return this._column;
    }

    public Position translatedPosition(Direction direction) {
        return new Position(this.getRow() + direction.getRowOffset(), this.getColumn() + direction.getColumnOffset());
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position)) {
            return false;
        }
        Position otherPosition = (Position)obj;
        return this == obj || this._row == otherPosition.getRow() && this._column == otherPosition.getColumn();
    }
}
