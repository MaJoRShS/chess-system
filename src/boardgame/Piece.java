package boardgame;

public class Piece {

	protected Position position;
	private Board board;

	/*
	 * Aqui o position ta como nulo porque vão existir métodos especificos para
	 * acesso de posições.
	 * 
	 * Eu tinha cabaçado e tinha colocado o position no construtor o que me atrasou
	 * pelo menos uns 30 minutos
	 * 
	 */
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	/*
	 * E aqui só tem ele e ta com protected porque só ele e as subclasses de peças
	 * pode acessar esse cara.
	 */
	protected Board getBoard() {
		return board;
	}
}
