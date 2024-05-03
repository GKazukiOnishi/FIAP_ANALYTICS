import java.util.List;

public class Tabela {
    private String nome;
    private List<Campo> campos;
    private long linhasIniciais;
    private long linhasIncrementais;

    public Tabela(String nome, List<Campo> campos, long linhasIniciais, long linhasIncrementais) {
        this.nome = nome;
        this.campos = campos;
        this.linhasIniciais = linhasIniciais;
        this.linhasIncrementais = linhasIncrementais;
    }

    public String getNome() {
        return nome;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public long getLinhasIniciais() {
        return linhasIniciais;
    }

    public long getLinhasIncrementais() {
        return linhasIncrementais;
    }

    public long getTotalBytesRegistro() {
        int totalRegistro = 0;
        for (Campo campo : campos) {
            totalRegistro += campo.getTamanho();
        }
        return totalRegistro;
    }

    public long getCargaInicial() {
        return linhasIniciais * getTotalBytesRegistro();
    }

    public long getCargaIncremental() {
        return linhasIncrementais * getTotalBytesRegistro();
    }

    public long getCargaIncremental(int qtdMeses) {
        return qtdMeses * getCargaIncremental();
    }

    public long getVolumeTotal(int qtdMeses) {
        return getCargaInicial() + getCargaIncremental(qtdMeses);
    }
}
