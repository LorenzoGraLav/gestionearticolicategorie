package it.prova.gestioneordiniarticolicategorie.dao.articolo;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {
	public void unlinkCategoriaByArticolo(Long idArticoloinput) throws Exception;
	public void removeArticoloAndUnlinkByCategoria(Articolo articoloInstance) throws Exception;
	public int sumPriceArticoloByCategoria(Long id) throws Exception;
}
