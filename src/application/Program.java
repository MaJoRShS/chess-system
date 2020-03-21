package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
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
			// Adicionamos o tratamento das execeções que podem ocorrer aqui no programa
			try {
				// Método que limpa a tela e impede que fique apenas rolando para baixo
				UI.clearScreen();
				UI.printBoard(chessMatch.getPiences());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			}
		}
	}

}
