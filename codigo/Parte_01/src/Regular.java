public class Regular implements IFidelidade {
    
	  private static final double PERCENTUALDESCONTO = 1;

    @Override
    public double checaFidelidade() {
        return PERCENTUALDESCONTO;
    }
}