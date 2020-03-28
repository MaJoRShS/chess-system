package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;

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
	
	public int getMoveCount() {
		return moveCount;
	}

	public void increseMoveCount() {
		moveCount++;
	}

	public void decreseMoveCount() {
		moveCount--;
	}

	/*
	 * Aqui eu to retornando as posições do xadrez porque eu não tenho acesso ao
	 * position da piece e não posso retornar as posiçõe em forma de matriz
	 */
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	/*
	 * Aqui eu to verificando se no tabuleiro a casa para onde eu quero andar esta
	 * nula ou se tem peça de inimigo, se tiver nula e consigo ir e se tiver peça de
	 * inimigo eu também consigo, mais se for peça do meu time eu não consigo, isso
	 * claro ainda não levamos em consideração as movimentações de cada peça
	 */
	public boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

}
