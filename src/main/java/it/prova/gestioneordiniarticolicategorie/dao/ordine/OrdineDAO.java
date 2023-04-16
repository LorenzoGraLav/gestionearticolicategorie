package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	public List<Ordine> allByCategoria(Long id)throws Exception;
	public Ordine getOrdinePiuRecenteByCategoria(Long id) throws Exception;
	public List<String> getCodiciDiCategorieMeseEAnno(int mese, int anno) throws Exception;
	public Integer getSumPriceOfDestinatario(String nomeDestinatario) throws Exception;
	public List<String> listaDiIndirizziDiOrdiniAventiArticoliConSeriale (String seriale)throws Exception;
}
