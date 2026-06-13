/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class King
extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return Math.abs(from.getRow() - to.getRow()) == 1 && Math.abs(from.getColumn() - to.getColumn()) == 1;
    }

    public String toString() {
        return "K";
    }
}
