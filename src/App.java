import java.util.List;
import java.util.function.Predicate;

public class App {

    public void executar() {
        LeArquivo leitor = new LeArquivo();
        List<RegistroDoTempo> registros = leitor.carregaDados();

        Consultas consultas = new Consultas(registros);

        System.out.println("Dia em que mais choveu no ano de 1980:");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));

        System.out.println("Datas em que choveu mais de 90 milímetros:");
        consultas.datasEmQueChoveuMaisDe(90).forEach(System.out::println);

        Predicate<RegistroDoTempo> cond = reg -> reg.getTempMaxima() == 30.0;
        List<Data> datas = consultas.diasEmQue(cond);
        System.out.println("Datas:");
        datas.forEach(System.out::println);
    }
}