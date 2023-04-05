
import br.com.lanchonete.pessoas.*;
import br.com.lanchonete.produtos.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Classe que contém métodos de manipulação de arquivos Json
 *
 * @author Mateus Henrique Machado
 * @author Iago Mateus Ávila Fernandes 
 * @see <a href="https://mvnrepository.com/artifact/com.google.code.gson/gson">
 * Gson MvnRepository </a>
 * @see
 * <a href="https://www.devmedia.com.br/como-converter-objetos-java-para-ou-de-json-com-a-biblioteca-gson/28091">
 * DevMedia - Como converter objetos java para Json com a biblioteca Gson </a>
 *
 * @see
 * <a href="https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt">
 * StackOverflow - Convert from json to a typed ArrayList </a>
 *
 * @see
 * <a href="https://stackoverflow.com/questions/27893342/how-to-convert-list-to-a-json-object-using-gson">
 * StackOverflow - How to convert list to a json object using gson </a>
 *
 * @see
 * <a href="https://www.baeldung.com/gson-list"> Baeldung - Gson List </a>
 *
 *
 */
//Questão 1 - Implementar todas as classes com base no diagrama de classes criado
//Questão 13 - Salve e recupere todas as informações do sistema em um arquivo json
public class manipularJson {

    /**
     * Construtor padrão
     */
    public manipularJson() {
    }

