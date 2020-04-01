package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		// Setei a lista de peças capturadas no jogo
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			// Adicionamos o tratamento das execeções que podem ocorrer aqui no programa
			try {
				// Método que limpa a tela e impede que fique apenas rolando para baixo
				UI.clearScreen();
				
				//Tive que mudar aqui para ele apresentar as listas de peças capturadas
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPiences(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

				/*
				 * Aqui tem a validação que é feita para saber se tem alguma peça capturada, se
				 * houver eu jogo ela na lista de peças capturadas se não fica como está
				 */
				if (capturedPiece != null) {
					captured.add(capturedPiece);

				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine();
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
				}
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				/*
				 * Descobri o que está acontecendo e dando erro sempre e mostrando duas
				 * mensagens de erro, aqui estava com "sc.hasNextLine();" e não com o
				 * "sc.nextLine();" que é o correto.
				 */
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
