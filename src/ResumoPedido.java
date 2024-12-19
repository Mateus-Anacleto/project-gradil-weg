public class ResumoPedido {
    private final double comprimentoSolicitado;
    private final double comprimentoVendido;
    private final int numTelas;
    private final int numPostes;
    private final int numFixadores;
    private final int numParafusos;
    private final double altura;

    public ResumoPedido(double comprimentoSolicitado, double comprimentoVendido, int numTelas, int numPostes, int numFixadores, int numParafusos, double altura) {
        this.comprimentoSolicitado = comprimentoSolicitado;
        this.comprimentoVendido = comprimentoVendido;
        this.numTelas = numTelas;
        this.numPostes = numPostes;
        this.numFixadores = numFixadores;
        this.numParafusos = numParafusos;
        this.altura = altura;
    }

    public double getComprimentoSolicitado() {
        return comprimentoSolicitado;
    }

    public double getComprimentoVendido() {
        return comprimentoVendido;
    }

    public double getDiferencaComprimento() {
        return comprimentoVendido - comprimentoSolicitado;
    }

    public int getNumTelas() {
        return numTelas;
    }

    public int getNumPostes() {
        return numPostes;
    }

    public int getNumFixadores() {
        return numFixadores;
    }

    public int getNumParafusos() {
        return numParafusos;
    }

    public double getAltura() {
        return altura;
    }
}
