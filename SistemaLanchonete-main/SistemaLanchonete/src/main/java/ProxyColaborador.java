
import br.com.lanchonete.pessoas.*;
import br.com.lanchonete.produtos.Pedido;
import br.com.lanchonete.produtos.Produto;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * Classe intermediária para funcionalidades geralmente disponíveis ao
 * Colaborador
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class ProxyColaborador {

    //FORMATADOR COM RESTRIÇÃO QUE NOS GARANTE A VERIFICAÇÃO DE DATAS VÁLIDAS
    DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    DateTimeFormatter localHourFormatter = DateTimeFormatter.ofPattern("HH:mm");

    //PEDIDOS
    private static ArrayList<String> extratosPedidos = new ArrayList();

    /**
     * Função padrão para cadastro de novos pedidos a um cliente já cadastrado
     * no sistema
     */
    public void cadastroPedido() {
        System.out.printf("CPF do cliente: ");
        Scanner input = new Scanner(System.in);
        String CPFCliente = input.nextLine();
        int numPedidos = 0;
        Cliente Cl = ProxyAdministrador.consultaCliente(CPFCliente);

        if (Cl != null) {

            Pedido novoPedido = new Pedido();

            for (Cliente cliente : ProxyAdministrador.getClientes()) {
                for (Pedido pe : cliente.getPedidosCliente()) {
                    numPedidos = numPedidos + 1;
                }

            }
            Pedido.setNumPedido(numPedidos);
            novoPedido.setIdPedido();


            boolean produtoCadastrado = false;

            do {
                int switchCadastro;
                System.out.println("""
                                   Escolha uma opção:
                                   1 - Inserir item
                                   2 - Listar produtos cadastrados
                                   """);
                switchCadastro = input.nextInt();

                switch (switchCadastro) {
                    case 1 -> {
                        System.out.printf("ID do Produto: ");
                        Integer idProduto = input.nextInt();

                        for (Produto Pr : ProxyAdministrador.getListaProdutos()) {
                            if (Pr != null) {
                                if (Pr.getIdProduto() == idProduto) {
                                    novoPedido.setListaProdutos(idProduto);
                                    produtoCadastrado = true;
                                    break;
                                }
                            }
                        }
                        if (produtoCadastrado == false) {
                            System.out.println("Produto não encontrado. Tente novamente!");
                        }
                        break;
                    }
                    case 2 -> {
                        for (Produto Pr : ProxyAdministrador.getListaProdutos()) {
                            if (Pr != null) {
                                System.out.println("[" + Pr.getIdProduto() + "]" + "     " + Pr.getNomeProduto());
                            }
                        }
                        System.out.println("\n");
                        produtoCadastrado = false;
                        break;
                    }
                    default -> {
                        System.out.println("Opção inválida. Tente novamente.");
                        produtoCadastrado = false;
                    }
                }

            } while (produtoCadastrado == false);

            boolean finalizarPedido = false;
            do {
                System.out.println("""
                               Escolha uma opção: 
                               1 - Inserir adicionais 
                               2 - Finalizar pedido
                               """);

                int i = input.nextInt();
                switch (i) {
                    case 1 -> {

                        boolean prCadastrado = false;

                        do {
                            int switchCadastro;
                            System.out.println("""
                                   Escolha uma opção:
                                   1 - Inserir item
                                   2 - Listar produtos cadastrados
                                   """);
                            switchCadastro = input.nextInt();

                            switch (switchCadastro) {
                                case 1 -> {
                                    
                                    System.out.printf("ID do Produto: ");
                                    Integer idAdicional = input.nextInt();

                                    for (Produto Pr : ProxyAdministrador.getListaProdutos()) {
                                        if (Pr != null) {
                                            if (Pr.getIdProduto() == idAdicional) {
                                                novoPedido.setListaProdutos(idAdicional);
                                                prCadastrado = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (prCadastrado == false) {
                                        System.out.println("Produto não encontrado. Tente novamente!");
                                    }
                                    break;
                                }
                                case 2 -> {
                                    for (Produto Pr : ProxyAdministrador.getListaProdutos()) {
                                        if (Pr != null) {
                                            System.out.println("[" + Pr.getIdProduto() + "]" + "     " + Pr.getNomeProduto());
                                        }
                                    }
                                    System.out.println("\n");
                                    prCadastrado = false;
                                    break;
                                }
                                default -> {
                                    System.out.println("Opção inválida. Tente novamente.");
                                    prCadastrado = false;
                                }
                            }

                        } while (prCadastrado == false);

                        break;
                    }

                    case 2 -> {
                        finalizarPedido = true;
                        break;
                    }

                    default -> {
                        System.out.println("Opção inválida. Tente novamente!");
                    }
                }

            } while (finalizarPedido == false);

            novoPedido.setDataPedido(LocalDate.now().toString());
            novoPedido.setHoraPedido(LocalTime.now().toString());
            novoPedido.setHoraEntregaPedido(LocalTime.now().plusMinutes(50).toString());

            float valorTotal = 0;
            for (Integer i : novoPedido.getListaProdutos()) {
                for (Produto po : ProxyAdministrador.getListaProdutos()) {
                    if (po.getIdProduto() == i) {
                        valorTotal += po.getValorProduto();
                    }
                }
            }
            novoPedido.setValorTotalPedido(valorTotal);
            novoPedido.setStatusPedido(1);
            Cl.setPedidosCliente(novoPedido);
            
            System.out.println("EXTRATO:\n__________________________\n" +
                    Cl.getCPF() + "     " + Cl.getNomePessoa().toUpperCase() + " " + Cl.getSobrenomePessoa().toUpperCase() + "\n" + novoPedido);
            
        }
        else{
            System.out.println("CPF inválido ou cliente não cadastrado. Tente novamente!");
        }

    }

    /**
     *
     * @return Lista de Extratos de todos os pedidos cadastrados no sistema
     */
    //Questão 9 - Cada pedido efetuado vai gerar um extrato que deve ser salvo junto com a informação do cliente que fez o pedido
    public static ArrayList<String> getExtratosPedidos() {
        return extratosPedidos;
    }

    /**
     * Cadastro da lista de Extrados de todos os pedidos
     *
     * @param extratosPedidos Lista de Extratos de Pedidos
     */
    //Questão 9 - Cada pedido efetuado vai gerar um extrato que deve ser salvo junto com a informação do cliente que fez o pedido
    public static void setExtratosPedidos(ArrayList<String> extratosPedidos) {
        ProxyColaborador.extratosPedidos = extratosPedidos;
    }

    /**
     * Função de construção dos extratos de todos os pedidos cadastrados no
     * sistema
     *
     * @return Lista de extratos de pedidos cadastrados no sistema
     */
    //Questão 9 - Cada pedido efetuado vai gerar um extrato que deve ser salvo junto com a informação do cliente que fez o pedido
    public ArrayList<String> extratosPedidos() {
        ArrayList<String> extratos = new ArrayList();
        for (Cliente cl : ProxyAdministrador.getClientes()) {
            for (Pedido pedido : cl.getPedidosCliente()) {
                LocalTime horaPedido = LocalTime.parse(pedido.getHoraPedido());
                LocalDate dataPedido = LocalDate.parse(pedido.getDataPedido());
                extratos.add(cl.getCPF() + "    " + cl.getNomePessoa().toUpperCase() + " " + cl.getSobrenomePessoa().toUpperCase()
                        + "    [" + pedido.getIdPedido() + "]    R$" + pedido.getValorTotalPedido()
                        + "    " + dataPedido.format(localDateFormatter) + "    " + horaPedido.format(localHourFormatter) + "    ITENS: " + pedido.getListaProdutos());
            }
        }
        ProxyColaborador.setExtratosPedidos(extratos);
        return ProxyColaborador.getExtratosPedidos();
    }

    /**
     * Função padrão para o acesso às opções de exibição de pedidos realizados
     * no sistema Fornece as opções de listagem por um intervalo de datas ou
     * intervalo de data e hora
     */
    //Questão 7 - Verificar e imprimir dados dos pedidos dos clientes tal como o status
    //Questão 14 - Pesquisar pedidos de clientes em um determinado intervalo de datas e horários
    public void listarPedidos() {
        boolean encerrarLista = false;
        do {
            System.out.println("""
                               Escolha uma opção: 
                               1 - Pesquisa por intervalo de data
                               2 - Pesquisa por intervalo de data e hora
                               3 - Voltar
                                """);
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();

            switch (i) {
                case 1 -> {
                    String diaMin = "";
                    String diaMax = "";
                    String mesMin = "";
                    String mesMax = "";
                    String anoMin = "";
                    String anoMax = "";
                    boolean validaMax = false;
                    boolean validaMin = false;

                    while (validaMax == false || validaMin == false) {
                        System.out.println("Insira o intervalo de tempo:\nDE_______________");

                        input = new Scanner(System.in);
                        System.out.printf("Dia (Ex.: 01): ");
                        diaMin = input.nextLine();
                        if (diaMin.length() == 1) {
                            diaMin = "0" + diaMin;
                        }

                        System.out.printf("Mes (Ex.: 01): ");
                        mesMin = input.nextLine();
                        if (mesMin.length() == 1) {
                            mesMin = "0" + mesMin;
                        }

                        System.out.printf("Ano (Ex.: 2002): ");
                        anoMin = input.nextLine();

                        System.out.println("ATÉ\n________________");

                        System.out.printf("Dia (Ex.: 01):");
                        diaMax = input.nextLine();
                        if (diaMax.length() == 1) {
                            diaMax = "0" + diaMax;
                        }

                        System.out.printf("Mês (Ex.: 01): ");
                        mesMax = input.nextLine();
                        if (mesMax.length() == 1) {
                            mesMax = "0" + mesMax;
                        }

                        System.out.printf("Ano (Ex.: 2002): ");
                        anoMax = input.nextLine();

                        String dataMin = anoMin + mesMin + diaMin;
                        String dataMax = anoMax + mesMax + diaMax;

                        validaMin = isDateValid(dataMin);
                        validaMax = isDateValid(dataMax);
                    }

                    LocalDate dataMin = LocalDate.parse(anoMin + "-" + mesMin + "-" + diaMin);
                    LocalDate dataMax = LocalDate.parse(anoMax + "-" + mesMax + "-" + diaMax);
                    System.out.println("""
                                   PEDIDOS CADASTRADOS NO SISTEMA
                                   ____________________________________________
                                   """);

                    for (int k = 0; k < ProxyAdministrador.getClientes().size(); k++) {
                        for (int j = 0; j < ProxyAdministrador.getClientes().get(k).getPedidosCliente().size(); j++) {
                            LocalDate dataPedido = LocalDate.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getDataPedido());
                            LocalDate dataPrint = LocalDate.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getDataPedido());
                            LocalTime horaPrint = LocalTime.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getHoraPedido());
                            LocalTime horaEntregaPrint = LocalTime.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getHoraEntregaPedido());
                            if (((dataPedido.isEqual(dataMin)) || (dataPedido.isAfter(dataMin))) && ((dataPedido.isEqual(dataMax)) || (dataPedido.isBefore(dataMax)))) {
                                System.out.println("CLIENTE: " + ProxyAdministrador.getClientes().get(k).getNomePessoa().toUpperCase() + " "
                                        + ProxyAdministrador.getClientes().get(k).getSobrenomePessoa().toUpperCase()
                                        + "\nENDEREÇO: " + ProxyAdministrador.getClientes().get(k).getEnderecoCliente().toUpperCase()
                                        + "\nTELEFONE: " + ProxyAdministrador.getClientes().get(k).getTelefoneCliente()
                                        + "\nID DO PEDIDO: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getIdPedido()
                                        + "\nDATA DO PEDIDO: " + dataPrint.format(localDateFormatter)
                                        + "\nHORA DO PEDIDO: " + horaPrint.format(localHourFormatter)
                                        + "\nHORA ESTIMADA DE ENTREGA: " + horaEntregaPrint.format(localHourFormatter)
                                        + "\nVALOR: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getValorTotalPedido()
                                        + "\nSTATUS: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getStatusPedido()
                                        + "\n___________________________________");
                            }
                        }
                    }
                    break;
                }

                case 2 -> {
                    String diaMin = ""; //dia do limite inferior
                    String diaMax = ""; //dia do limite superior
                    String mesMin = ""; //mes do limite inferior
                    String mesMax = ""; //mes do limite superior
                    String anoMin = ""; //ano do limite inferior
                    String anoMax = ""; //ano do limite superior
                    String horaMin = ""; //hora do limite inferior
                    String horaMax = ""; //hora do limite superior
                    String minMin = ""; //minutos do limite inferior
                    String minMax = ""; //minutos do limite superior
                    boolean validaDataMin = false;
                    boolean validaDataMax = false;
                    boolean validaHoraMin = false;
                    boolean validaHoraMax = false;

                    while (validaDataMin == false || validaDataMax == false || validaHoraMin == false || validaHoraMax == false) {
                        System.out.println("Insira o intervalo de tempo:\n DE");

                        input = new Scanner(System.in);
                        System.out.printf("Dia (Ex.: 01): ");
                        diaMin = input.nextLine();
                        if (diaMin.length() == 1) {
                            diaMin = "0" + diaMin;
                        }

                        System.out.printf("Mes (Ex.: 01): ");
                        mesMin = input.nextLine();
                        if (mesMin.length() == 1) {
                            mesMin = "0" + mesMin;
                        }

                        System.out.printf("Ano (Ex. 2002): ");
                        anoMin = input.nextLine();

                        System.out.printf("Hora (Ex.: 13): ");
                        horaMin = input.nextLine();
                        if (horaMin.length() == 1) {
                            horaMin = "0" + horaMin;
                        }

                        System.out.printf("Minuto (Ex.: 53): ");
                        minMin = input.nextLine();
                        if (minMin.length() == 1) {
                            minMin = "0" + minMin;
                        }

                        System.out.println("ATÉ\n");

                        System.out.printf("Dia (Ex.: 01):");
                        diaMax = input.nextLine();
                        if (diaMax.length() == 1) {
                            diaMax = "0" + diaMax;
                        }

                        System.out.printf("Mês (Ex.: 01): ");
                        mesMax = input.nextLine();
                        if (mesMax.length() == 1) {
                            mesMax = "0" + mesMax;
                        }

                        System.out.printf("Ano (Ex.: 2002: ");
                        anoMax = input.nextLine();

                        System.out.printf("Hora (Ex.: 13): ");
                        horaMax = input.nextLine();
                        if (horaMax.length() == 1) {
                            horaMax = "0" + horaMax;
                        }

                        System.out.printf("Minuto (Ex.: 51) ");
                        minMax = input.nextLine();
                        if (minMax.length() == 1) {
                            minMax = "0" + minMax;
                        }

                        String dataMin = anoMin + mesMin + diaMin;
                        String dataMax = anoMax + mesMax + diaMax;
                        String limHoraMin = horaMin + ":" + minMin;
                        String limHoraMax = horaMax + ":" + minMax;

                        validaDataMin = isDateValid(dataMin);
                        validaDataMax = isDateValid(dataMax);
                        validaHoraMin = isHourValid(limHoraMin);
                        validaHoraMax = isHourValid(limHoraMax);

                    }

                    LocalDate dataMin = LocalDate.parse(anoMin + "-" + mesMin + "-" + diaMin);
                    LocalDate dataMax = LocalDate.parse(anoMax + "-" + mesMax + "-" + diaMax);
                    LocalTime horaLimMin = LocalTime.parse(horaMin + ":" + minMin + ":" + "00");
                    LocalTime horaLimMax = LocalTime.parse(horaMax + ":" + minMax + ":" + "00");
                    System.out.println("""
                                   PEDIDOS CADASTRADOS NO SISTEMA
                                   ____________________________________________
                                   """);

                    for (int k = 0; k < ProxyAdministrador.getClientes().size(); k++) {
                        for (int j = 0; j < ProxyAdministrador.getClientes().get(k).getPedidosCliente().size(); j++) {
                            LocalDate dataPedido = LocalDate.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getDataPedido());
                            LocalTime horaPedido = LocalTime.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getHoraPedido());
                            LocalDate dataPrint = LocalDate.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getDataPedido());
                            LocalTime horaPrint = LocalTime.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getHoraPedido());
                            LocalTime horaEntregaPrint = LocalTime.parse(ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getHoraEntregaPedido());
                            if (((dataPedido.isAfter(dataMin) || dataPedido.isEqual(dataMin)) && horaPedido.isAfter(horaLimMin))
                                    && ((dataPedido.isEqual(dataMax) || dataPedido.isBefore(dataMax)) && (horaPedido.isBefore(horaLimMax)))) {
                                System.out.println("CLIENTE: " + ProxyAdministrador.getClientes().get(k).getNomePessoa() + " " + ProxyAdministrador.getClientes().get(k).getSobrenomePessoa()
                                        + "\nENDEREÇO: " + ProxyAdministrador.getClientes().get(k).getEnderecoCliente()
                                        + "\nTELEFONE: " + ProxyAdministrador.getClientes().get(k).getTelefoneCliente()
                                        + "\nID DO PEDIDO: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getIdPedido()
                                        + "\nDATA DO PEDIDO: " + dataPrint.format(localDateFormatter)
                                        + "\nHORA DO PEDIDO: " + horaPrint.format(localHourFormatter)
                                        + "\nHORA ESTIMADA DE ENTREGA: " + horaEntregaPrint.format(localHourFormatter)
                                        + "\nVALOR: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getValorTotalPedido()
                                        + "\nSTATUS: " + ProxyAdministrador.getClientes().get(k).getPedidosCliente().get(j).getStatusPedido()
                                        + "\n__________________________________");
                            }
                        }
                    }

                    break;
                }

                case 3 -> {
                    encerrarLista = true;
                    break;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente!");
                    break;
                }

            }

        } while (encerrarLista == false);
    }

    /**
     * Função padrão de acesso às opções de modificação de pedidos cadastrados
     * no sistema
     *
     * @param idPedido Chave de busca de pedidos na lista de pedidos associados
     * a um cliente
     * @param Cl Objeto cliente o qual possui uma lista de pedidos associada a
     * ele
     */
    public void modificarPedido(int idPedido, Cliente Cl) {
        Scanner input = new Scanner(System.in);
        boolean menuAnterior = false;
        do {
            if (consultarPedido(idPedido, Cl) != null) {
                Pedido modPedido = consultarPedido(idPedido, Cl);
                System.out.println("DADOS DO PEDIDO\n___________________________\n");
                System.out.println("Id: " + modPedido.getIdPedido()
                        + "    Data:" + modPedido.getDataPedido()
                        + "    Hora: " + modPedido.getHoraPedido()
                        + "    Hora de entrega: " + modPedido.getHoraEntregaPedido()
                        + "    Valor total: " + modPedido.getValorTotalPedido()
                        + "    Status: " + modPedido.getStatusPedido()
                        + "\n_____________________________________________________");
                System.out.println("""
                               \nEscolha uma opção: 
                               1 - Alterar Hora de Entrega do Pedido 
                               2 - Alterar Status do Pedido 
                               3 - Adicionar itens
                               4 - Remover itens
                               5 - Fechar
                                """);
                int i = input.nextInt();
                switch (i) {
                    case 1 -> {
                        System.out.println("Entre com o novo horário de entrega: ");
                        System.out.printf("Hora: ");
                        String novaHora = input.nextLine();
                        System.out.printf("Minutos: ");
                        String novoMin = input.nextLine();

                        modPedido.setHoraEntregaPedido(novaHora + ":" + novoMin + ":" + "00");
                        System.out.println("Alteração realizada com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("""
                               Insira o código do novo status do pedido:  
                               1 - Aceito 
                               2 - Em preparo
                               3 - Saiu para entrega
                               4 - Entregue 
                               5 - Cancelado
                                """);
                        int novoStatus = input.nextInt();
                        if (novoStatus == 5) {
                            modPedido.setHoraEntregaPedido("00:00:00");
                        }
                        modPedido.setStatusPedido(novoStatus);
                        System.out.println("Alteração realizada com sucesso!");
                    }
                    case 3 -> {
                        System.out.printf("ID do item: ");
                        int novoItem = input.nextInt();

                        modPedido.setListaProdutos(novoItem);
                        modPedido.setValorTotalPedido(modPedido.getValorTotalPedido() + ProxyAdministrador.consultaProduto(novoItem).getValorProduto());
                        System.out.printf("Alteração realizada com sucesso!");
                    }
                    case 4 -> {
                        System.out.printf("ID do Pedido: ");
                        int removerItem = input.nextInt();
                        int f = 0;
                        for (int r = 0; r <= modPedido.getListaProdutos().size(); r++) {
                            f++;
                            if (modPedido.getListaProdutos().get(r) == removerItem) {
                                modPedido.getListaProdutos().remove(r);
                                modPedido.setValorTotalPedido(modPedido.getValorTotalPedido() - ProxyAdministrador.consultaProduto(removerItem).getValorProduto());
                                System.out.printf("Alteração realizada com sucesso!");
                                break;
                            } else if (f > modPedido.getListaProdutos().size()) {
                                System.out.println("Id inválido ou o produto não está associado a esse pedido. Tente novamente");
                                break;
                            }
                        }
                    }
                    case 5 -> {
                        menuAnterior = true;
                        break;
                    }

                    default -> {
                        System.out.println("Opção inválida, tente novamente.");

                    }

                }
            }

        } while (menuAnterior == false);

    }

    /**
     * Função padrão de consulta a um objeto do tipo Pedido associado a um
     * cliente
     *
     * @param idPedido Chave de busca do objeto pedido na base de pedidos de um
     * cliente
     * @param Cl Objeto cliente utilizado para acesso à lista de pedidos
     * @return Objeto do tipo pedido se a chave tiver sido previamente
     * cadastrada
     */
    //Questão 7 - Verificar e imprimir dados dos pedidos dos clientes tal como o status
    public Pedido consultarPedido(int idPedido, Cliente Cl) {
        Pedido attPedido = new Pedido();
        attPedido = null;

        for (int i = 0; i < Cl.getPedidosCliente().size(); i++) {
            if (Cl.getPedidosCliente().get(i).getIdPedido() == idPedido) {
                attPedido = Cl.getPedidosCliente().get(i);
            }
        }
        return attPedido;
    }

    /**
     * Função padrão de exclusão de pedidos do sistema Obs.: Função destinada à
     * correção de cadastros realizados indevidamente no sistema
     */
    public void excluirPedido() {
        System.out.printf("CPF: ");
        Scanner input = new Scanner(System.in);
        String CPF = input.nextLine();

        Cliente Cl = ProxyAdministrador.consultaCliente(CPF);

        if (Cl != null) {
            System.out.println("PEDIDOS CADASTRADOS\n___________________________");
            for (int i = 0; i < Cl.getPedidosCliente().size(); i++) {
                LocalDate dataPedido = LocalDate.parse(Cl.getPedidosCliente().get(i).getDataPedido());
                System.out.println("Id: " + Cl.getPedidosCliente().get(i).getIdPedido()
                        + "    Data: " + Cl.getPedidosCliente().get(i).getDataPedido()
                        + "    Hora: " + dataPedido.format(localDateFormatter));
            }

            System.out.printf("ID do Pedido: ");
            int idRemPedido = input.nextInt();

            for (int p = 0; p < Cl.getPedidosCliente().size(); p++) {
                if (Cl.getPedidosCliente().get(p).getIdPedido() == idRemPedido) {
                    Cl.getPedidosCliente().remove(p);
                    System.out.println("Pedido removido com sucesso.");
                }
            }
        }

    }

    /**
     * Função padrão para a exibição das estatísticas de venda em um determinaod
     * período de tempo
     */
    public void statsVendas() {
        Scanner input = new Scanner(System.in);
        String diaMin = "";
        String diaMax = "";
        String mesMin = "";
        String mesMax = "";
        String anoMin = "";
        String anoMax = "";
        boolean validaMax = false;
        boolean validaMin = false;

        while (validaMax == false || validaMin == false) {
            System.out.println("Insira o intervalo de tempo:\nDE\n_________________________");

            input = new Scanner(System.in);
            System.out.printf("Dia: (Ex.: 01) ");
            diaMin = input.nextLine();
            if (diaMin.length() == 1) {
                diaMin = "0" + diaMin;
            }

            System.out.printf("Mes (Ex.: 01): ");
            mesMin = input.nextLine();
            if (mesMin.length() == 1) {
                mesMin = "0" + mesMin;
            }

            System.out.printf("Ano (Ex.: 2002): ");
            anoMin = input.nextLine();

            System.out.println("ATÉ\n_______________________");

            System.out.printf("Dia(Ex.: 01): ");
            diaMax = input.nextLine();
            if (diaMax.length() == 1) {
                diaMax = "0" + diaMax;
            }

            System.out.printf("Mês (Ex.: 01): ");
            mesMax = input.nextLine();
            if (mesMax.length() == 1) {
                mesMax = "0" + mesMax;
            }

            System.out.printf("Ano (Ex.: 2002): ");
            anoMax = input.nextLine();

            String dataMin = anoMin + mesMin + diaMin;
            String dataMax = anoMax + mesMax + diaMax;

            validaMin = isDateValid(dataMin);
            validaMax = isDateValid(dataMax);
        }

        LocalDate dataMin = LocalDate.parse(anoMin + "-" + mesMin + "-" + diaMin);
        LocalDate dataMax = LocalDate.parse(anoMax + "-" + mesMax + "-" + diaMax);
        ArrayList<Cliente> listaClientes = ProxyAdministrador.getClientes();

        float receitaTotal = 0;
        int qntCancelados = 0;
        int qntAceitos = 0;
        int qntEntregues = 0;
        System.out.println("""
                                   RELATÓRIO DE VENDAS 
                                   Pedidos cadastrados no sistema
                                   ____________________________________________________________
                                   """);

        for (int k = 0; k < listaClientes.size(); k++) {
            for (int j = 0; j < listaClientes.get(k).getPedidosCliente().size(); j++) {
                LocalDate dataPedido = LocalDate.parse(listaClientes.get(k).getPedidosCliente().get(j).getDataPedido()); //passar o novo formater
                LocalTime horaPedido = LocalTime.parse(listaClientes.get(k).getPedidosCliente().get(j).getHoraPedido());
                if (((dataPedido.isEqual(dataMin)) || (dataPedido.isAfter(dataMin))) && ((dataPedido.isEqual(dataMax)) || (dataPedido.isBefore(dataMax)))) {
                    System.out.println(listaClientes.get(k).getNomePessoa().toUpperCase()
                            + " " + listaClientes.get(k).getSobrenomePessoa().toUpperCase()
                            + "    [" + listaClientes.get(k).getPedidosCliente().get(j).getIdPedido() + "]"
                            + "    " + dataPedido.format(localDateFormatter)
                            + "    " + horaPedido.format(localHourFormatter)
                            + "    STATUS: " + listaClientes.get(k).getPedidosCliente().get(j).getStatusPedido()
                            + "    " + listaClientes.get(k).getPedidosCliente().get(j).getValorTotalPedido()
                            + "\n_____________________________________________________________");
                    qntAceitos++;
                    switch (listaClientes.get(k).getPedidosCliente().get(j).getStatusPedido()) {
                        case 4 -> {
                            qntEntregues++;
                            receitaTotal += listaClientes.get(k).getPedidosCliente().get(j).getValorTotalPedido();
                        }
                        case 5 ->
                            qntCancelados++;
                        default -> {
                        }
                    }
                }
            }
        }

        System.out.println("Estatísticas de venda para o intervalo de " + dataMin.format(localDateFormatter) + " à " + dataMax.format(localDateFormatter));
        System.out.println("Qnt. Pedidos Aceitos: " + qntAceitos + "      Qnt. Pedidos Cancelados [5]: " + qntCancelados + "      Qnt. Pedidos Entregues[4]: " + qntEntregues + "      Receita total arrecadada: " + receitaTotal
                + "\n_________________________________________________________________");

    }

    /**
     * Função padrão para identificação de datas válidas
     *
     * @see <a href="https://www.youtube.com/watch?v=9wL1a987BWo/"> Feltex -
     * Trabalho com data e hora em JAVA </a>
     *
     * @see
     * <a href="https://pt.stackoverflow.com/questions/187272/como-verificar-se-a-data-é-válida-ou-inválida)">
     * User28595 - Resposta à "Como verificar se a data é válida ou inválida?"
     * (StackOverflow) </a>
     *
     * @param strDate Recebe uma data em formato string para validação
     * @return Estado da validação: true para datas válidas e false para datas
     * inválidas
     */
    public static boolean isDateValid(String strDate) {
        //Formatter de data
        String dateFormat = "uuuuMMdd";
        //Resolver Strict garante que seja feito dentro dos valores possíveis
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true; //Se for válida
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    /**
     * Função padrão de validação de horas
     *
     * @see <a href="https://www.youtube.com/watch?v=9wL1a987BWo/"> Feltex -
     * Trabalho com data e hora em JAVA </a>
     *
     * @see
     * <a href="https://pt.stackoverflow.com/questions/187272/como-verificar-se-a-data-é-válida-ou-inválida)">
     * User28595 - Resposta à "Como verificar se a data é válida ou inválida?"
     * (StackOverflow) </a>
     *
     * @param strHour Recebe uma hora em formato string para validação
     * @return Estado da validação: true para horas válidas e false para horas
     * inválidas
     */
    public static boolean isHourValid(String strHour) {
        //Formatter de hora
        String hourFormat = "HH:mm";
        //Resolver Strict garante que seja feito dentro dos valores possíveis
        DateTimeFormatter hourTimeFormatter = DateTimeFormatter.ofPattern(hourFormat).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalTime hour = LocalTime.parse(strHour, hourTimeFormatter);
            return true; //Se for válida
        } catch (DateTimeParseException f) {
            return false; //Se for inválida
        }
    }

    /**
     * Função padrão para acesso à modificação de senha do Colaborador
     *
     * @param Colab Objeto do tipo Colaborador que deve estar logado no sistema
     */
    public void modificarColaborador(Colaborador Colab) {
        String senhaAnt;
        String novaSenha;
        String senhaConf;
        Scanner input = new Scanner(System.in);
        System.out.printf("Senha anterior: ");
        senhaAnt = input.nextLine();
        System.out.printf("Nova senha: ");
        novaSenha = input.nextLine();
        System.out.printf("Confirmar nova senha: ");
        senhaConf = input.nextLine();

        if (Colab.getSenhaUsuario().equals(senhaAnt)) {
            if (novaSenha.equals(senhaConf)) {
                Colab.setSenhaUsuario(novaSenha);
                System.out.println("Alteração realizada com sucesso!\n");
            } else {
                System.out.println("Senhas não são iguais. Tente novamente.\n");
            }
        } else {
            System.out.println("Senha antiga não confere. Tente novamente.\n");
        }
    }

    /**
     *
     * @return Representação em String da classe intermediária de
     * funcionalidades geralmente disponíveis ao colaborador
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "ProxyColaborador";
    }

}
