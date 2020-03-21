package boardgame;

public class BoardException extends RuntimeException {
	/*
	 * Essa parada é obrigatória porque eu to extendendo a RunTimeException
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Aqui é igual quando criamos as execeções personalizadas, eu to passando a
	 * mensagem para a super classe para poder apresentar ela.
	 */
	public BoardException(String msg) {
		super(msg);
	}
}
