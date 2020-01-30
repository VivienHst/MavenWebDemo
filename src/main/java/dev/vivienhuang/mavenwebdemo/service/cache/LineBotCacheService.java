package dev.vivienhuang.mavenwebdemo.service.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames= {"testQueryReult", "demo"})
public class LineBotCacheService implements ILineBotCacheService {

	@Override
	@Cacheable
	public String query(String keyword) {
		// TODO Auto-generated method stub
		
		System.out.println("start query : " + keyword);
		
		return doQuery(keyword);
	}
	@Override
	@CacheEvict
    public void delete(String keyword){
       System.out.println("delete keyword: " + keyword);
    }
	
	public String doQuery(String keyword) {
		String result = "";
		try {
			Thread.sleep(3000);

			result = "result of " + keyword;
			System.out.println("query result : " + result);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
