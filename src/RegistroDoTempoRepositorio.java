import java.util.List;

public interface RegistroDoTempoRepositorio {

    List<String> datasEmQueChoveuMaisDe(double milimetros);

    List<RegistroDoTempo> getRegistros();

    String diaQueMaisChoveuNoAno(int ano);
}