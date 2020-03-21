package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {

	private Color color;

	/*
	 * Aqui não tem o position no construtor , eu tinha cabaçado na criação da
	 * classe piece e por isso deu erro em algumas coisa quiando fui criar
	 */
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
