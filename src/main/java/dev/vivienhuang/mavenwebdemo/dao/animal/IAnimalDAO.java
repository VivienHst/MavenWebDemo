package dev.vivienhuang.mavenwebdemo.dao.animal;

import java.util.List;
import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;

public interface IAnimalDAO {
	public void createAnimal(AnimalVO animalVO);
	public AnimalVO getAnimal(int aid);
	public List<AnimalVO> getAnimals();
	public void updateAnimal(AnimalVO animalVO);
	public void deleteAnimal(int aid);
}
