package br.com.lanchonete.pessoas;

import java.util.*;

/**
 * Classe Comparator para a classe Cliente
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
//Questão 12 - Implementar a interface Comparator para as classes Cliente e Pedido
public class ClienteComparator implements Comparator<Cliente> {

    /**
     * Implementação do método comparator para Clientes
     *
     * @param o1 Objeto do tipo cliente
     * @param o2 Objeto do tipo cliente
     * @return Resultado da comparação (0: são iguais | + : o1 é maior que o2 |
     * - : o1 é meior que o2)
     */
    @Override
    public int compare(Cliente o1, Cliente o2) {
        if (o1.getCPF().equals(o2.getCPF())) {
            return 0;
        }
        return o1.getCPF().compareTo(o2.getCPF());
    }

    /**
     *
     * @return Representação em String da Classe Comparator para Clientes
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "ClienteComparator";
    }

}
