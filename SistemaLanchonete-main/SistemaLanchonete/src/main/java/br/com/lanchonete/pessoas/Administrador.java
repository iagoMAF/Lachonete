package br.com.lanchonete.pessoas;

/**
 * Classe representativa da entidade Administrador no Sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public final class Administrador extends Usuario {

    private static int admCount;

    //Construtor
    /**
     * Construtor Parametrizado Obs.: Realiza uma chamada ao construtor
     * parametrizado da classe pai (Usuário)
     *
     * @param nomePessoa define um nome para o Administrador do programa
     * @param sobrenomePessoa define o sobrenome para o Administrador do
     * programa
     * @param CPF define o CPF, usado como identificador único, para o
     * Administrador do programa
     * @param loginUsuario define o login utilizado pelo Administrador do
     * programa
     * @param senhaUsuario define a senha utilizada pelo Administrador do
     * programa
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Administrador(String nomePessoa, String sobrenomePessoa, String CPF, String loginUsuario, String senhaUsuario) {
        super(nomePessoa, sobrenomePessoa, CPF, loginUsuario, senhaUsuario);
        setAdmCount(admCount);
    }

    /**
     * Construtor padrão Obs.: Realiza uma chamada ao construtor padrão da
     * classe pai (Usuário)
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Administrador() {
        super();
    }

    /**
     *
     * @return Quantidade de administradores cadastrados no sistema
     */
    public static int getAdmCount() {
        return admCount;
    }

    /**
     *
     * @param admCount Define a contagem de administradores no sistema
     */
    public static void setAdmCount(int admCount) {
        Administrador.admCount = admCount + 1;
    }

    /**
     *
     * @return Representação em String do Administrador conforme estabelecido
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getCPF() + "    " + getNomePessoa().toUpperCase() + " " + getSobrenomePessoa().toUpperCase()
                + "    " + getLoginUsuario();
    }
}
