package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	/*
	 * Saparada aqui são códigos de cores para serem apresnetadas diferenciadas no
	 * terminal
	 */
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	/*
	 * Código do stackOverFlow para poder limpar a tela.
	 */

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/*
	 * Aqui eu to recebendo uma Posição de Xadrez(ChessPosition) no formato "a1" e
	 * ai eu quebro primeiro a letra e depois o numero, ali tem uma parada que é o
	 * "parseInt" onde eu transformo uma string em inteiro, e qualquer execeção que
	 * der eu vou printar aquela mensagem ali para o usuario.
	 */

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading  ChessPosition. Valid values are from a1 to h8");
		}
	}

	/*
	 * Aqui é o novo método responsavel por imprimir a partida na tela e ele vai
	 * paresentar as informações de qual player deve jogar na tela
	 */
	public static void printMatch(ChessMatch chessMatch,List<ChessPiece> captured) {
		printBoard(chessMatch.getPiences());
		System.err.println();
		printCapturedPieces(captured);
		System.out.println();
		System.err.println("Trun: " + chessMatch.getTurn());
		System.err.println("Waiting player: " + chessMatch.getCurrentPlayer());
	}

	/*
	 * Classe responsavel por imprimir as peças do jogo, esse cara aqui de cima
	 * printa todas as peças do tabuleiro, isso caso existam , se não ele só vai
	 * imprimir o tabuleiro
	 */
	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {

			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {
				/*
				 * Aqui tive que colocar como false porque eu tive que mudar o proprio método
				 * "printPiece()"
				 */
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	/*
	 * Sobrecarga do método printBoard, aqui ele vai printar os movimentos possiveis
	 * de cada peça selecionada, porém é só na sobregarca que acontece isso
	 */
	public static void printBoard(ChessPiece[][] pieces, boolean[][] posibleMoves) {

		for (int i = 0; i < pieces.length; i++) {

			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {
				/*
				 * Aqui eu to printando todas as posições que são possiveis serem alteradas
				 */
				printPiece(pieces[i][j], posibleMoves[i][j]);
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

	/*
	 * Mudei o "printPiece" porque agora eu printo as posições que são possiveis de
	 * cada peça
	 */
	public static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			/*
			 * Aqui tive que colocar o resete em cada peça para ele limpar depois de eu
			 * mover a peça
			 */
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	
	/*
	 * Método para mostrar as peças capturadas no jogo.
	 */
	private static void printCapturedPieces(List<ChessPiece> captured) {
		/*
		 * Aqui eu pego uma lista de peças capturadas durante o jogo mesmo as brancas como as pretas
		 */
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured Pieces ");
		System.out.print("White : ");
		System.out.print(ANSI_WHITE);
		
		/*
		 * Aqui é um macete para poder printar um array
		 */
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		
		System.out.print("Black : ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
	
	
}