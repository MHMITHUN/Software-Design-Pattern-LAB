/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Direction;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.chess.Cell;
import com.directi.training.codesmells.refactored.chess.MoveUtil;
import com.directi.training.codesmells.refactored.pieces.Bishop;
import com.directi.training.codesmells.refactored.pieces.King;
import com.directi.training.codesmells.refactored.pieces.Knight;
import com.directi.training.codesmells.refactored.pieces.Pawn;
import com.directi.training.codesmells.refactored.pieces.Piece;
import com.directi.training.codesmells.refactored.pieces.Queen;
import com.directi.training.codesmells.refactored.pieces.Rook;

public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private final Cell[][] _board = new Cell[8][8];
    private boolean _kingDead;

    public ChessBoard() {
        this.initBoard();
        this.resetBoard();
    }

    private void initBoard() {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                Color color = (row + column) % 2 == 0 ? Color.WHITE : Color.BLACK;
                this._board[row][column] = new Cell(color);
            }
        }
    }

    public void resetBoard() {
        this.placePieces(Color.WHITE);
        this.placePieces(Color.BLACK);
        this._kingDead = false;
    }

    private void placePieces(Color color) {
        int otherPiecesRow;
        int pawnsRow;
        switch (color) {
            case WHITE: {
                pawnsRow = 6;
                otherPiecesRow = 7;
                break;
            }
            case BLACK: {
                pawnsRow = 1;
                otherPiecesRow = 0;
                break;
            }
            default: {
                System.err.println("Unexpected color passed to placePieces.");
                return;
            }
        }
        this.placeOtherPieces(otherPiecesRow, color);
        this.placePawns(pawnsRow, color);
    }

    private void placePawns(int row, Color color) {
        for (int column = 0; column < 8; ++column) {
            this._board[row][column].setPiece(new Pawn(color));
        }
    }

    private void placeOtherPieces(int row, Color color) {
        for (int column = 0; column < 8; ++column) {
            Piece piece = null;
            if (column == 0 || column == 7) {
                piece = new Rook(color);
            } else if (column == 1 || column == 6) {
                piece = new Knight(color);
            } else if (column == 2 || column == 5) {
                piece = new Bishop(color);
            } else if (column == 3) {
                piece = new King(color);
            } else if (column == 4) {
                piece = new Queen(color);
            }
            this._board[row][column].setPiece(piece);
        }
    }

    private boolean isPositionOutOfBounds(Position position) {
        return position.getRow() < 0 || position.getRow() >= 8 || position.getColumn() < 0 || position.getColumn() >= 8;
    }

    public boolean isEmpty(Position position) {
        return this.isPositionOutOfBounds(position) || this.getCell(position).isEmpty();
    }

    private Cell getCell(Position position) {
        return this._board[position.getRow()][position.getColumn()];
    }

    public Piece getPiece(Position position) {
        return this.isEmpty(position) ? null : this.getCell(position).getPiece();
    }

    public boolean isValidMove(Position from, Position to) {
        return !(from.equals(to) || this.isPositionOutOfBounds(from) || this.isPositionOutOfBounds(to) || this.isEmpty(from) || !this.isEmpty(to) && this.getPiece(from).getColor() == this.getPiece(to).getColor() || !this.getPiece(from).isValidMove(from, to) || !this.hasNoPieceInPath(from, to) || this.getPiece(from) instanceof Pawn && !this.isValidPawnMove(from, to));
    }

    private boolean hasNoPieceInPath(Position from, Position to) {
        if (this.getPiece(from) instanceof Knight) {
            return true;
        }
        if (!MoveUtil.isStraightLineMove(from, to)) {
            return false;
        }
        Direction direction = new Direction(this.cappedCompare(to.getRow(), from.getRow()), this.cappedCompare(to.getColumn(), from.getColumn()));
        from = from.translatedPosition(direction);
        while (!from.equals(to)) {
            if (!this.isEmpty(from)) {
                return false;
            }
            from = from.translatedPosition(direction);
        }
        return true;
    }

    private int cappedCompare(int x, int y) {
        return Math.max(-1, Math.min(1, Integer.compare(x, y)));
    }

    public void movePiece(Position from, Position to) {
        this.updateIsKingDead(to);
        if (!this.getCell(to).isEmpty()) {
            this.getCell(to).removePiece();
        }
        this.getCell(to).setPiece(this.getPiece(from));
        this.getCell(from).removePiece();
    }

    private void updateIsKingDead(Position positionBeingMovedTo) {
        if (this.getPiece(positionBeingMovedTo) instanceof King) {
            this._kingDead = true;
        }
    }

    private boolean isValidPawnMove(Position from, Position to) {
        assert (this.getPiece(from) instanceof Pawn);
        Pawn pawn = (Pawn)this.getPiece(from);
        Color pawnColor = pawn.getColor();
        int forwardRow = from.getRow() + (pawnColor == Color.BLACK ? 1 : -1);
        Position forwardLeft = new Position(forwardRow, from.getColumn() + (pawnColor == Color.WHITE ? -1 : 1));
        Position forwardRight = new Position(forwardRow, from.getColumn() + (pawnColor == Color.WHITE ? 1 : -1));
        boolean opponentPieceAtForwardLeft = !this.isEmpty(forwardLeft) && this.getPiece(forwardLeft).getColor() != pawnColor;
        boolean opponentPieceAtForwardRight = !this.isEmpty(forwardRight) && this.getPiece(forwardRight).getColor() != pawnColor;
        boolean atInitialPosition = from.getRow() == (pawnColor == Color.BLACK ? 1 : 6);
        return pawn.isValidMoveGivenContext(from, to, atInitialPosition, opponentPieceAtForwardLeft, opponentPieceAtForwardRight);
    }

    public boolean isKingDead() {
        return this._kingDead;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (int column = 0; column < 8; ++column) {
            stringBuilder.append("  ").append(column + 1).append("  ");
        }
        stringBuilder.append("\n");
        for (int row = 0; row < 8; ++row) {
            stringBuilder.append(row + 1);
            for (int column = 0; column < 8; ++column) {
                stringBuilder.append(" ").append(this._board[row][column]).append(" ");
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
