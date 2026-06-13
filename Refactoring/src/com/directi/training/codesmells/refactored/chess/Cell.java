/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.pieces.Piece;

public class Cell {
    private Piece _piece;
    private Color _color;

    public Cell(Color color) {
        this._color = color;
    }

    public void removePiece() {
        this._piece = null;
    }

    public Piece getPiece() {
        return this._piece;
    }

    public void setPiece(Piece piece) {
        this._piece = piece;
    }

    public boolean isEmpty() {
        return this._piece == null;
    }

    public String toString() {
        return this._piece == null ? "." + (Object)((Object)this._color) + "." : this._piece.getColor().toString() + (Object)((Object)this._color) + this._piece.toString();
    }
}
