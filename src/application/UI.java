package application;

import chess.ChessPiece;

public class UI {

	/*
	 * Classe responsavel por imprimir as peças do jogo, esse cara aqui de cima
	 * printa todas as peças do tabuleiro, isso caso existam , se não ele só vai
	 * imprimir o tabuleiro
	 */
	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {

			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	/*
	 * Método auxiliar que faz com que cada peça seja impreça , se não existir peça
	 * ele vai colocar um traço(-) no lugar e imprimir um espaço em branco para não
	 * deixar as peças colados umas nas outras
	 */

	public static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
