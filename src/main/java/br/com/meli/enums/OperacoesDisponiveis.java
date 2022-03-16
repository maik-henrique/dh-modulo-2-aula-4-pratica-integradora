package br.com.meli.enums;

public enum OperacoesDisponiveis {
    REGISTRAR_CLIENTE("Registrar Clientes", 0),
    LISTAR_CLIENTES("Excluir Cliente", 1),
    BUSCAR_CLIENTES("Buscar cliente", 2),
    REGISTRAR_FATURA("Registrar fatura", 3),
    IMPRIMIR_FATURAS("Imprimir faturas", 4),
    ENCERRAR_APLICACAO("Encerrar aplicação", 5);

    private final String label;
    private final int opcao;

    OperacoesDisponiveis(String label, int opcao) {
        this.label = label;
        this.opcao = opcao;
    }

    public String getLabel() {
        return label;
    }

    public int getOpcao() {
        return opcao;
    }
}
