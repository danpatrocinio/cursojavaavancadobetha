package api.biblioteca.satc.exceptions;

public class ModelException extends Exception {

    /**
     * Cria a exception com a mensagem padrão da classe <code>Exeption</code>.
     */
    public ModelException() {
        super();
    }

    /**
     * Cria a exception com a mensagem passada como parâmetro.
     *
     * @param mensagem
     *            Mensagem específica da exception.
     */
    public ModelException(String mensagem) {
        super(mensagem);
    }

    /**
     * Cria a exception com a mensagem e causa passadas por parâmetro.
     *
     * @param mensagem
     *            Mensagem específica da exception.
     * @param causa
     *            Indica a causa que gerou essa exception.
     */
    public ModelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
