package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;

	/*
	 * Aqui nesse construtor eu já começo instanciando com o tamanho correto do
	 * tabuleiro, porque quem tem que saber qual é o tamanho do tabuleiro é a classe
	 * que é responsavel pela partida.
	 */
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	/*
	 * Aqui eu to criando um tipo diferente de peça , poruqe eu não quero que o
	 * programa acesse diretamente as minhas peças em nivel de peça mais só direto
	 * nas peças da partida, que na teoria seriam as peças com tudo e não peças no
	 * sentido de apenas peça, aqui as peças são literalmente as peças de uma
	 * partida de xadrez ou seja , rei, rainha etc.
	 * 
	 * Criei uma variavel auxiliar que é um vetor, e já instancio ela recebendo de
	 * parâmetro o row e column, ai precisa passar por dois for, um para pegar a
	 * linha outro para pegar a coluna, ai depois disso eu faço o downCasting do
	 * board para um ChessPiece.
	 */
	public ChessPiece[][] getPiences() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	// Operação que vai movimentar as peças
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// Aqui preciso transformar essa posições de xadrez em posições de matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		// Validando para ver se a posição de origem existia
		validateSourcePosition(source);

		// Validando posição de destino para ver se ele está fazendo correto
		validateTargetPosition(source, target);

		// moendo a peça da origem(source) para o destino(target
		Piece capturedPiece = makeMove(source, target);
		// Aqui tem que fazer o downCast pórque o "capturePiece" é do tipo Piece
		return (ChessPiece) capturedPiece;

	}

	private Piece makeMove(Position source, Position target) {
		// Removendo a peça do local de origem
		Piece p = board.removePiece(source);
		// Removendo a posivel peça do lugar de destino
		Piece capturedPiece = board.removePiece(target);
		// Movendo a peça de Origem para o local destino
		board.PlacePiece(p, target);
		return capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is not a piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	/*
	 * Aqui eu to testando a posição de destino, porque eu preciso saber se eu posso
	 * mover a peça para la
	 */
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}

	/*
	 * Aqui eu obrigando a ser informado as peças mais nas cordenadas do xadrez e
	 * não na da matriz, e como eu já criei o método para converter de posição de
	 * xadrez para matriz eu uso esse método.
	 */
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
	}

	/*
	 * Ai aqui eu pego direto a posição do xadrez para colocar a peça, porque dentro
	 * da "ChessPosition" eu to convertendo essa posição para a de matriz o que vai
	 * colocar a peça no lugar correto, e não crio nada no board porque ele ta no
	 * método que faz a conversão da parada
	 * 
	 * 
	 * Mandou mais umas peças
	 */
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
