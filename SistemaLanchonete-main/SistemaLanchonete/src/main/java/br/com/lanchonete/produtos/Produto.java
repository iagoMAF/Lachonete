package br.com.lanchonete.produtos;

/**
 * Classe representativa dos produtos no sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class Produto {

    private String nomeProduto;
    private float valorProduto;
    private int idProduto;
    private String descricaoProduto;

    /**
     * Construtor com inicialização de atributos
     *
     * @param nomeProduto Define o nome do produto
     * @param valorProduto Define o valor (float) do produto
     * @param idProduto Define um identificador para o produto
     * @param descricaoProduto Define uma descrição em texto para o produto
     */
    public Produto(String nomeProduto, float valorProduto, int idProduto, String descricaoProduto) {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.idProduto = idProduto;
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * Construtor Padrão
     */
    public Produto() {
    }

    /**
     *
     * @return Nome associado ao produto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     *
     * @param nomeProduto Define o nome para o produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     *
     * @return Valor associado ao produto
     */
    public float getValorProduto() {
        return valorProduto;
    }

    /**
     *
     * @param valorProduto Define um valor para o produto
     */
    public void setValorProduto(float valorProduto) {
        this.valorProduto = valorProduto;
    }

    /**
     *
     * @return Identificador associado ao produto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     *
     * @param idProduto Define um valor para o identificador do produto
     */
    public void setIdProduto(int idProduto) {    
        this.idProduto = idProduto;
    }

    /**
     *
     * @return Descrição textual associada ao produto
     */
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    /**
     *
     * @param descricaoProduto Define uma descrição textual para o produto
     */
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     *
     * @return Representação em String do objeto produto
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "[" + idProduto + "]   " + nomeProduto.toUpperCase() + "    R$" + valorProduto + "      " + descricaoProduto;
    }

}
