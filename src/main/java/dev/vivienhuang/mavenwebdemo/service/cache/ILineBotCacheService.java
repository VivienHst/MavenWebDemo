package dev.vivienhuang.mavenwebdemo.service.cache;

public interface ILineBotCacheService {
	public String query(String keyword);
    public void delete(String keyword);
}
