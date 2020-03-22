package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;

	public ChessPosition(char column, int row) {

		/*
		 * Aqui eu to validando para ver se tudo está dentro do tabuleiro, e como o "if"
		 * valida até mesmo caraceteres eu to querendo mudar o tipó de validação para ao
		 * invés de um "[0][0]" de uma matriz eu vou ter que converter ele para o nome
		 * das posições que eu criei, que no caso vai de a1 - h8
		 */
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition.  Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/*
	 * Não tem set porque não é para permitir a alteração das linhas e das colunas.
	 */

	/*
	 * Aqui a conta para achar a linha mais nas posições da matriz vai ser (8 - o
	 * numero da linha que tu quer) e para achar a coluna é (coluna - 'a')
	 * 
	 * essa parada do 'a' é porque segundo a lenda da para você somar letras, por
	 * causa do unicode de cada letra, o que basicamente retorna um valor, que no
	 * caso de (a - a da 0) e de (b - a da 1) e assim por diante, o que acaba dando
	 * a matriz numeral que criamos.
	 */
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	/*
	 * Aqui eu tenho o processo reverso, eu to pegando a posição da matriz e
	 * convertendo para a posição do tabuleiro de xadrez o que faz com que a cada
	 * instancia de nova peça eu coloque a peça no lugar correto usando a referencia
	 * que eu criei para o tabuleiro
	 * 
	 * 
	 * Aqui também tinha um erro que tava com sinal de menos(-) no return do ChessPosition.
	 */
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
	}
	//	CONFUSO ↑

	@Override
	public String toString() {
		/*
		 * Aqui tem um GATO que força o compilador a não dar erro para imprimir uma linha , que é passando uma string vazia antes das variaveis 
		 */
		return "" + column + row;
	}

}
