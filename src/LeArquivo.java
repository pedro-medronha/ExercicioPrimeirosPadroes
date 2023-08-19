import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeArquivo {

    private String nArq;

    public LeArquivo() {
        this.nArq = "poa_temps.txt";
    }

    public List<RegistroDoTempo> carregaDados() {
        List<RegistroDoTempo> registros = new ArrayList<>();

        String currDir = Paths.get("").toAbsolutePath().toString();

        String nomeCompleto = currDir + "/" + nArq;
        System.out.println(nomeCompleto);

        Path path = Paths.get(nomeCompleto);

        String linha = "";
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            sc.nextLine();
            while (sc.hasNext()) {
                linha = sc.nextLine();
                String dados[] = linha.split(" ");
                String data[] = dados[0].split("/");
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int ano = Integer.parseInt(data[2]);
                double precipitacao = Double.parseDouble(dados[1]);
                double tempMaxima = Double.parseDouble(dados[2]);
                double tempMinima = Double.parseDouble(dados[3]);
                double horasInsolacao = Double.parseDouble(dados[4]);
                double temperaturaMedia = Double.parseDouble(dados[5]);
                double umidadeRelativaDoAr = Double.parseDouble(dados[6]);
                double velocidadeDoVento = Double.parseDouble(dados[7]);
                RegistroDoTempo reg = new RegistroDoTempo(dia, mes, ano, precipitacao, tempMaxima, tempMinima,
                        horasInsolacao, temperaturaMedia, umidadeRelativaDoAr, velocidadeDoVento);
                registros.add(reg);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

        return registros;
    }
}