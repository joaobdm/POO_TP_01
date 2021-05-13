
public class Diamante implements Fidelidade {
	
	private static final double PERCENTUALDESCONTO = 0.7;

	@Override
	public double checaFidelidade() 
	{
		return PERCENTUALDESCONTO;
	}

}
