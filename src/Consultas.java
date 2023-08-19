import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Consultas implements RegistroDoTempoRepositorio {

    private List<RegistroDoTempo> registros;

    @SuppressWarnings("unused")
    private Predicate<RegistroDoTempo> consulta;

    public Consultas(List<RegistroDoTempo> registros) {
        this.registros = registros;
        this.consulta = r -> true;
    }

    @Override
    public List<RegistroDoTempo> getRegistros() {
        return registros;
    }

    @Override
    public String diaQueMaisChoveuNoAno(int ano) {
        RegistroDoTempo registro = registros
                .stream()
                .filter(reg -> reg.getAno() == ano)
                .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
                .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia() + "/" + registro.getMes() + "/" + registro.getAno() + ", "
                + registro.getPrecipitacao();
        return resp;
    }

    @Override
    public List<String> datasEmQueChoveuMaisDe(double milimetros) {
        return registros
                .stream()
                .filter(r -> r.getPrecipitacao() > milimetros)
                .map(r -> r.getDia() + "/" + r.getMes() + "/" + r.getAno())
                .toList();
    }

    public void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta) {
        this.consulta = consulta;
    }

    public List<Data> diasEmQue(Predicate<RegistroDoTempo> condicao) {
        ArrayList<Data> data = new ArrayList<>();
        for (RegistroDoTempo reg : registros) {
            if (condicao.test(reg)) {
                data.add(new Data(reg.getDia(), reg.getMes(), reg.getAno()));
            }
        }
        return data;
    }
}