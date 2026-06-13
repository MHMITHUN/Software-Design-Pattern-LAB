/*
 * Decompiled with CFR 0.152.
 */
package com.directi.training.codesmells.refactored.chess;

import com.directi.training.codesmells.refactored.Color;
import com.directi.training.codesmells.refactored.Position;
import com.directi.training.codesmells.refactored.chess.ChessBoard;
import com.directi.training.codesmells.refactored.chess.Player;
import java.util.Scanner;

public class GameEngine {
    private static final Scanner scanner = new Scanner(System.in);
    private final ChessBoard _chessBoard = new ChessBoard();
    private Player _player1;
    private Player _player2;
    private Player _currentPlayer;

    public GameEngine(Player player1, Player player2) {
        this._player1 = player1;
        this._player2 = player2;
    }

    public void initGame() {
        if (this._currentPlayer == null || this._player1.getColor() == Color.BLACK) {
            this._currentPlayer = this._player1;
            this._player1.setColor(Color.WHITE);
            this._player2.setColor(Color.BLACK);
        } else {
            this._currentPlayer = this._player2;
            this._player1.setColor(Color.BLACK);
            this._player2.setColor(Color.WHITE);
        }
        System.out.println("\nGame initialized");
        System.out.println("Player " + this._player1.getName() + " has Color " + (Object)((Object)this._player1.getColor()));
        System.out.println("Player " + this._player2.getName() + " has Color " + (Object)((Object)this._player2.getColor()));
        System.out.println("");
        this._chessBoard.resetBoard();
        System.out.println();
    }

    public void startGame() {
        while (true) {
            System.out.println("Next move is of " + this._currentPlayer.getName() + " [" + (Object)((Object)this._currentPlayer.getColor()) + "]");
            System.out.print("Enter position (row col) of piece to move: ");
            Position from = this.inputPosition();
            System.out.print("Enter destination position: ");
            Position to = this.inputPosition();
            if (this.isValidMove(from, to)) {
                this.makeMove(from, to);
                continue;
            }
            System.out.println("Invalid move!");
        }
    }

    private Position inputPosition() {
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return new Position(row, col);
    }

    private void endGame() {
        System.out.println("Game Ended");
        Player winner = this._currentPlayer;
        winner.incrementGamesWon();
        System.out.println("WINNER - " + winner + "\n\n");
    }

    private Player getOtherPlayer() {
        return this._player1 == this._currentPlayer ? this._player2 : this._player1;
    }

    public void makeMove(Position from, Position to) {
        this._chessBoard.movePiece(from, to);
        System.out.println("Piece moved for Player : " + this._currentPlayer);
        System.out.println("");
        System.out.println(this._chessBoard);
        if (this._chessBoard.isKingDead()) {
            this.endGame();
            this.initGame();
        } else {
            this._currentPlayer = this.getOtherPlayer();
        }
    }

    public boolean isValidMove(Position from, Position to) {
        return this.isPlayerMovingItsOwnColoredPiece(from) && this._chessBoard.isValidMove(from, to);
    }

    private boolean isPlayerMovingItsOwnColoredPiece(Position from) {
        return !this._chessBoard.isEmpty(from) && this._chessBoard.getPiece(from).getColor() == this._currentPlayer.getColor();
    }

    public ChessBoard getChessBoard() {
        return this._chessBoard;
    }
}
