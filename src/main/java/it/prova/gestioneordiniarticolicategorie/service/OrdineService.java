package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineService {
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdine) throws Exception;
	
	public List<Ordine> TrovaOrdineDaCategoria(Long id) throws Exception;
	
	public Ordine TrovaOrdinePiuRecenteDaCategoria(Long id) throws Exception;
	
	public List<String> caricaCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno(int mese, int anno)
			throws Exception;
	
	public Integer sommaPrezzoSingoloArticoloConDestinatario(String nomeDestinatario) throws Exception;
	
	public List<String> listaIndirizziOrdiniAventiArticoliConSeriale (String seriale)throws Exception;

	public void eliminaOrdine(Ordine input) throws Exception;
	
	public void setOrdineDAO(OrdineDAO ordineDAO);
}
