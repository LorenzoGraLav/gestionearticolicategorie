package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;


import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class ArticoloServiceImpl implements ArticoloService {
	
	private ArticoloDAO articoloDAO;

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO = articoloDAO;;
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return articoloDAO.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return articoloDAO.get(id);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					articoloDAO.update(articoloInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
		
	}

	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					articoloDAO.insert(articoloInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public void rimuovi(Long idArticolo) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					articoloDAO.delete(articoloDAO.get(idArticolo));

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// 'attacco' alla sessione di hibernate i due oggetti
					// così jpa capisce che se risulta presente quell articolo non deve essere inserito
					articoloInstance = entityManager.merge(articoloInstance);
					// attenzione che categoriaInstance deve essere già presente (lo verifica dall'id)
					// se così non è viene lanciata un'eccezione
					categoriaInstance = entityManager.merge(categoriaInstance);

					articoloInstance.getCategorie().add(categoriaInstance);
					// l'update non viene richiamato a mano in quanto
					// risulta automatico, infatti il contesto di persistenza
					// rileva che articoloInstance ora è dirty vale a dire che una sua
					// proprieta ha subito una modifica (vale anche per i Set ovviamente)
					// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
		
	}

	@Override
	public void rimuoviCategoriaDaArticolo(Long idArticolo) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDAO.unlinkCategoriaByArticolo(idArticolo);;

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuoviArticoloConCategorieCollegate(Articolo articoloIstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					articoloDAO.removeArticoloAndUnlinkByCategoria(articoloIstance);;
					articoloDAO.delete(articoloIstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
		
	}

	@Override
	public int sommaPrezzoArticoloDaCategoria(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDAO.sumPriceArticoloByCategoria(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}
	
	
}
