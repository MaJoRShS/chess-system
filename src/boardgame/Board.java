package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	/*
	 * Adicionamos "programação defensiva" no código, na real aqui são validações
	 * para impedir que o programa seja iniciado com peças fora de lugar ou que
	 * fiquem peças fora do tabuleiro ou peça sobre peça
	 */
	public Board(int rows, int columns) {

		/*
		 * Aqui é só para que o programa seja iniciado com pelo menos uma linha e uma
		 * coluna.
		 */
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	/*
	 * Removemos o setRows, e o setColumns porque não devemos deixar ser alterado
	 * após o inicio do jogo
	 */

	public Piece piece(int row, int column) {
		/*
		 * Validação para ver ser peça foi instanciada dentro do tabuleiro.
		 */
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	/*
	 * Aqui eu estou passando uma posição de linha e coluna para uma peça no
	 * tabuleiro, e eu consigo acessar direto o "piece" para passar o position
	 * porque ele é um atributo "proteceted" que me permite fazer isso porque estou
	 * em uma classe filha
	 */
	public void PlacePiece(Piece piece, Position position) {
		/*
		 * Validação para ver se já existe uma peça naquela posição do tabuleiro.
		 */
		if (thereIsAPiece(position)) {
			throw new BoardException("There is  alredy a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	/*
	 * Esse novo métood serve para remover as peças do tabuleiro, isso aqui primeiro
	 * verifica se tem peça em algum lugar do tabuleiro em caso de nulo ele devolve
	 * uma mensagem de erro, caso exista peça ele marca o lugar dela como nulo, e só
	 * depois devolve ela como nulo para o programa
	 */

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public boolean positionExists(int row, int column) {
		/*
		 * Aqui eu to vendo se a posição está dentro do tabuleiro, vendo se não foi
		 * instanciado vazio e se não passa da quantidade que foi informada
		 */
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		/*
		 * Aqui eu to vendo se a posição existe dentro da validação de se a peça existe
		 * para que não tenha que colocar isso em todas as validações.
		 */
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}
