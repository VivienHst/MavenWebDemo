package dev.vivienhuang.mavenwebdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;

public class GetURLAnimalContent {
	public String getDataFromUrl(String urlStr){
        String data = "";
        URL url = null;

        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("User-agent", " Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            InputStream inputStream = urlConn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                data += line;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }




    public Map<String, AnimalVO> getAnimalPicture(){
        Map<String, AnimalVO> animalPictureMap = new HashMap<String, AnimalVO>();

        String data = getDataFromUrl("https://animalcrossing.fandom.com/wiki/Villager_list_(New_Horizons)");

        if(data != null){
            String[] animalDataArr = data.split("<td style=\"background-color:#FFF;border-bottom:1px&#160;;border-top-left-radius");
//            System.out.println(data);
            for (int i =1; i< animalDataArr.length; i++){
                AnimalVO animalVO = new AnimalVO();
                String nameRaw = animalDataArr[i].split("<b><a href=\"/wiki/")[1];
                String name = nameRaw.substring(0, nameRaw.indexOf("\""));
                System.out.println("Name : " + name);

                String pictureRaw = animalDataArr[i].split("<img src=\"https:")[1];
                String picture = "https:" + pictureRaw.substring(0, pictureRaw.indexOf(".png") + 4);

                System.out.println("Picture : " + picture);
                //<a href="/wiki/Bird" title="Bird">Bird</a>

                String speciesRaw = pictureRaw.split("<td style=\"background-color:#FFF;border-bottom:")[2].split("title=\"")[1];
                String species = speciesRaw.substring(0, speciesRaw.indexOf("\""));

                System.out.println("Species : " + species);
                animalVO.setNameEN(name);
                animalVO.setPicture(picture);
                animalVO.setSpecies(species);


                animalPictureMap.put(name, animalVO);
            }
        }
        return animalPictureMap;
    }



    /**
     * @param urlStr
     */
    public List<AnimalVO> GetUrlContent(String urlStr) {

        List<AnimalVO> animalVOS = new ArrayList<AnimalVO>();
        String data = getDataFromUrl(urlStr);

        Map<String, AnimalVO> animalPictureMap = getAnimalPicture();

        if (data != null) {
//                if(data.contains("<table><tbody><tr><td colspan=\"4\" nowrap=\"nowrap\">")) {
//                    String[] animalContent = data.split("<table><tbody><tr><td colspan=\"4\" nowrap=\"nowrap\">");
            String imageTag = "<img class=\"aligncenter";
            if(data.contains(imageTag)) {
                String[] animalContent = data.split(imageTag);

                //<img class="aligncenter
                for (int i = 1; i < animalContent.length; i++) {

                    String animalImage = animalContent[i].substring(animalContent[i].indexOf("src=\"") + 5, animalContent[i].indexOf(".png")+4);

                    String[] animalForm = animalContent[i].split("<table><tbody><tr><td colspan=\"4\" nowrap=\"nowrap\">");

                    System.out.println();
                    String nameRaw = animalForm[1].substring(0, animalForm[1].indexOf("</td>"));
                    String genderRaw = nameRaw.substring( nameRaw.length()-1, nameRaw.length());

                    String name = nameRaw.substring(0, nameRaw.length()-1);
                    System.out.println();
                    String gender= "";

                    if(genderRaw.equals("♀")) {
                        gender = "female";
                    } else if(genderRaw.equals("♂")) {
                        gender = "male";
                    } else {
                        gender = "other";
                    }

                    System.out.println("名字 : " + name);

                    System.out.println("性別 : " + gender);

                    String[] animalData = animalForm[1].split("<tr>");
                    String birthdayRaw = animalData[1].split("<td nowrap=\"nowrap\">")[2];
                    String personalityRaw = animalData[1].split("<td nowrap=\"nowrap\">")[4];
                    String initialPhraseRaw = animalData[2].split("<td colspan=\"2\" nowrap=\"nowrap\">")[2];
                    String birthday = birthdayRaw.substring(0, birthdayRaw.indexOf("</td>"));
                    String personality = personalityRaw.substring(0, personalityRaw.indexOf("</td>"));
                    String initialPhrase = initialPhraseRaw.substring(0, initialPhraseRaw.indexOf("</td>"));

                    String englishNameRaw = animalData[6].split("<td colspan=\"2\" nowrap=\"nowrap\">")[2];

                    String englishName = "";

                    if(englishNameRaw.contains("（英）")){
                        englishName = englishNameRaw.substring(0, englishNameRaw.indexOf("（英）"));
                    }


                    System.out.println("生日 : " + birthday);
                    System.out.println("個性 : " + personality);
                    System.out.println("初始口頭禪 : " + initialPhrase);
                    System.out.println("英文名字 : " + englishName);

                    System.out.println("---------------------------------------");

                    AnimalVO animalVO = new AnimalVO();

                    if(animalPictureMap.get(englishName)!=null){
                        animalVO = animalPictureMap.get(englishName);
                    }
                    System.out.println("動物圖片 : " + animalImage);

                    animalVO.setName(name);
//                    animalVO.setBirthday(birthday);
                    animalVO.setGender(gender);
                    animalVO.setPersonality(personality);
                    animalVO.setInitialPhrase(initialPhrase);
                    animalVO.setNameEN(englishName);

                    if(animalVO.getName().contains("♂") || animalVO.getName().contains("♀")){
                        name = animalVO.getName().substring(0, animalVO.getName().length()-1);
                        if(animalVO.getName().contains("♂")){
                            animalVO.setName(name);
                            animalVO.setGender("male");
                        } else if(animalVO.getName().contains("♀")){
                            animalVO.setName(name);
                            animalVO.setGender("female");
                        }

                    }
                    animalVO.setBirthdayMonth(Integer.parseInt(birthday.substring(0, birthday.indexOf("月"))));
                    animalVO.setBirthdayMonth(Integer.parseInt(birthday.substring(birthday.indexOf("月") + 1,
                        birthday.indexOf("日"))));
                    animalVOS.add(animalVO);
                }
            }

        }


        return animalVOS;
    }

}
