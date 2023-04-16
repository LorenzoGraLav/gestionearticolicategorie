package it.prova.gestioneordiniarticolicategorie.test;

import java.time.LocalDate;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.OrdineService;

public class MyTest {

	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {

			System.out.println(
					"In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			// testInserimentoNuovoOrdine(ordineServiceInstance);
			// testAggiornamentoOrdine(ordineServiceInstance);
			// testInserimentoNuovoArticolo(articoloServiceInstance, ordineServiceInstance);
			// testAggiornamentoArticolo(articoloServiceInstance);
			// testRimozioneArticolo(articoloServiceInstance);
			// testInserimentoNuovaCategoria(categoriaServiceInstance);
			// testAggiornamentoCategoria(categoriaServiceInstance);
			// testAggiungiCategoriaAdArticolo(articoloServiceInstance,
			// categoriaServiceInstance);

			// testAggiungiArticoloACategoria(categoriaServiceInstance,
			// articoloServiceInstance);
			// testRimuoviCategoriaDaArticolo(articoloServiceInstance,
			// categoriaServiceInstance);
			// testRimozioneArticoloConCategorieCollegate(articoloServiceInstance);
			// testTrovaOrdiniDaCategoria(ordineServiceInstance, categoriaServiceInstance);
			// testTrovaCategoriaDaOrdine(ordineServiceInstance, categoriaServiceInstance);
			// testSommaPrezzoSingoloArticoloDaCategoria(articoloServiceInstance,
			// categoriaServiceInstance);
			// testTrovaOrdinePiuRecenteDaCategoria(ordineServiceInstance,
			// categoriaServiceInstance);
			testCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno(categoriaServiceInstance, articoloServiceInstance,
					ordineServiceInstance);
			System.out.println(
					"****************************** fine batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");
			System.out.println(
					"In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovoOrdine inizio.............");

		Ordine ordineInstance = new Ordine("Luca Meloni", "Via Prenestina 40", LocalDate.parse("2023-04-02"));
		ordineServiceInstance.inserisciNuovo(ordineInstance);
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoOrdine fallito ");

		System.out.println(".......testInserimentoNuovoOrdine fine: PASSED.............");

	}

	private static void testAggiornamentoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testAggiornamentoOrdine inizio.............");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		Ordine daAggiornare = listaOrdini.get(0);

		daAggiornare.setNomeDestinatario("Luca Scarola");

		ordineServiceInstance.aggiorna(daAggiornare);

		List<Ordine> listaOrdiniFinale = ordineServiceInstance.listAll();

		System.out.println(listaOrdiniFinale.get(0));

		System.out.println(".......testAggiornamentoOrdine fine: PASSED.............");

	}

	private static void testInserimentoNuovoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovoArticolo inizio.............");

		Articolo articoloInstance = new Articolo("Elden Ring", "Ed678", 60, LocalDate.parse("2023-04-03"));
		articoloInstance.setOrdine(ordineServiceInstance.listAll().get(3));
		articoloServiceInstance.inserisciNuovo(articoloInstance);
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoArticolo fallito ");

