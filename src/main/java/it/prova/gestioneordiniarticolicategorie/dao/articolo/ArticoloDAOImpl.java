package it.prova.gestioneordiniarticolicategorie.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();

	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);

	}

	@Override
	public void update(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);

	}

	@Override
	public void insert(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);

	}

	@Override
	public void delete(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public void unlinkCategoriaByArticolo(Long idArticoloinput) throws Exception {

		entityManager.createNativeQuery("delete from articolo_categoria g where g.articolo_id = ?1")
				.setParameter(1, idArticoloinput).executeUpdate();
	}

	@Override
	public void removeArticoloAndUnlinkByCategoria(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null || articoloInstance.getId() == null || articoloInstance.getId() < 1)
			throw new Exception("errore! input invalido...");
		entityManager.createNativeQuery("delete from articolo_categoria where articolo_id=?1").setParameter(1,
				articoloInstance.getId()).executeUpdate();

		
	}

	@Override
	public int sumPriceArticoloByCategoria(Long id) throws Exception {
		return entityManager
				.createQuery("select sum(a.prezzoSingolo) from Articolo a join a.categorie c where c.id = ?1")
				.setParameter(1, id).getFirstResult();
	}

}
