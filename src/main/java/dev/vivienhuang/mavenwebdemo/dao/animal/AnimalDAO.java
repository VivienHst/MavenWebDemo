package dev.vivienhuang.mavenwebdemo.dao.animal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;

@Repository
public class AnimalDAO implements IAnimalDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createAnimal(AnimalVO animalVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(animalVO);	
	}

	@Override
	public AnimalVO getAnimal(int aid) {
		Session session = sessionFactory.getCurrentSession();
		AnimalVO animalVO = session.get(AnimalVO.class, aid);
		return animalVO;
	}

	@Override
	public List<AnimalVO> getAnimals() {
		Session session = sessionFactory.getCurrentSession();
		Query<AnimalVO> query = session.createQuery("from AnimalVO order by Aid desc",
				AnimalVO.class);
		List<AnimalVO> animalVOs = query.getResultList();		
		return animalVOs;
	}

	@Override
	public void updateAnimal(AnimalVO animalVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(animalVO);
	}

	@Override
	public void deleteAnimal(int aid) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(AnimalVO.class, aid));
	}

}
