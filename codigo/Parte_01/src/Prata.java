public class Prata implements IFidelidade {
	private static final double PERCENTUALDESCONTO = 0.9;
    @Override
    public double checaFidelidade() {
        return PERCENTUALDESCONTO;
    }
}
