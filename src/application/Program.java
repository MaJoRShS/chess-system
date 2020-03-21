package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * Trocamos aqui para iniciar uma partida de xadrez e ainda para apresentar as
		 * peças e o tabuleiro, isso caso exista os dois.
		 */
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.printBoard(chessMatch.getPiences()); 
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); 
		}
	}

}