    /**
     * Função para descarregar dados referentes aos colaboradores cadastrados
     *
     * @param Co Lista de colaboradores do sistema
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void dumpColaborador(Colaborador[] Co) throws IOException {
        Gson jsonObject = new Gson();
        File colaboradorFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Colaboradores.json");

        FileWriter colaboradorWriter = null;
        String dadosColaboradores = jsonObject.toJson(Co);

        try {
            colaboradorWriter = new FileWriter("src\\main\\java\\SistemaLanchoneteArquivos\\Colaboradores.json");
            colaboradorWriter.write(dadosColaboradores);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            colaboradorWriter.close();
        }
    }

    /**
     * Função de assimilação dos dados referentes aos colaboradores existes no
     * arquivo
     *
     * @return Lista de colaboradores do sistema
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public Colaborador[] assimilateColaborador() throws IOException {
        Gson jsonObject = new Gson();
        File colabFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Colaboradores.json");

        try {
            String dadosColab = new String(Files.readAllBytes(Paths.get(colabFile.toURI())));
            Colaborador[] colabC = jsonObject.fromJson(dadosColab, new TypeToken<Colaborador[]>() {
            }.getType());
            return colabC;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos Clientes cadastrados
     *
     * @param Cl Lista de clientes do sistema
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void dumpCliente(ArrayList<Cliente> Cl) throws IOException {
        Gson jsonObject = new Gson();
        File clienteFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Clientes.json");
        FileWriter clienteWriter = null;
        String dadosCliente = jsonObject.toJson(Cl);

        try {
            clienteWriter = new FileWriter("src\\main\\java\\SistemaLanchoneteArquivos\\Clientes.json");
            clienteWriter.write(dadosCliente);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clienteWriter.close();
        }

    }

    /**
     * Função para assimilação dos dados referentes aos clientes no arquivo
     *
     * @return Lista de Clientes
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public ArrayList<Cliente> assimilateCliente() throws IOException {
        Gson jsonObject = new Gson();
        File clienteFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Clientes.json");

        try {
            String dadosCliente = new String(Files.readAllBytes(Paths.get(clienteFile.toURI())));
            ArrayList<Cliente> ClienteC = jsonObject.fromJson(dadosCliente, new TypeToken<ArrayList<Cliente>>() {
            }.getType());
            return ClienteC;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos extrados de pedidos
     * cadastrados
     *
     * @param extratosPedidos Lista de extratos de Pedidos
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    //Questão 9 - Cada pedido efetuado vai gerar um extrato que deve ser salvo junto com a informação do cliente que fez o pedido
    public void dumpExtratosPedidos(ArrayList<String> extratosPedidos) throws IOException {
        Gson jsonObject = new Gson();
        File pedidoFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Pedidos.json");
        FileWriter pedidoWriter = null;
        String dadosPedidos = jsonObject.toJson(extratosPedidos);

        try {
            pedidoWriter = new FileWriter("src\\main\\java\\SistemaLanchoneteArquivos\\Pedidos.json");
            pedidoWriter.write(dadosPedidos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pedidoWriter.close();
        }
    }

    /**
     * Função de assimilação de dados relativos aos Extratos de Pedidos
     *
     * @return Lista de extratos de pedidos
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    //Questão 9 - Cada pedido efetuado vai gerar um extrato que deve ser salvo junto com a informação do cliente que fez o pedido
    public ArrayList<String> assimilateExtratosPedidos() throws IOException {
        Gson jsonObject = new Gson();
        File clienteFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Pedidos.json");

        try {
            String dadosCliente = new String(Files.readAllBytes(Paths.get(clienteFile.toURI())));
            ArrayList<String> extratosPedidos = jsonObject.fromJson(dadosCliente, new TypeToken<ArrayList<String>>() {
            }.getType());
            return extratosPedidos;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos produtos cadastrados
     *
     * @param Po Lista de produtos cadastrados
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void dumpProdutos(ArrayList<Produto> Po) throws IOException {
        Gson jsonObject = new Gson();
        File produtoFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Produtos.json");
        FileWriter produtoWriter = null;
        String dadosProdutos = jsonObject.toJson(Po);
        try {
            produtoWriter = new FileWriter("src\\main\\java\\SistemaLanchoneteArquivos\\Produtos.json");
            produtoWriter.write(dadosProdutos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            produtoWriter.close();
        }

    }

    /**
     * Função para assimilação de dados referentes aos produtos cadastrados
     *
     * @return Lista de produtos
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public ArrayList<Produto> assimilateProduto() throws IOException {
        Gson jsonObject = new Gson();
        File produtoFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Produtos.json");

        try {
            String dadosProduto = new String(Files.readAllBytes(Paths.get(produtoFile.toURI())));
            ArrayList<Produto> ProdutoP = jsonObject.fromJson(dadosProduto, new TypeToken<ArrayList<Produto>>() {
            }.getType());
            return ProdutoP;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Função para descarregar dados referentes aos Administrador do sistema
     *
     * @param Adm Objeto do Administrador
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void dumpAdministrador(Administrador Adm) throws IOException {
        Gson jsonObject = new Gson();
        File admFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Administrador.json");
        FileWriter admWriter = null;
        String dadosAdm = jsonObject.toJson(Adm);
        try {
            admWriter = new FileWriter("src\\main\\java\\SistemaLanchoneteArquivos\\Administrador.json");
            admWriter.write(dadosAdm);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            admWriter.close();
        }
    }

    /**
     * Função para assimilação dos dados do Administrador salvos no sistema
     *
     * @return Objeto do tipo Administrador
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public Administrador assimilateAdministrador() throws IOException {

        Gson jsonObject = new Gson();
        File admFile = new File("src\\main\\java\\SistemaLanchoneteArquivos\\Administrador.json");
        try {
            String dadosAdm = new String(Files.readAllBytes(Paths.get(admFile.toURI())));
            Administrador admMain = jsonObject.fromJson(dadosAdm, new TypeToken<Administrador>() {
            }.getType());
            return admMain;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Função destinada à chamada das funções de assimilação de dados de forma
     * generalizada
     *
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void assimilateAll() throws IOException {
        ProxyAdministrador.setColaboradores(assimilateColaborador());
        ProxyAdministrador.setClientes(assimilateCliente());
        ProxyAdministrador.setListaProdutos(assimilateProduto());

    }

    /**
     * Função destinada à chamada das funções de descarregamento de dados de
     * forma generalizada
     *
     * @param menuColab
     * @throws IOException Exceção associada à manipulação de dados Json
     */
    public void dumpAll(ProxyColaborador menuColab) throws IOException {
        dumpColaborador(ProxyAdministrador.getColaboradores());
        dumpCliente(ProxyAdministrador.getClientes());
        dumpProdutos(ProxyAdministrador.getListaProdutos());
        dumpExtratosPedidos(menuColab.extratosPedidos());
    }

    /**
     *
     * @return Representação em String da Classe de manipulação de arquivos
     */
    //Questão 3 - Sobrescrever o método toString() de todas as classes implementadas
    @Override
    public String toString() {
        return "Manipulação de Arquivos JSON";
    }

}
