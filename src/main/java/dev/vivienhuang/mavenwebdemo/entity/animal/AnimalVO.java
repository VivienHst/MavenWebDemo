package dev.vivienhuang.mavenwebdemo.entity.animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Animal")
public class AnimalVO {
	
/**
 * 
 * 
 * CREATE TABLE [dbo].[Animal](
	[Aid] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](16) NOT NULL,
	[Name_EN] [nvarchar](16) NULL,
	[Gender] [nvarchar](8) NOT NULL,
	[Personality] [nvarchar](16) NOT NULL,
	[Species] [nvarchar](16) NULL,
	[InitialPhrase] [nvarchar](32) NOT NULL,
	[Birthday_Month] [int] NULL,
	[Birthday_Day] [int] NULL,
	[Picture] [nvarchar](300) NULL
) ON [PRIMARY]
 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Aid")
	private Integer aid;
	
	@Column(name="Name")
	@NotEmpty
	private String name;
	
	@Column(name="Name_EN")
	private String nameEN;
	
	@Column(name="Gender")
	@NotEmpty
	private String gender;
	
	@Column(name="Personality")
	@NotEmpty
	private String personality;
	
	@Column(name="Species")
	@NotEmpty
	private String species;
	
	@Column(name="InitialPhrase")
	@NotEmpty
	private String initialPhrase;
	
	@Column(name="Birthday_Month")
	private Integer birthdayMonth;
	
	@Column(name="Birthday_Day")
	private Integer birthdayDay;
	
	@Column(name="Picture")
	private String picture;
	
	public AnimalVO() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getInitialPhrase() {
		return initialPhrase;
	}

	public void setInitialPhrase(String initialPhrase) {
		this.initialPhrase = initialPhrase;
	}

	public Integer getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(Integer birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public Integer getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(Integer birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "AnimalVO [aid=" + aid + ", name=" + name + ", nameEN=" + nameEN + ", gender=" + gender
				+ ", personality=" + personality + ", species=" + species + ", initialPhrase=" + initialPhrase
				+ ", birthdayMonth=" + birthdayMonth + ", birthdayDay=" + birthdayDay + ", picture=" + picture + "]";
	}
}
