package dev.vivienhuang.mavenwebdemo.service.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.animal.IAnimalDAO;
import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;

@Service
public class AnimalService implements IAnimalService {
	
	@Autowired
	IAnimalDAO animalDAO;

	@Override
	@Transactional
	public void createAnimal(AnimalVO animalVO) {
		animalDAO.createAnimal(animalVO);
	}

	@Override
	@Transactional
	public AnimalVO getAnimal(int aid) {
		
		return animalDAO.getAnimal(aid);
	}

	@Override
	@Transactional
	public List<AnimalVO> getAnimals() {
		return animalDAO.getAnimals();
	}

	@Override
	@Transactional
	public void updateAnimal(AnimalVO animalVO) {
		animalDAO.updateAnimal(animalVO);
	}
	
	@Override
	@Transactional
	public void deleteAnimal(int aid) {
		animalDAO.deleteAnimal(aid);
	}

}
