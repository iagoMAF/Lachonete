package br.com.lanchonete.pessoas;

import java.util.ArrayList;
import br.com.lanchonete.produtos.*;

/**
 * Classe representativa da entidade Cliente no Sistema
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */

//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class Cliente extends Pessoa {

    private String enderecoCliente;
    private String telefoneCliente;
    private ArrayList<Pedido> pedidosCliente = new ArrayList<>();

    /**
     * Construtor parametrizado para o objeto Cliente Obs.: Realiza uma chamada
     * ao construtor parametrizado da classe pai (Pessoa)
     *
     * @param nomePessoa Define um nome para o Cliente
     * @param sobrenomePessoa Define um sobrenome para o Cliente
     * @param CPF Define um número de CPF para o Cliente
     * @param enderecoCliente Define um endereço para o Cliente
     * @param telefoneCliente Define um número de telefone para o Cliente
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Cliente(String nomePessoa, String sobrenomePessoa, String CPF, String enderecoCliente, String telefoneCliente) {
        super(nomePessoa, sobrenomePessoa, CPF);
        this.enderecoCliente = enderecoCliente;
        this.telefoneCliente = telefoneCliente;
    }

    /**
     * Construtor padrão Obs.: Realiza uma chamada ao construtor padrão da
     * classe pai (Pessoa)
     */
    //Questão 4 - Utilizar a palavra chave super para implementar os construtores das subsclasses
    public Cliente() {
        super();
    }

    /**
     *
     * @return Lista de pedidos associados ao cliente
     */
    public ArrayList<Pedido> getPedidosCliente() {
        return pedidosCliente;
    }

    /**
     *
     * @param novoPedido Adicina um novo pedido à lista de pedidos feitas
     */
    public void setPedidosCliente(Pedido novoPedido) {
        this.pedidosCliente.add(0, novoPedido);
    }

    /**
     *
     * @return Endereço associado ao Cliente
     */
    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    /**
     *
     * @param enderecoCliente Define um endereço para o Cliente
     */
    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    /**
     *
     * @return Número de telefone associado ao Cliente
     */
    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    /**
     *
     * @param telefoneCliente Define um número de telefone para o Cliente
     */
    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    /**
     *
     * @return Representação String para o objeto Cliente
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return getCPF() + "    " + getNomePessoa().toUpperCase() + " " + getSobrenomePessoa().toUpperCase() + "    "
                + getTelefoneCliente() + "    " + getEnderecoCliente().toUpperCase();
    }

}
