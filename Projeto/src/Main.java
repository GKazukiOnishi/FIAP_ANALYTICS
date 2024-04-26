import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String arquivoCSV = "Volumetria_Exercicio.csv";
        String csvDivisor = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                List<String> campos = Arrays.asList(linha.split(csvDivisor));
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
                Integer linhasIniciais = Integer.valueOf(campos.get(indexInicialLinhas));
                Integer linhasIncrementais = Integer.valueOf(campos.get(indexInicialLinhas));
                
                getTotalBytesRegistro(tiposDeDados);
                
                System.out.println(getTotalBytesRegistro(tiposDeDados));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static int getTotalBytesRegistro(List<String> tiposDeDados) {
		int totalRegistro = 0;
		for (String tipo : tiposDeDados) {
			if (tipo.equals("INT")) {
				totalRegistro += 4;
			}
			if (tipo.equals("DATE")) {
				totalRegistro += 8;
			}
			if (tipo.contains("(")) {
				int indexAberturaParenteses = tipo.indexOf("(");
				int indexFechamentoParenteses = tipo.indexOf(")");
				String tamanhoStr = tipo.substring(indexAberturaParenteses + 1, indexFechamentoParenteses);
				totalRegistro += Integer.valueOf(tamanhoStr);
			}
		}
		return totalRegistro;
	}

}
