package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	/*
	 * Aqui eu tive que fazer isso para poder parar de dar erro no compilador, mais
	 * mesmo assim aqui eu to verificando se existem movimentos possiveis para essa
	 * peça, e como ainda é cedo para adicionar as regras de cada peça ela está
	 * recebendo uma matriz com todos os valores nulos.
	 */
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Setou uma posição como sendo 0,0 para marcar o inicio da matriz.
		Position p = new Position(0, 0);

		// above

		/*
		 * Aqui ele ta pegando os valores da peça e verificando se uma linha para cima
		 * está livre , e se estiver livre temos que marcar como true, e ai depois se
		 * for isso mesmo continua marcando casa para cima e contua até achar uma peça
		 */
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}

		/*
		 * Aqui ele vai chegar quando achar uma peça no tabuleiro e depois ele vai ver
		 * se a peça que ta la é do time inimigo ele marca também como true.
		 */
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left

		/*
		 * Aqui é para a esquerda e como eu to caminhando na coluna eu mando um menos -1
		 * na coluna e não mais na linha
		 */
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right

		/*
		 * Aqui na real como eu to indo para a direito é coluna mais(+) 1
		 */
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below

		/*
		 * Aqui na real como eu to indo para a direito é linha mais(+) 1
		 */
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}

		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
