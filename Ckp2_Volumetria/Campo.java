public class Campo {
    private String nome;
    private String tipo;
    private int tamanho;

    public Campo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = calculaTamanho();
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    private int calculaTamanho() {
        if (tipo.equals("INT")) {
            return 4;
        }
        if (tipo.equals("DATE")) {
            return 8;
        }
        if (tipo.contains("(")) {
            int indexAberturaParenteses = tipo.indexOf("(");
            int indexFechamentoParenteses = tipo.indexOf(")");
            String tamanhoStr = tipo.substring(indexAberturaParenteses + 1, indexFechamentoParenteses);
            return Integer.parseInt(tamanhoStr);
        }
        return 0;
    }
}
