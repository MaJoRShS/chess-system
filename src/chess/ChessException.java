package chess;

import boardgame.BoardException;

/*
 * Aqui eu tive que mudar para BoardException porque eu to querendo facilitar a 
 * vida da minha aplicação e ela conseguir tratar tanto erros de xadrez quanto 
 * de tabuleiro, eu vou capturar as chessException e automaticamente pego as boardException
 */
public class ChessException extends BoardException {
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	}
}
