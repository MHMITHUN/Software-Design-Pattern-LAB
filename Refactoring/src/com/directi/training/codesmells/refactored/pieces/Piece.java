/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;

public abstract class Piece {
    private Color _color;

    public Piece(Color color) {
        this._color = color;
    }

    public Color getColor() {
        return this._color;
    }

    public abstract boolean isValidMove(Position var1, Position var2);
}
