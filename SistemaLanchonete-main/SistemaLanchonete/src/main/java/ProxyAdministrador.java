
import br.com.lanchonete.pessoas.*;
import br.com.lanchonete.produtos.Produto;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe intermediária para funcionalidades geralmente disponíveis ao
 * Administrador
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
public class ProxyAdministrador {

    /**
     * Construtor padrão
     */
    public ProxyAdministrador() {
    }

    //COLABORADORES
    //Questão 5 - O sistema deve armazenar de forma estática 15 colaboradores
    private static Colaborador Colaboradores[] = new Colaborador[15];

    /**
     *
     * @return Lista de colaboradores cadastrados no sistema
     */
    public static Colaborador[] getColaboradores() {
        return Colaboradores;
    }

    /**
     * Função padrão para adição de novos colaboradores à lista mantida pelo
     * Sistema
     *
     * @param C Objeto colaborador a ser adicionado à lista de colaboradores
     */
    public void addColaboradores(Colaborador C) {
        for (int i = 0; i < 15; i++) {
            if (ProxyAdministrador.Colaboradores[i] == null) {
                ProxyAdministrador.Colaboradores[i] = C;
                break;
            }
        }

    }

    /**
     *
     * @param Colaboradores Lista de Colaboradores do negócio
     */
    public static void setColaboradores(Colaborador[] Colaboradores) {
        ProxyAdministrador.Colaboradores = Colaboradores;

    }

    /**
     * Função padrão para cadastro de novos colaboradores no sistema
     */
    //Questão 6 - Deve ser possível cadastrar os clientes no sistema e alterar seus atributos
    public void cadastroColaborador() {

        String nomeColaborador, sobrenomeColaborador, CPF, loginColaborador, senhaColaborador;

        Scanner input = new Scanner(System.in);

        //Input de dados 
        System.out.printf("Nome: ");
        nomeColaborador = input.nextLine();

        System.out.printf("Sobrenome: ");
        sobrenomeColaborador = input.nextLine();

        do {
            System.out.printf("CPF: ");
            CPF = input.nextLine();

            if (ValidaCPF(CPF) == false || consultaColaborador(CPF) != null) {
                System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
            }
        } while (ProxyAdministrador.ValidaCPF(CPF) == false || consultaColaborador(CPF) != null);

        System.out.printf("E-mail: ");
        loginColaborador = input.nextLine();

        System.out.printf("Senha: ");
        senhaColaborador = input.nextLine();

        //Cadastro
        Colaborador C = new Colaborador(nomeColaborador, sobrenomeColaborador, CPF, loginColaborador, senhaColaborador);
        addColaboradores(C);
        System.out.println("Cadastro realizado com sucesso!");
    }

    /**
     * Função padrão para consulta a um objeto do tipo Colaborador
     *
     * @param CPF Chave de comparação entre os objetos do tipo Colaborador
     * @return Objeto do tipo colaborador caso a chave esteja cadastrada no
     * sistema
     */
    public Colaborador consultaColaborador(String CPF) {

        String CPFColaborador = CPF;
        //Colaborador attColaborador = new Colaborador();
        Colaborador attColaborador = null;

        for (Colaborador colab : ProxyAdministrador.getColaboradores()) {
            if (colab != null) {
                if (CPFColaborador.equals(colab.getCPF())) {
                    attColaborador = colab;
                    break;
                }
            }

        }
        return attColaborador;
    }

