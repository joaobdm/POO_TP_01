public class Regular implements IFidelidade {
    
	  private static final double PERCENTUALDESCONTO = 0.95;

    @Override
    public double checaFidelidade() {
        return PERCENTUALDESCONTO;
    }
}
