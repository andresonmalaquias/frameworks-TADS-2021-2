package ifam.edu;

import ifam.edu.dao.CidadeDAO;
import ifam.edu.dao.DAOGenerico;
import ifam.edu.dao.EstadoDAO;
import ifam.edu.dao.PessoaDAO;
import ifam.edu.model.*;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {

    private static void inserir(){
        EntityManager em = JPAUtil.getEntityManager();

        Estado e = new Estado();
        e.setNome("São Paulo");
        e.setSigla("SP");

        Cidade c = new Cidade();
        c.setNome("Campinas");

        c.setEstado(e);

        Pessoa p = new Pessoa();

        p.setNome("Maria");
        p.setTelefone("3612-1234");
        p.setDocumento("123456");

        p.setCidade(c);

        //Persiste no Banco

        em.getTransaction().begin();

        //Persistindo os objetos (Managed)
        em.persist(e);
        em.persist(c);
        em.persist(p);

        em.getTransaction().commit();
    }

    private static void inserirPessoaComCidadeEEstadoNoBD(){
        EntityManager em = JPAUtil.getEntityManager();

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Paulo");
        pessoa.setTelefone("(92) 3355-1122");
        pessoa.setDocumento("111222");
        pessoa.setSexo(SexoEnum.MASCULINO);

        Cidade cidade = em.find(Cidade.class,8);

        pessoa.setCidade(cidade);

        //Persiste no Banco

        em.getTransaction().begin();

        //Persistindo os objetos (Managed)
        em.persist(pessoa);

        em.getTransaction().commit();
        em.close();
    }

    private static void  consultar(){
        EntityManager em = JPAUtil.getEntityManager();

        Cidade cidade = em.find(Cidade.class,5);

        System.out.println("Cidade ID:" + cidade.getId());
        System.out.println("Cidade Nome:" + cidade.getNome());

        Pessoa pessoa = em.find(Pessoa.class,9);

        System.out.println("Pessoa ID:" + pessoa.getId());
        System.out.println("Pessoa Nome:" + pessoa.getNome());
        System.out.println("Pessoa Cidade Nome:" + pessoa.getCidade().getNome());
        System.out.println("Pessoa Estado Nome:" + pessoa.getCidade().getEstado().getNome());

        em.close();
    }

    private static void remover(){

        //Removendo no Banco
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        //remove
        Estado estado = em.find(Estado.class, 11);
        em.remove(estado);

        //Persistindo os objetos (Managed)
        //em.persist(pessoa);

        em.getTransaction().commit();
        em.close();
    }

    private static void inserirEstado(){

        Estado estado = new Estado();
        estado.setNome("Goias");
        estado.setSigla("Go");

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        //Persistindo os objetos (Managed)
        em.persist(estado);

        em.getTransaction().commit();
        em.close();
    }

    private static void inserirEstadoAtravesDoDao(){
        Estado e = new Estado();
        e.setNome("Santa Catarina");
        e.setSigla("SC");

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        entityManager.getTransaction().begin();

        dao.salvar(e);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    private static void consultarEstadoAtravesDoDao(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        Estado estado = dao.consultar(12);

        System.out.println(estado);

        entityManager.close();

    }

    private static  void removerEstadoAtravesDoDAO(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        entityManager.getTransaction().begin();

        dao.remover(12);

        entityManager.getTransaction().commit();

        entityManager.close();

    }

    private static  void listarEstadoAtravesDoDAO(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        List<Estado> estados = dao.listar();

        for (Estado e: estados) {
            System.out.println(e);
        }

        entityManager.close();

    }

    private static void inserirCidadeAtravesDoDao(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO estadoDAO = new EstadoDAO(entityManager);

        Estado estado = estadoDAO.consultar(15);

        Cidade cidade = new Cidade();
        cidade.setNome("Florianopolis");
        cidade.setEstado(estado);

        CidadeDAO dao = new CidadeDAO();

        dao.salvar(cidade);
    }

    private static void consultarCidadeAtravesDoDao(){

        CidadeDAO dao = new CidadeDAO();

        Cidade cidade = dao.consultar(16);

        System.out.println(cidade);
    }

    private static  void removerCidadeAtravesDoDAO(){
        CidadeDAO dao = new CidadeDAO();

        dao.remover(16);
    }

    private static  void listarCidadeAtravesDoDAO(){
        CidadeDAO dao = new CidadeDAO();

        List<Cidade> cidades = dao.listar();

        for (Cidade c: cidades) {
            System.out.println(c);
        }

    }

    public static void inserirAtravesDoDAOGenerico(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        DAOGenerico<Estado> estadoDAO = new DAOGenerico<Estado>(entityManager, Estado.class);

        Estado e = new Estado();
        e.setNome("Maranhao");
        e.setSigla("MA");

        entityManager.getTransaction().begin();

        estadoDAO.salvar(e);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void consultarAtravesDoDAOGenerico(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        DAOGenerico<Estado> estadoDAO = new DAOGenerico<Estado>(entityManager, Estado.class);

        Estado estado = estadoDAO.consultar(15);

        System.out.println(estado);

        entityManager.close();
    }

    public static void removerAtravesDoDAOGenerico(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        DAOGenerico<Estado> estadoDAO = new DAOGenerico<Estado>(entityManager, Estado.class);

        entityManager.getTransaction().begin();

        estadoDAO.remover(17);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public static void listarAtravesDoDAOGenerico(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        DAOGenerico<Estado> estadoDAO = new DAOGenerico<Estado>(entityManager, Estado.class);

        List<Estado> estados = estadoDAO.listar();

        for (Estado e: estados) {
            System.out.println(e);
        }

        entityManager.close();
    }

    public static void listarPorNomeAtravesPessoaDAO(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        PessoaDAO pessoaDAO = new PessoaDAO(entityManager);

        List<Pessoa> pessoas = pessoaDAO.listarPorNomeParcial("a");

        for (Pessoa p: pessoas) {
            System.out.println(p);
        }

        entityManager.close();
    }

    public static void testandoEnumeration(){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome("José");
        pessoa.setTelefone("92 2222-5555");
        pessoa.setSexo(SexoEnum.MASCULINO);

        System.out.println("Pessoa: " +pessoa.getNome()+", "+pessoa.getSexo());
    }

    public static void testandoFindGetReference(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoaGetReference = entityManager.getReference(Pessoa.class, 18);

       //lazy

        System.out.println("Executei o GetReference");

        System.out.println(pessoaGetReference);


        //Eager
        Pessoa pessoaFind =  entityManager.find(Pessoa.class, 18);

        System.out.println("Executei o Find");

        System.out.println(pessoaGetReference);
    }

    public static  void testandoMerge(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Pessoa pessoa = entityManager.find(Pessoa.class, 18);

        System.out.println("Nome: " + pessoa.getNome());

        entityManager.detach(pessoa);

        pessoa.setNome("Juliana alterado");

        //Managed
        Pessoa pessoaMerge = entityManager.merge(pessoa);

        pessoaMerge.setNome("Juliana - Merge");

        pessoa.setNome("Juliana - Detach");

        System.out.println("Pessoa Original: " + pessoa);

        System.out.println("PessoaMerge: " + pessoaMerge);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static  void testandoMerge2(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("João Paulo");

        pessoa.setDocumento("11111");

        //Managed
        Pessoa pessoaMerge = entityManager.merge(pessoa);

        pessoaMerge.setNome("João Paulo - Merge Alterado");

        pessoa.setNome("João Paulo - Original");

        System.out.println("Pessoa Original: " + pessoa);

        System.out.println("PessoaMerge: " + pessoaMerge);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static  void testaPessoaCidadeAssociacao(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade c = new Cidade();
        c.setNome("Floripa");

        Pessoa p1 = new Pessoa();
        p1.setNome("Mariana de Floripa");
        p1.setDocumento("F123");
        p1.setCidade(c);

        Pessoa p2 = new Pessoa();
        p2.setNome("Janaina de Floripa");
        p2.setDocumento("F124");
        p2.setCidade(c);

        entityManager.getTransaction().begin();

        entityManager.persist(p1);
        entityManager.persist(p2);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static  void testaCidadePessoaListOneToMany(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade cidade = entityManager.find(Cidade.class, 22);

        System.out.println("Cidade: " + cidade);

        for(Pessoa p: cidade.getPessoas()){
            System.out.println("Pessoa: " + p.getNome());
        }

        entityManager.close();

    }

    public static void testaCidadeEPessoaRelacionamento(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade c1 = new Cidade();
        c1.setNome("Belem");

        Interesse i1 = new Interesse("Jogos");
        Interesse i2 = new Interesse("Livros");
        Interesse i3 = new Interesse("Filme");

        Pessoa p1 = new Pessoa();
        p1.setNome("Paula");
        p1.setDocumento("PL002");
        p1.setCidade(c1);

        //de pessoa insere interesse
        p1.addInteresse(i1);
        p1.addInteresse(i2);
        p1.addInteresse(i3);

        //de interesse insere pessoa
        i1.addPessoa(p1);
        i2.addPessoa(p1);
        i3.addPessoa(p1);

        entityManager.getTransaction().begin();

        entityManager.persist(c1);

        entityManager.persist(p1);

        entityManager.persist(i1);

        entityManager.persist(i2);

        entityManager.persist(i3);

        entityManager.getTransaction().commit();

        entityManager.close();



    }

    public static void testarCarregamentoNoRelacionamento(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class, 2);

        System.out.println("Fiz o Find");

        System.out.println(pessoa);

        System.out.println("Mostrei a pessoa!");


        for(Interesse i: pessoa.getInteresses()){
            System.out.println(i);
        }

        System.out.println("Mostrei os Interesses da pessoa!");

        entityManager.close();
    }


    public static void main(String[] args) {

        //inserir();

        //inserirPessoaComCidadeEEstadoNoBD();

        //inserirEstado();

        //consultar();

        //remover();

        //inserirEstadoAtravesDoDao();

        //consultarEstadoAtravesDoDao();

        //removerEstadoAtravesDoDAO();

        //listarEstadoAtravesDoDAO();


        //inserirCidadeAtravesDoDao();

        //removerCidadeAtravesDoDAO();

        //consultarCidadeAtravesDoDao();

// DAOGenerico

        //inserirAtravesDoDAOGenerico();

        //consultarAtravesDoDAOGenerico();

        //removerAtravesDoDAOGenerico();

        //listarAtravesDoDAOGenerico();

        //listarPorNomeAtravesPessoaDAO();

//outros testes

        //testandoEnumeration();

        //testandoFindGetReference();

        //testandoMerge();

        //testandoMerge2();

        //testaPessoaCidadeAssociacao();

        //testaCidadePessoaListOneToMany();

        //testaCidadeEPessoaRelacionamento();

        testarCarregamentoNoRelacionamento();

    }
}
