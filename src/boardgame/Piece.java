package boardgame;

public abstract class Piece {

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

	/*
	 * Métod abstrato que verifica se há movimentos possiveis, e como ele é abstrato
	 * tive que mudar a classe para abstract
	 */
	public abstract boolean[][] possibleMoves();

	/*
	 * Aqui é um método concreto que está utilizando um método abstrato, isso aqui é
	 * chamado de "HOOK MÉTHOD" ou método de gancho, basicamente eu to verificando
	 * em uma matriz quais são os movimentos possiveis para cada peça
	 */
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	/*
	 * Aqui eu to rebendo de novo o método abstrato e to utilizando os valores da
	 * matriz de verdadeiro e falso para poder saber quais casas estão livres para
	 * minha peça caminhar, claro que quantas casas e qual o sentido das dos
	 * movimentos vai ficar em cada peça
	 */
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
