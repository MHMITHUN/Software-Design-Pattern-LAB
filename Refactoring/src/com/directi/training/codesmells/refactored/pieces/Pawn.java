/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.pieces;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class Pawn
extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Position from, Position to) {
        int columnsMoved = Math.abs(to.getColumn() - from.getColumn());
        int rowsMoved = Math.abs(to.getRow() - from.getRow());
        return this.isForwardMove(from, to) && (columnsMoved <= 1 && rowsMoved == 1 || columnsMoved == 0 && rowsMoved == 2);
    }

    private boolean isForwardMove(Position from, Position to) {
        switch (this.getColor()) {
            case WHITE: {
                return to.getRow() < from.getRow();
            }
            case BLACK: {
                return to.getRow() > from.getRow();
            }
        }
        return false;
    }

    public boolean isValidMoveGivenContext(Position from, Position to, boolean atInitialPosition, boolean opponentPieceAtForwardLeft, boolean opponentPieceAtForwardRight) {
        return this.isForwardMove(from, to) && this.isTakingAllowedNumberOfForwardSteps(from, to, atInitialPosition) && this.isTakingAllowedNumberOfSidewaysSteps(from, to, opponentPieceAtForwardLeft, opponentPieceAtForwardRight);
    }

    private boolean isTakingAllowedNumberOfForwardSteps(Position from, Position to, boolean atInitialPosition) {
        int rowsAbsDiff = Math.abs(to.getRow() - from.getRow());
        return rowsAbsDiff > 0 && rowsAbsDiff <= (atInitialPosition ? 2 : 1);
    }

    private boolean isTakingAllowedNumberOfSidewaysSteps(Position from, Position to, boolean opponentPieceAtForwardLeft, boolean opponentPieceAtForwardRight) {
        int columnsDiff = to.getColumn() - from.getColumn();
        if (columnsDiff == -1) {
            return opponentPieceAtForwardLeft && this.getColor() == Color.WHITE || opponentPieceAtForwardRight && this.getColor() == Color.BLACK;
        }
        if (columnsDiff == 1) {
            return opponentPieceAtForwardRight && this.getColor() == Color.WHITE || opponentPieceAtForwardLeft && this.getColor() == Color.BLACK;
        }
        return columnsDiff == 0;
    }

    public String toString() {
        return "p";
    }
}
