package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Porblema valore in input");
		}
		entityManager.remove(input);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> allByCategoria(Long id) throws Exception {
		if (id == null)
			throw new Exception("Problema valore in input");

		return entityManager
				.createQuery("select o from Ordine o join o.articoli a join a.categorie c where c.id = :id ",
						Ordine.class)
				.setParameter("id", id).getResultList();
	}

	@Override
	public Ordine getOrdinePiuRecenteByCategoria(Long id) throws Exception {
		String queryString = "select o from Ordine o join o.articoli a join a.categorie c where c.id = :idCategoria and o.dataScadenza = (select max(o2.dataScadenza) from Ordine o2 join o2.articoli a2 join a2.categorie c2 where c2.id = :idCategoria)";
		TypedQuery<Ordine> query = entityManager.createQuery(queryString, Ordine.class);
		query.setParameter("idCategoria", id);
		return query.getSingleResult();
	}

	@Override
	public List<String> getCodiciDiCategorieMeseEAnno(int mese, int anno) throws Exception {
		String queryString = "select distinct c.codice from Ordine o join o.articoli a join a.categorie c where year(o.dataScadenza) = ?1 and month(o.dataScadenza) = ?2 ";
		TypedQuery<String> query = entityManager.createQuery(queryString, String.class);
		query.setParameter(1, anno);
		query.setParameter(2, mese);
		return query.getResultList();
	}

}
