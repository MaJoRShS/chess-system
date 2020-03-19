package chess;

import boardgame.Board;

public class ChessMatch {
	private Board board;

	/*
	 * Aqui nesse construtor eu já começo instanciando com o tamanho correto do
	 * tabuleiro, porque quem tem que saber qual é o tamanho do tabuleiro é a classe
	 * que é responsavel pela partida.
	 */
	public ChessMatch() {
		board = new Board(8, 8);
	}

	/*
	 * Aqui eu to criando um tipo diferente de peça , poruqe eu não quero que o
	 * programa acesse diretamente as minhas peças em nivel de peça mais só direto
	 * nas peças da partida, que na teoria seriam as peças com tudo e não peças no
	 * sentido de apenas peça, aqui as peças são literalmente as peças de uma
	 * partida de xadrez ou seja , rei, rainha etc.
	 * 
	 * Criei uma variavel auxiliar que é um vetor, e já instancio ela recebendo de
	 * parâmetro o row e column, ai precisa passar por dois for, um para pegar a
	 * linha outro para pegar a coluna, ai depois disso eu faço o downCasting do board para um ChessPiece.
	 */
	public ChessPiece[][] getPiences() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

}
