import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {

    private static final int QTD_MESES_INCREMENTAL = 12;
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {
        String arquivoCSV = "Volumetria_Exercicio.csv";
        String csvDivisor = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            List<Tabela> tabelas = new ArrayList<>();

            String linha;
            while ((linha = br.readLine()) != null) {
                List<String> campos = Arrays.asList(linha.split(csvDivisor));

                tabelas.add(extraiDadosDosCamposCSV(campos));
            }

            imprimeCalculosPorTabela(tabelas);
            imprimeTotais(tabelas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimeTotais(List<Tabela> tabelas) {
        long totalCargaInicial = 0;
        long totalCargaIncremental = 0;
        long totalCargaIncremental12Meses = 0;
        long totalVolumeTotal12Meses = 0;
        for (Tabela tabela : tabelas) {
            totalCargaInicial += tabela.getCargaInicial();
            totalCargaIncremental += tabela.getCargaIncremental();
            totalCargaIncremental12Meses += tabela.getCargaIncremental(QTD_MESES_INCREMENTAL);
            totalVolumeTotal12Meses += tabela.getVolumeTotal(QTD_MESES_INCREMENTAL);
        }

        System.out.println("Total de carga inicial: " + numberFormat.format(totalCargaInicial));
        System.out.println("Total de carga incremental: " + numberFormat.format(totalCargaIncremental));
        System.out.println("Total de carga incremental para " + QTD_MESES_INCREMENTAL + " meses: " + numberFormat.format(totalCargaIncremental12Meses));
        System.out.println("Total de volume total para " + QTD_MESES_INCREMENTAL + " meses: " + numberFormat.format(totalVolumeTotal12Meses));
    }

    private static void imprimeCalculosPorTabela(List<Tabela> tabelas) {
        for (Tabela tabela : tabelas) {
            System.out.println("Tabela: " + tabela.getNome());
            System.out.println("Total de bytes por registro: " + numberFormat.format(tabela.getTotalBytesRegistro()));
            System.out.println("Linhas iniciais consideradas: " + numberFormat.format(tabela.getLinhasIniciais()));
            System.out.println("Linhas incrementais consideradas: " + numberFormat.format(tabela.getLinhasIncrementais()));
            System.out.println("Carga inicial: " + numberFormat.format(tabela.getCargaInicial()));
            System.out.println("Carga incremental: " + numberFormat.format(tabela.getCargaIncremental()));
            System.out.println("Carga incremental para " + QTD_MESES_INCREMENTAL + " meses: " + numberFormat.format(tabela.getCargaIncremental(QTD_MESES_INCREMENTAL)));
            System.out.println("Volume total para " + QTD_MESES_INCREMENTAL + " meses: " + numberFormat.format(tabela.getVolumeTotal(QTD_MESES_INCREMENTAL)));
            System.out.println();
        }
    }

    private static Tabela extraiDadosDosCamposCSV(List<String> campos) {
        String nomeTabela = campos.get(0);
        List<String> nomesAtributos = new ArrayList<>();
        int indexInicialTipos;
        for (int i = 1;;++i) {
            if (campos.get(i).isEmpty()) {
                indexInicialTipos = i + 1;
                break;
            }
            nomesAtributos.add(campos.get(i));
        }
        List<String> tiposDeDados = new ArrayList<>();
        int indexInicialLinhas = nomesAtributos.size() + indexInicialTipos;
        for (int i = indexInicialTipos;i < indexInicialLinhas;++i) {
            tiposDeDados.add(campos.get(i));
        }
        long linhasIniciais = Long.parseLong(campos.get(indexInicialLinhas));
        long linhasIncrementais = Long.parseLong(campos.get(indexInicialLinhas + 1));

        List<Campo> camposTabela = new ArrayList<>();
        for (int i = 0;i < nomesAtributos.size();++i) {
            camposTabela.add(new Campo(nomesAtributos.get(i), tiposDeDados.get(i)));
        }

        return new Tabela(nomeTabela, camposTabela, linhasIniciais, linhasIncrementais);
    }

}
