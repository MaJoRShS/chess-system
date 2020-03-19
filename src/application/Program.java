package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		/*
		 * Trocamos aqui para iniciar uma partida de xadrez e ainda para apresentar as
		 * peças e o tabuleiro, isso caso exista os dois.
		 */
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPiences());

	}

}
