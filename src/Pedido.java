import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private final double comprimento;
    private final double altura;
    private final String cor;
    private final LocalDateTime data;

    public Pedido(double comprimento, double altura, String cor, LocalDateTime data) {
        this.comprimento = comprimento;
        this.altura = altura;
        this.cor = cor;
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        return "Comprimento: " + comprimento + " metros, Altura: " + altura + " metros, Cor: " + cor + ", Data: " + data.format(formatter);
    }
}
