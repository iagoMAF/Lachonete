package br.com.lanchonete.pessoas;

/**
 * Classe abstrata para representação da entidade Pessoa no Sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public abstract class Pessoa {

    private String nomePessoa;
    private String sobrenomePessoa;
    private String CPF;

    /**
     * Construtor parametrizado
     *
     * @param nomePessoa Define um nome para a pessoa
     * @param sobrenomePessoa Define um sobrenome para a pessoa
     * @param CPF Define um número de CPF para a pessoa
     */
    public Pessoa(String nomePessoa, String sobrenomePessoa, String CPF) {
        this.nomePessoa = nomePessoa;
        this.sobrenomePessoa = sobrenomePessoa;
        this.CPF = CPF;
    }

    /**
     * Construtor padrão
     */
    public Pessoa() {

    }

    /**
     *
     * @return Nome associado à pessoa
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     *
     * @param nomePessoa Define um nome para a pessoa
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    /**
     *
     * @return Sobrenome associado à pessoa
     */
    public String getSobrenomePessoa() {
        return sobrenomePessoa;
    }

    /**
     *
     * @param sobrenomePessoa Define um sobrenome para a pessoa
     */
    public void setSobrenomePessoa(String sobrenomePessoa) {
        this.sobrenomePessoa = sobrenomePessoa;
    }

    /**
     *
     * @return Número de CPF associado à pessoa
     */
    public String getCPF() {
        return CPF;
    }

    /**
     *
     * @param CPF Define um número de CPF para a pessoa
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     *
     * @return Representação String do objeto pessoa
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return CPF + "    " + nomePessoa.toUpperCase() + " " + sobrenomePessoa.toUpperCase();
    }
}
