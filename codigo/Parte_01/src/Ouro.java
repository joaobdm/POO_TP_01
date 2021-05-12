public class Ouro implements IFidelidade {
	private static final double PERCENTUALDESCONTO = 0.8;
    @Override
    public double checaFidelidade() {
        return PERCENTUALDESCONTO;
    }
}