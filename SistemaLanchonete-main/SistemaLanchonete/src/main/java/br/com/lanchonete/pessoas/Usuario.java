package br.com.lanchonete.pessoas;

/**
 * Classe representativa da entidade Usuário do Sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class Usuario extends Pessoa {

    private String loginUsuario;
    private String senhaUsuario;

    /**
     * Construtor Parametrizado Realiza uma chamada ao construtor da classe pai
     * (Pessoa)
     *
     * @param nomePessoa Define um nome para o usuário
     * @param sobrenomePessoa Define um sobrenome para o usuário
     * @param CPF Define um número de CPF para o usuário
     * @param loginUsuario Define um login (e-mail) de acesso para o usuário
     * @param senhaUsuario Define uma senha de acesso para o usuário
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Usuario(String nomePessoa, String sobrenomePessoa, String CPF, String loginUsuario, String senhaUsuario) {
        super(nomePessoa, sobrenomePessoa, CPF);
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    /**
     * Construtor Padrão Realiza uma chamada ao construtor padrão da classe pai
     * (Pessoa)
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Usuario() {
        super();
    }

    /**
     *
     * @return Login de acesso associado ao usuário
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }

    /**
     *
     * @param loginUsuario Define um login de acesso para o usuário
     */
    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    /**
     *
     * @return Senha de acesso associado ao usuário
     */
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    /**
     *
     * @param senhaUsuario Define uma senha de acesso para o usuário
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    /**
     *
     * @return Representação String do objeto Usuario
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getCPF() + "    " + getNomePessoa().toUpperCase() + " " + getSobrenomePessoa().toUpperCase()
                + "    " + loginUsuario;

    }

}
