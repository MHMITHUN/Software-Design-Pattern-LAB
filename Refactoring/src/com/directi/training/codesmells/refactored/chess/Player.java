/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Color;

public class Player {
    private String _name;
    private int _gamesWon;
    private Color _color;

    public Player(String name) {
        this._name = name;
        this._gamesWon = 0;
    }

    public String getName() {
        return this._name;
    }

    public void incrementGamesWon() {
        ++this._gamesWon;
    }

    public Color getColor() {
        return this._color;
    }

    public void setColor(Color color) {
        this._color = color;
    }

    public String toString() {
        return "NAME: " + this._name + "; GAMES WON: " + this._gamesWon;
    }
}
