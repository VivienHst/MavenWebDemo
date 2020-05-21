package dev.vivienhuang.mavenwebdemo.service.animal;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;

public interface IAnimalService {
	public void createAnimal(AnimalVO animalVO);
	public AnimalVO getAnimal(int aid);
	public List<AnimalVO> getAnimals();
	public void updateAnimal(AnimalVO animalVO);
	public void deleteAnimal(int aid);
}
