package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	/*
	 * Adicionamos mais dois atributos o turno e o jogador da vez
	 */
	private Board board;
	private int turn;
	private Color currentPlayer;
	/*
	 * Adicionou um atributo que vai receber o true ou false em caso da partida
	 * estar em check
	 */
	private boolean check;

	/*
	 * Duas novas listar para armazenar as peças que foram capturadas e as peças que
	 * ainda estão no game
	 */
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	/*
	 * Aqui nesse construtor eu já começo instanciando com o tamanho correto do
	 * tabuleiro, porque quem tem que saber qual é o tamanho do tabuleiro é a classe
	 * que é responsavel pela partida.
	 */
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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

	/*
	 * Aqui eu to passando a posição direto para a classe principal para poder
	 * pintar a tela para os movimentos possiveis
	 */
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
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
		// Aqui eu mudando o turno e o jogador
		nextTurn();
		// Aqui tem que fazer o downCast pórque o "capturePiece" é do tipo Piece
		return (ChessPiece) capturedPiece;

	}

	private Piece makeMove(Position source, Position target) {
		// Removendo a peça do local de origem
		Piece p = board.removePiece(source);
		// Removendo a posivel peça do lugar de destino
		Piece capturedPiece = board.removePiece(target);
		// Movendo a peça de Origem para o local destino
		board.placePiece(p, target);

		/*
		 * Aqui eu valido as peças que foram capturadas e adiciono na lista de capturas
		 * as peças que foram capturadas e removo do tabuleiro
		 */
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	/*
	 * Desfazendo a joga em caso de o check na partida, o jogador não pode se
	 * colocar em chek
	 */
	public void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is not a piece on source position");
		}
		/*
		 * Aqui to validando se a peça escolhida pertence aquele player
		 * 
		 * e na validação como o getColor e da classe "ChessPiece" e não da piece eu
		 * tive que fazer o casting e mudar o board.piece naquela posição para
		 * chessPìece, ai o getColor funciona.
		 */
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
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

	public void nextTurn() {
		/*
		 * Aqui vai um imcremento para saber em qual rodada está e depois uma condição
		 * ternaria para mudar o jogar pela cor
		 */
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	/*
	 * Esse cara vai retornar o oponento do jogador da rodada
	 */
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	/*
	 * Aqui ele ta procurando um rei da cor que foi informada em todo o tabuleiro,ai
	 * usa la o lambda para retornar esse rei e quando encontra devolve, caso não
	 * ache nenhum rei no tabuleiro da erro , mais esse erro é para nunca acontecer
	 */
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + "king on the board");
	}

	/*
	 * Aqui eu obrigando a ser informado as peças mais nas cordenadas do xadrez e
	 * não na da matriz, e como eu já criei o método para converter de posição de
	 * xadrez para matriz eu uso esse método.
	 */
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());

		/*
		 * Adicionamos aqui as peças do tabuleiro na lista de peças no tabuleiro
		 */
		piecesOnTheBoard.add(piece);
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
