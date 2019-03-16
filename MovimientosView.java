import java.util.List;

import model.Catalogo;
import model.Movimiento;
import service.CatalogosService;
import service.MovimientosService;

public class MovimientosView {
	
	

	
	private List <MovimientosConCatalogo> movwithcata;
	private List <Catalogo> catalogos;


	public List <MovimientosConCatalogo> getMovimientoConCatalogo() {
		return movwithcata;
	}

	public void setMovimientoConCatalogo(List <MovimientosConCatalogo> mov) {
		movwithcata = mov;
	}
	
	
	void populate()
	{
		MovimientosService service = new MovimientosService();
		CatalogosService catService = new CatalogosService();
		
		List <Movimiento> lista =  service.getAllMovimientos();
		catalogos = catService.getAllCatalogos();
		
		MovimientosConCatalogo temp = new MovimientosConCatalogo();
		
		for (Movimiento movimiento : lista) {
			temp.setMovimiento(movimiento);
			temp.setNameCatalogo( 
					catService.findById(movimiento.getIdCatalogo()).getNombre());
	
		}
	}

}
