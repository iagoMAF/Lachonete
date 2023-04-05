package br.com.lanchonete.produtos;

import java.util.*;

/**
 * Classe comparator para a classe Pedido
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
//Questão 12 - Implementar a interface Comparator para as classes Cliente e Pedido
public class PedidoComparator implements Comparator<Pedido> {

    /**
     * Implementação do método Comparator
     *
     * @param o1 Objeto do tipo Pedido
     * @param o2 Objeto do tipo Pedido
     * @return Resultado da comparação (0 : iguais | 1 : o1 é maior que o2 | -1
     * : o1 é menor que o2)
     */
    @Override
    public int compare(Pedido o1, Pedido o2) {
        if (o1.getIdPedido() == o2.getIdPedido()) {
            return 0;
        } else if (o1.getIdPedido() > o2.getIdPedido()) {
            return 1;
        } else {
            return -1;
        }

    }

    /**
     *
     * @return Representação em String da Classe Comparator para Pedidos
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "PedidoComparator";
    }

}
