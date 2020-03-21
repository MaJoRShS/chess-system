package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public  class Rook extends ChessPiece {

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
		return mat;
	}

}
