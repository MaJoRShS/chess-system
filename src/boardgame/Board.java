package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Piece piece(int row, int column) {
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	/*
	 * Aqui eu estou passando uma posição de linha e coluna para uma peça no
	 * tabuleiro, e eu consigo acessar direto o "piece" para passar o position
	 * porque ele é um atributo "proteceted" que me permite fazer isso porque estou
	 * em uma classe filha
	 */
	public void PlacePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;

	}

}