    /**
     * Função padrão de acesso às opções de modificações do Colaborador
     *
     * @param CPF Chave de busca do objeto Colaborador na base de colaboradores
     * do sistema
     */
    //Questão 6 - Deve ser possível cadastrar os clientes no sistema e alterar seus atributos
    public void modificarColaborador(String CPF) {
        Scanner inputSwitch = new Scanner(System.in);

        boolean menuAnterior = false;
        do {
            String CPFColab = CPF;
            if (consultaColaborador(CPFColab) != null) {
                Colaborador modColab = consultaColaborador(CPFColab);
                System.out.println("DADOS COLABORADOR");
                System.out.println(modColab + "\n________________________________\n");
                System.out.println("""
                               Escolha uma opção: 
                               1 - Alterar Nome 
                               2 - Alterar CPF
                               3 - Alterar login
                               4 - Fechar
                                """);

                int i = inputSwitch.nextInt();
                Scanner inputDado = new Scanner(System.in);

                switch (i) {
                    case 1 -> {
                        inputDado = new Scanner(System.in);
                        System.out.printf("Novo nome: ");
                        String dado = inputDado.nextLine();
                        modColab.setNomePessoa(dado);

                        System.out.printf("Novo sobrenome: ");
                        dado = inputDado.nextLine();
                        modColab.setSobrenomePessoa(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }

                    case 2 -> {
                        String dado;
                        do {
                            System.out.printf("Novo CPF: ");
                            dado = inputDado.nextLine();
                            if (ProxyAdministrador.ValidaCPF(dado) == false || consultaColaborador(CPF) != null) {
                                System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
                            }
                        } while (ProxyAdministrador.ValidaCPF(dado) == false || consultaColaborador(CPF) != null);
                        modColab.setCPF(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        CPFColab = dado;
                        break;
                    }

                    case 3 -> {
                        String novoLogin;
                        String confirmLogin;
                        do {
                            System.out.printf("Novo login: ");
                            novoLogin = inputDado.nextLine();
                            System.out.printf("Confirmar novo login: ");
                            confirmLogin = inputDado.nextLine();

                            if (novoLogin.equals(confirmLogin)) {
                                modColab.setLoginUsuario(confirmLogin);
                                System.out.println("Alteração realizada com sucesso!");
                            } else {
                                System.out.println("Dados não conferem. Tente novamente!\n");
                            }

                        } while (!novoLogin.equals(confirmLogin));

                    }

                    case 4 -> {
                        menuAnterior = true;
                        break;
                    }

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } else {
                System.out.println("CPF inválido!");
            }
        } while (menuAnterior == false);

    }

    /**
     * Função padrão para remoção de colaboradores no sistema
     *
     * @param CPF Chave de busca do objeto Colaborador na base de colaboradores
     * do sistema
     */
    //Questão 6 - Deve ser possível cadastrar os clientes no sistema e alterar seus atributos
    public void excluirColaborador(String CPF) {
        String CPFColaborador = CPF;

        for (Colaborador colab : ProxyAdministrador.getColaboradores()) {
            if (colab != null && colab.getCPF().equals(CPFColaborador)) {
                colab = null;
                break;
            }
        }
        System.out.println("Alteração realizada com sucesso!");

    }

    /**
     * Função padrão para impressão de dados dos colaboradores do sistema
     */
    public void printColaboradores() {
        for (Colaborador colab : ProxyAdministrador.getColaboradores()) {
            if (colab != null) {
                System.out.println(colab);
            }
        }
    }

    /**
     * Função padrão para exibição de dados referentes a um colaborador
     * específico cadastrado no sistema
     *
     * @param CPF Chave de busca do objeto na base de colaboradores do sistema
     */
    public void printColaborador(String CPF) {
        if (consultaColaborador(CPF) != null) {
            System.out.println(consultaColaborador(CPF));
        } else {
            System.out.println("Colaborador não cadastrado.");
        }
    }

    //MANIPULAÇÃO DE CLIENTES
    //Questão 8 - Clientes e pedidos devem ser salvos de forma dinâmica
    private static ArrayList<Cliente> Clientes = new ArrayList<>();

    /**
     *
     * @return Lista de clientes cadastrados no sistema
     */
    public static ArrayList<Cliente> getClientes() {
        return Clientes;
    }

    //Questão 10 - Criar duas variáveis de classe que armazenam quantas instâncias foram criadas do tipo Cliente dentro da classe sistema
    //Questão 10a - Uma delas usando o enfoque em encapsulamento
    private static int qntClientesPrivate;
    //Questão 10b - Implementar usando o controle de acesso do tipo protected
    protected static int qntClientesProtected;

    /**
     *
     * @return Quantidade de clientes cadastrados no sistema
     */
    //Questão 10a - Uma delas usando o enfoque em encapsulamento
    //Questão 11 - Criar um método de classe que deve retornar quantas instâncias foram criadas do tipo cliente
    public static int getQntClientesPrivate() {
        return qntClientesPrivate;
    }

    /**
     * Incremento da quantidade de clientes cadastrados no sistema
     */
    //Questão 10a - Uma delas usando o enfoque em encapsulamento
    public static void setQntClientesPrivate() {
        int qnt = 0;
        for (Cliente cl : ProxyAdministrador.getClientes()) {
            qnt++;
        }
        ProxyAdministrador.qntClientesPrivate = qnt;
    }

    /**
     *
     * @return Quantidade de clientes cadastrados no sistema
     */
    //Questão 11 - Criar um método de classe que deve retornar quantas instâncias foram criadas do tipo cliente
    public static int getQntClientesProtected() {
        return qntClientesProtected;
    }

    /**
     * Incremento da quantidade de clientes cadastrados no sistema
     */
    public static void setQntClientesProtected() {
        int qnt = 0;
        for (Cliente cl : ProxyAdministrador.getClientes()) {
            qnt++;
        }
        qntClientesProtected = qnt;
    }

    /**
     *
     * @param Clientes Define a lista de clientes para a base do Sistema
     */
    public static void setClientes(ArrayList<Cliente> Clientes) {
        ProxyAdministrador.Clientes = Clientes;
    }

    /**
     * Função padrão para o cadastro de clientes no sistema
     */
    public void cadastroCliente() {
        String nomeCliente, sobrenomeCliente, CPF, enderecoCliente, telefoneCliente;

        Scanner input = new Scanner(System.in);

        //Input de dados 
        System.out.printf("Nome: ");
        nomeCliente = input.nextLine();
        System.out.printf("Sobrenome: ");
        sobrenomeCliente = input.nextLine();

        do {
            System.out.printf("CPF: ");
            CPF = input.nextLine();
            if (ProxyAdministrador.ValidaCPF(CPF) == false || consultaCliente(CPF) != null) {
                System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
            }
        } while (ProxyAdministrador.ValidaCPF(CPF) == false || consultaCliente(CPF) != null);

        System.out.printf("Endereço: ");
        enderecoCliente = input.nextLine();
        System.out.printf("Telefone: ");
        telefoneCliente = input.nextLine();

        Cliente cl = new Cliente(nomeCliente, sobrenomeCliente, CPF, enderecoCliente, telefoneCliente);
        ProxyAdministrador.Clientes.add(cl);
        System.out.println("Cadastro realizado com sucesso!");
        //Manipulação com membro devidamente encapsulado, requer o uso de um método para tanto
        setQntClientesPrivate();

        //Manipulação com membro protected. Permite o acesso direto à variável;
        qntClientesProtected++;
    }

    /**
     * Função para consulta a um objeto do tipo Cliente na base de clientes
     * cadastrados no sistema
     *
     * @param CPF Chave de busca na base de dados do Sistema
     * @return Objeto cliente caso a chave esteja devidamente cadastrada
     */
    public static Cliente consultaCliente(String CPF) {

        String CPFCliente = CPF;
        Cliente attCliente = new Cliente();
        attCliente = null;

        for (Cliente cliente : ProxyAdministrador.getClientes()) {
            if (CPFCliente.equals(cliente.getCPF())) {
                attCliente = cliente;
                break;
            }
        }
        return attCliente;
    }

    /**
     * Função padrão para a exclusão de clientes cadastrados no sistema
     *
     * @param CPF Chave de busca na base de dados de clientes cadastrados no
     * sistema
     */
    public void excluirCliente(String CPF) {
        String CPFCliente = CPF;

        for (Cliente cliente : ProxyAdministrador.getClientes()) {
            if (CPFCliente.equals(cliente.getCPF())) {
                ProxyAdministrador.getClientes().remove(cliente);
                break;
            }
        }
        System.out.println("Alteração realizada com sucesso!");
    }

    /**
     * Função padrão para a modificação de dados relativos a um cliente
     * cadastrado no sistema
     *
     * @param CPF Chave de busca do objeto cliente na base de dados de clientes
     * cadastrados no sistema
     */
    public void modificarCliente(String CPF) {

        Scanner inputSwitch = new Scanner(System.in);

        boolean menuAnterior = false;

        String CPFCli = CPF;
        do {
            if (consultaCliente(CPFCli) != null) {
                Cliente modCliente = consultaCliente(CPFCli);
                System.out.println("DADOS CLIENTE");
                System.out.println(modCliente + "\n______________________________");
                System.out.println("""
                               Escolha uma opção: 
                               1 - Alterar Nome
                               2 - Alterar CPF
                               3 - Alterar Endereço
                               4 - Alterar Telefone
                               5 - Fechar
                                """);

                int i = inputSwitch.nextInt();
                Scanner inputDado = new Scanner(System.in);

                switch (i) {
                    case 1 -> {
                        inputDado = new Scanner(System.in);
                        System.out.printf("Novo nome: ");
                        String dado = inputDado.nextLine();
                        modCliente.setNomePessoa(dado);

                        System.out.printf("Novo sobrenome: ");
                        dado = inputDado.nextLine();
                        modCliente.setSobrenomePessoa(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }

                    case 2 -> {
                        String dado;
                        do {
                            System.out.printf("Novo CPF: ");
                            dado = inputDado.nextLine();
                            if (ProxyAdministrador.ValidaCPF(dado) == false || consultaCliente(CPF) != null) {
                                System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
                            }
                        } while (ProxyAdministrador.ValidaCPF(dado) == false || consultaCliente(CPF) != null);
                        modCliente.setCPF(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        CPFCli = dado;
                        break;
                    }
                    case 3 -> {
                        System.out.printf("Novo endereço: ");
                        String dado = inputDado.nextLine();
                        modCliente.setEnderecoCliente(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }
                    case 4 -> {
                        System.out.printf("Novo telefone: ");
                        String dado = inputDado.nextLine();
                        modCliente.setTelefoneCliente(dado);
                        System.out.printf("Alteração realizada com sucesso!");
                        break;
                    }

                    case 5 -> {
                        menuAnterior = true;
                        break;
                    }

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } else {
                System.out.println("CPF inválido!");
            }
        } while (menuAnterior == false);

    }

    /**
     * Função padrão para exibição de dados de todos os clientes cadastrados no
     * sistema
     */
    public void printClientes() {
        //Questão 12 - Implementar a interface Comparator para as classes Cliente e Pedido
        ClienteComparator comparator = new ClienteComparator();
        Collections.sort(ProxyAdministrador.getClientes(), comparator);
        for (Cliente cliente : ProxyAdministrador.getClientes()) {
            if (cliente != null) {
                System.out.println(cliente);
            }
        }

    }

    /**
     * Função padrão para exibição de dados de um cliente em específico
     * cadastrado no sistema
     *
     * @param CPF Chave de busca do cliente na base de clientes cadastrados
     */
    public void printCliente(String CPF) {
        if (consultaCliente(CPF) != null) {
            System.out.println(consultaCliente(CPF));
        }
    }

    //PRODUTOS
    //LISTA DE PRODUTOS
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();

    //Controle da id dos produtos
    private static int numProdutos = 0;

    /**
     * @return Quantidade de produtos cadastrados no sistema
     */
    public static int getNumProdutos() {
        return numProdutos;
    }

    /**
     * Incremento da quantidade de produtos cadastrados no sistema
     *
     */
    public static void setNumProdutos() {
        for (Produto Pr : getListaProdutos()) {
            if (Pr != null) {
                numProdutos++;
            }
        }
    }

    /**
     *
     * @return Lista de produtos cadastrados no sistema
     */
    public static ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    /**
     *
     * @param listaProdutos Define a lista de produtos a ser cadastrada no
     * sistema
     */
    public static void setListaProdutos(ArrayList<Produto> listaProdutos) {
        ProxyAdministrador.listaProdutos = listaProdutos;
    }

    /**
     * Função padrão para o cadastro de produtos no sistema
     */
    public void cadastroProduto() {
        String nomeProduto, descricaoProduto;
        float valorProduto = 0;
        int idProduto;
        boolean valorValido = false;
        Scanner input = new Scanner(System.in);

        //INPUT DE DADOS
        System.out.printf("Nome: ");
        nomeProduto = input.nextLine();

        System.out.printf("Descrição: ");
        descricaoProduto = input.nextLine();

        do {
            try {
                System.out.printf("Valor (Ex.: 13,3): ");
                valorProduto = input.nextFloat();
                valorValido = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente!\n");
                input = new Scanner(System.in);
                valorProduto = 0;
                valorValido = false;
            }

        } while (valorValido == false);
        ProxyAdministrador.setNumProdutos();
        idProduto = ProxyAdministrador.getNumProdutos();

        Produto Pr = new Produto(nomeProduto, valorProduto, idProduto, descricaoProduto);
        ProxyAdministrador.getListaProdutos().add(Pr);

    }

    /**
     * Função padrão para exclusão de produtos cadastrados no sistema
     */
    public void excluirProduto() {
        System.out.println("PRODUTOS CADASTRADOS\n___________________________");

        for (Produto produto : ProxyAdministrador.listaProdutos) {
            System.out.println("[" + produto.getIdProduto() + "]   " + produto.getNomeProduto());
        }

        System.out.println("""
                           _________________________________________________
                           Exclusão de produtos
                           """);
        System.out.printf("Id do Produto: ");
        Scanner input = new Scanner(System.in);
        int idProduto = input.nextInt();

        for (Produto produto : ProxyAdministrador.getListaProdutos()) {
            if (idProduto == produto.getIdProduto()) {
                ProxyAdministrador.listaProdutos.remove(produto);
                break;
            }
        }
        System.out.println("Produto removido com sucesso.");
    }

    /**
     * Função padrão para consulta a um objeto do tipo produto na base de
     * produtos do sistema
     *
     * @param idProduto Chave de busca do objeto produto dentro da base de
     * produtos cadastrados
     * @return Objeto do tipo Produto caso a chave tenha sido previamente
     * cadastrada no sistema
     */
    public static Produto consultaProduto(int idProduto) {
        Produto attProduto = new Produto();
        attProduto = null;

        for (Produto produto : ProxyAdministrador.listaProdutos) {
            if (idProduto == produto.getIdProduto()) {
                attProduto = produto;
                break;
            }
        }

        return attProduto;
    }

    /**
     * Função padrão de acesso às opções de modificação dos dados de um produto
     *
     * @param idProduto Chave de busca de um objeto produto dentro da base de
     * produtos cadastrados no sistema
     */
    public void modificarProduto(int idProduto) {

        Scanner inputSwitch = new Scanner(System.in);

        boolean menuAnterior = false;
        do {
            if (consultaProduto(idProduto) != null) {
                Produto modProduto = consultaProduto(idProduto);
                System.out.println("DADOS DO PRODUTO");
                System.out.println(modProduto + "\n_____________________________________");
                System.out.println("""
                               Escolha uma opção: 
                               1 - Alterar Nome 
                               2 - Alterar Descrição 
                               3 - Alterar Valor
                               4 - Fechar
                                """);

                int i = inputSwitch.nextInt();
                Scanner inputDado = new Scanner(System.in);

                switch (i) {
                    case 1 -> {
                        System.out.printf("Novo nome: ");
                        String dado = inputDado.nextLine();

                        modProduto.setNomeProduto(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }

                    case 2 -> {
                        System.out.printf("Nova descrição: ");
                        String dado = inputDado.nextLine();

                        modProduto.setDescricaoProduto(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }

                    case 3 -> {
                        System.out.printf("Novo CPF: ");
                        float dado = inputDado.nextFloat();

                        modProduto.setValorProduto(dado);
                        System.out.println("Alteração realizada com sucesso!");
                        break;
                    }

                    case 4 -> {
                        menuAnterior = true;
                        break;
                    }

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } else {
                System.out.println("Identificador de produto inválido!");
                break;
            }
        } while (menuAnterior == false);

    }

    /**
     * Função padrão para exibição de produtos cadastrados no sistema Obs.:
     * Usada para acessar os índices associados a cada produto
     */
    public void consultaListaProdutos() {
        System.out.println("PRODUTOS CADASTRADOS\n___________________________");
        for (int i = 0; i < ProxyAdministrador.listaProdutos.size(); i++) {
            System.out.println("Id: " + ProxyAdministrador.listaProdutos.get(i).getIdProduto()
                    + "\t|\t Nome: " + ProxyAdministrador.listaProdutos.get(i).getNomeProduto());
        }
    }

    /**
     * Função padrão para exibição de dados completos de um produto específico
     * cadastrado no sistema
     */
    public void printProduto() {
        System.out.println("PRODUTOS CADASTRADOS\n___________________________");
        for (Produto produto : ProxyAdministrador.listaProdutos) {
            System.out.println("[" + produto.getIdProduto() + "]   " + produto.getNomeProduto());
        }
        System.out.println("CONSULTA\n_______________________");
        System.out.printf("Id do Produto: ");
        Scanner input = new Scanner(System.in);
        int idProduto = input.nextInt();

        if (consultaProduto(idProduto) != null) {
            System.out.println(consultaProduto(idProduto));
        }
    }

    //ADMINISTRADORES
    /**
     * Função padrão para exibição dos dados associados ao Administrador do
     * programa
     *
     * @param Adm Objeto administrador que deve estar logado no momento da
     * chamada
     */
    public void consultaAdm(Administrador Adm) {
        System.out.println(Adm);
    }

    //Modificar admnistrador 
    /**
     * Função padrão para acesso às opções de modificação de dados associados ao
     * Administrador
     *
     * @param Adm Objeto administrador que deve estar logado no sistema
     */
    public void modificarAdm(Administrador Adm) {

        boolean menuAnterior = false;
        do {
            System.out.println(Adm + "\n____________________________________________");
            Scanner input = new Scanner(System.in);
            System.out.println("""
                           Escolha uma opção:
                           1 - Alterar login
                           2 - Alterar senha
                           3 - Alterar CPF
                           4 - Alterar nome
                           5 - Fechar
                           """);
            int i = input.nextInt();
            switch (i) {
                case 1 -> {
                    input = new Scanner(System.in);
                    System.out.printf("Novo login: ");
                    String novoLogin = input.nextLine();
                    System.out.printf("Confirme o login: ");
                    String confirLogin = input.nextLine();

                    if (novoLogin.equals(confirLogin)) {
                        Adm.setLoginUsuario(confirLogin);
                        break;
                    } else {
                        System.out.println("Dados inseridos não conferem. Tente novamente");
                        break;
                    }
                }
                case 2 -> {
                    input = new Scanner(System.in);
                    System.out.printf("Senha anterior: ");
                    String senhaAnt = input.nextLine();
                    System.out.printf("Nova senha: ");
                    String novaSenha = input.nextLine();
                    System.out.printf("Confirmar nova senha: ");
                    String confirmSenha = input.nextLine();

                    if (Adm.getSenhaUsuario().equals(senhaAnt)) {
                        if (novaSenha.equals(confirmSenha)) {
                            Adm.setSenhaUsuario(novaSenha);
                            System.out.println("Alteração realizada com sucesso!");
                            break;
                        } else {
                            System.out.println("Senhas diferentes. Tente novamente.");
                            break;
                        }
                    } else {
                        System.out.println("Senha antiga não confere. Tente novamente");
                        break;
                    }
                }

                case 3 -> {

                    String novoCPF;
                    input = new Scanner(System.in);
                    do {
                        System.out.printf("Novo CPF: ");
                        novoCPF = input.nextLine();
                        if (ProxyAdministrador.ValidaCPF(novoCPF) == false || consultaColaborador(novoCPF) != null) {
                            System.out.println("Número de CPF inválido ou já cadastrado. Tente novamente!");
                        }
                    } while (ProxyAdministrador.ValidaCPF(novoCPF) == false || consultaColaborador(novoCPF) != null);
                    Adm.setCPF(novoCPF);
                    System.out.println("Alteração realizada com sucesso!");
                    break;
                }

                case 4 -> {
                    System.out.printf("Novo nome: ");
                    String novoNome = input.nextLine();
                    Adm.setNomePessoa(novoNome);

                    System.out.printf("Novo sobrenome: ");
                    String novoSobrenome = input.nextLine();
                    Adm.setSobrenomePessoa(novoSobrenome);

                    System.out.println("Alteração realizada com sucesso!");
                    break;
                }

                case 5 -> {
                    menuAnterior = true;
                    break;
                }

                default -> {
                    System.out.println("Opção inválida, tente novamente.");
                    break;
                }

            }

        } while (menuAnterior == false);

    }

    /**
     * Função padrão para a validação do número de CPF repassado como parâmetro
     * em cadastros de Clientes e Colaboradores
     *
     * @author Iago Mateus Ávila Fernandes
     * @author Mateus Henrique Machado
     * @param cpf Número do CPF inserido pelo usuário
     * @return Validade do CPF: true ou false
     */
    public static boolean ValidaCPF(String cpf) {
        //Validando se o CPF é formado apenas por numeros iguais
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            System.out.println("CPF inválido. Digite novamente.");
            return false;
        }

        // variaveis do décimo e décimo primeiro 
        char digito10;
        char digito11;
        int soma;
        int i;
        int r;
        int numero;
        int peso;

        try {
            // para calcular o primeiro digito "verificador"
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // para converter o caractere do CPF em um número inteiro
                // o 48 representa o zero tabela ASCII
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                digito10 = '0';
            } else {
                digito10 = (char) (r + 48);
            }

            //para calcular o segundo digito "verificador"
            soma = 0;
            //peso igual a 11, pois, o primeiro digito verificador já foi calculado
            peso = 11;
            for (i = 0; i < 10; i++) {
                // xS é a variavel a qual vai receber os valores das somas
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                // O xpeso sempre diminui de uma soma para a outra
                peso = peso - 1;
            }

            r = 11 - (soma % 11);

            if ((r == 10) || (r == 11)) {
                digito11 = '0';
            } else {
                digito11 = (char) (r + 48);
            }

            //Valida se os numeros informados batem o valor com os numeros 
            if ((digito10 == cpf.charAt(9))
                    && (digito11 == cpf.charAt(10))) {
                return (true);
            } else {
                System.out.println("CPF inválido. Digite novamente.");
                return (false);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel validar o CPF.");
            return (false);
        }
    }

    /**
     *
     * @return Representação String da classe intermediária de funcionalidades
     * do Administrador
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "ProxyAdministrador";
    }

}
