package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	public List<Categoria> findByOrdine(Long id) throws Exception;
}
