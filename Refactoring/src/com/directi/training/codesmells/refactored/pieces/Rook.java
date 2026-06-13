/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.chess.MoveUtil;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class Rook
extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return MoveUtil.isHorizontalOrVerticalMove(from, to);
    }

    public String toString() {
        return "r";
    }
}