		System.out.println(".......testInserimentoNuovoArticolo fine: PASSED.............");

	}

	private static void testAggiornamentoArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(".......testAggiornamentoArticolo inizio.............");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo daAggiornare = listaArticoli.get(0);

		daAggiornare.setDescrizione("Nintendo Switch");

		articoloServiceInstance.aggiorna(daAggiornare);

		List<Articolo> listaArticoliFinale = articoloServiceInstance.listAll();

		System.out.println(listaArticoliFinale.get(0));

		System.out.println(".......testAggiornamentoArticolo fine: PASSED.............");

	}

	private static void testRimozioneArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("........ testRimozioneArticolo inizio........");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo daEliminare = listaArticoli.get(1);

		if (daEliminare.getId() == null)
			throw new RuntimeException("testRimozioneArticolo fallito: non riesco a trovare nessun articolo! ");

		articoloServiceInstance.rimuovi(daEliminare.getId());

		System.out.println("...........testRimozioneArticolo fine: PASSED...........");

	}

	private static void testInserimentoNuovaCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovaCategoria inizio.............");

		Categoria categoriaInstance = new Categoria("PcPortatili", "HPLON");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovaCategoria fallito ");

		System.out.println(".......testInserimentoNuovaCategoria fine: PASSED.............");

	}

	private static void testAggiornamentoCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testAggiornamentoCategoria inizio.............");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		Categoria daAggiornare = listaCategorie.get(0);

		daAggiornare.setCodice("NVDK");

		categoriaServiceInstance.aggiorna(daAggiornare);

		List<Categoria> listaCategorieFinale = categoriaServiceInstance.listAll();

		System.out.println(listaCategorieFinale.get(0));

		System.out.println(".......testAggiornamentoCategoria fine: PASSED.............");

	}

	private static void testAggiungiCategoriaAdArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("........ testAggiungiCategoriaAdArticolo inizio........");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo daCollegare = listaArticoli.get(0);

		if (daCollegare.getId() == null)
			throw new RuntimeException(
					"testAggiungiCategoriaAdArticolo fallito: non riesco a trovare nessun articolo! ");

		List<Categoria> elencoCategorie = categoriaServiceInstance.listAll();
		Categoria daAggiungere = elencoCategorie.get(0);

		articoloServiceInstance.aggiungiCategoria(daCollegare, daAggiungere);

		System.out.println("...........testAggiungiCategoriaAdArticolo fine: PASSED...........");

	}

	private static void testAggiungiArticoloACategoria(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("........ testAggiungiArticoloACategoria  inizio........");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		Categoria daCollegare = listaCategorie.get(2);

		if (daCollegare.getId() == null)
			throw new RuntimeException(
					"testAggiungiArticoloACategoria fallito: non riesco a trovare nessuna categoria! ");

		List<Articolo> elencoArticoli = articoloServiceInstance.listAll();
		Articolo daAggiungere = elencoArticoli.get(2);

		categoriaServiceInstance.aggiungiArticolo(daCollegare, daAggiungere);

		System.out.println("...........testAggiungiArticoloACategoria fine: PASSED...........");

	}

	private static void testRimuoviCategoriaDaArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("........testRimuoviCategoriaDaArticolo  inizio........");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo daScollegare = listaArticoli.get(0);

		if (daScollegare.getId() == null)
			throw new RuntimeException(
					"testRimuoviCategoriaDaArticolo fallito: non riesco a trovare nessun articolo! ");

		List<Categoria> elencoCategorie = categoriaServiceInstance.listAll();
		Categoria daRimuovere = elencoCategorie.get(0);

		articoloServiceInstance.rimuoviCategoriaDaArticolo(daRimuovere.getId());
		;

		System.out.println("...........testRimuoviCategoriaDaArticolo fine: PASSED...........");

	}

	private static void testRimozioneArticoloConCategorieCollegate(ArticoloService articoloServiceInstance)
			throws Exception {
		System.out.println("........ testRimozioneArticoloConCategorieCollegate inizio........");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo daEliminare = listaArticoli.get(0);

		if (daEliminare.getId() == null)
			throw new RuntimeException(
					"testRimozioneArticoloConCategorieCollegate fallito: non riesco a trovare nessun articolo! ");

		articoloServiceInstance.rimuoviArticoloConCategorieCollegate(daEliminare);

		System.out.println("...........testRimozioneArticoloConCategorieCollegate fine: PASSED..............");

	}

	private static void testTrovaOrdiniDaCategoria(OrdineService ordineServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("..........testTrovaOrdiniDaCategoria inizio..............");

		Long idDaPrendere = categoriaServiceInstance.listAll().get(0).getId();

		List<Ordine> resultOrdini = ordineServiceInstance.TrovaOrdineDaCategoria(idDaPrendere);

		if (resultOrdini.isEmpty())
			throw new RuntimeException(" testTrovaOrdiniDaCategoria fallito: nessun ordine disponibile!");

		System.out.println(resultOrdini);

		System.out.println(".........testTrovaOrdiniDaCategoria fine: PASSED...................");
	}

	private static void testTrovaCategoriaDaOrdine(OrdineService ordineServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("..........testTrovaCategoriaDaOrdine inizio.........");

		Long idDaPrendere = ordineServiceInstance.listAll().get(0).getId();

		List<Categoria> resultCategorie = categoriaServiceInstance.TrovaCategoriaDaOrdine(idDaPrendere);

		if (resultCategorie.isEmpty())
			throw new RuntimeException(" testTrovaCategoriaDaOrdine fallito: nessuna categoria disponibile!");

		System.out.println(resultCategorie);

		System.out.println("..........testTrovaCategoriaDaOrdine fine: PASSED........");

	}

	private static void testSommaPrezzoSingoloArticoloDaCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".............testSommaPrezzoSingoloArticoloDaCategoria inizio.............");

		Long idDaPrendere = categoriaServiceInstance.listAll().get(2).getId();

		int result = articoloServiceInstance.sommaPrezzoArticoloDaCategoria(idDaPrendere);

		System.out.println(result);

		System.out.println("..........testSommaPrezzoSingoloArticoloDaCategoria fine: PASSED............");
	}

	private static void testTrovaOrdinePiuRecenteDaCategoria(OrdineService ordineServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testTrovaOrdinePiuRecenteDaCategoria inizio.........");

		Long idDaPrendere = categoriaServiceInstance.listAll().get(0).getId();

		Ordine result = ordineServiceInstance.TrovaOrdinePiuRecenteDaCategoria(idDaPrendere);

		if (result == null) {
			throw new RuntimeException("testTrovaOrdinePiuRecenteDaCategoria fallito non trovo ordini!");
		}

		System.out.println(result);

		System.out.println("...........testTrovaOrdinePiuRecenteDaCategoria fine: PASSED.........");

	}

	private static void testCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("..............testCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno inizio.......");

		List<String> elencoCodici = ordineServiceInstance.caricaCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno(4, 2023);

		if (elencoCodici.isEmpty()) {
			throw new RuntimeException(
					"testCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno fallito: non riesco a trovare Codici!");

		}

		System.out.println(elencoCodici);

		System.out.println(".............testCodiciDiCategorieDiArticoliDiOrdiniInMeseEAnno fine: PASSED............");
	}

}
