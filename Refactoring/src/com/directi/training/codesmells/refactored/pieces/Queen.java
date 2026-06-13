/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.chess.MoveUtil;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class Queen
extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        return MoveUtil.isStraightLineMove(from, to);
    }

    public String toString() {
        return "q";
    }
}
