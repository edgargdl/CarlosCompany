package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import model.Movimiento;
import model.MovimientosView;


@RestController
@RequestMapping("/Movimientos")
@CrossOrigin(origins = "*")

public class Controller {
	
	@Autowired
	service.MovimientosService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<Movimiento> createUser(@RequestBody Movimiento input)
	{
		
	service.persist(input);
		return new ResponseEntity<Movimiento>(HttpStatus.OK);
	}
	
	@RequestMapping("/getMovimientos")
	public HttpEntity<List> getMovimientos(@RequestBody MovimientosView view)
	
	{
		MovimientosView movimientos = new MovimientosView();
		movimientos.populate();
		
		return new ResponseEntity<List>(service.getAllMovimientos(),HttpStatus.OK);
	
		
	}
	
}