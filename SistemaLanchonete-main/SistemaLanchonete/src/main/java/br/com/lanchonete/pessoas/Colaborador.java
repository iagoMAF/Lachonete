package br.com.lanchonete.pessoas;

/**
 * Classe representativa da entidade Colaborador no Sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class Colaborador extends Usuario {

    /**
     * Construtor parametrizado Obs.: Realiza uma chamada para o construtor
     * parametrizado da classe pai (Usuário)
     *
     * @param nomePessoa Define um nome para o Colaborador
     * @param sobrenomePessoa Define um sobrenome para o Colaborador
     * @param CPF Define um número de CPF para o Colaborador
     * @param loginUsuario Define um login (e-mail) de acesso para o Colaborador
     * @param senhaUsuario Define uma senha de acesso para o Colaborador
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Colaborador(String nomePessoa, String sobrenomePessoa, String CPF, String loginUsuario, String senhaUsuario) {
        super(nomePessoa, sobrenomePessoa, CPF, loginUsuario, senhaUsuario);
    }

    /**
     * Construtor padrão Obs.: Realiza uma chamada ao construtor padrão da
     * classe pai (Usuário)
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Colaborador() {
        super();
    }

    /**
     *
     * @return Representação String do objeto Colaborador
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getCPF() + "    " + getNomePessoa().toUpperCase() + " " + getSobrenomePessoa().toUpperCase()
                + "     " + getLoginUsuario();
    }

}
