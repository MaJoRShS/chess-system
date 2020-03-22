package boardgame;

public class Position {

	private int row;
	private int column;

	public Position() {

	}

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/*
	 * Aqui eu to permitindo que sejam inseridos novos valores para linha e coluna,
	 * mais sempre por meio de um método.
	 */
	public void setValues(int row, int column) {
		this.column = column;
		this.row = row;
	}

	@Override
	public String toString() {
		return row + ", " + column;
	}

}
