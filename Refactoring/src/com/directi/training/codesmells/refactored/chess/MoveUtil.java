/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Position;

public class MoveUtil {
    public static boolean isDiagonalMove(Position from, Position to) {
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
    }

    public static boolean isHorizontalOrVerticalMove(Position from, Position to) {
        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }

    public static boolean isStraightLineMove(Position from, Position to) {
        return MoveUtil.isDiagonalMove(from, to) || MoveUtil.isHorizontalOrVerticalMove(from, to);
    }
}
