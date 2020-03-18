package dev.vivienhuang.mavenwebdemo.service.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.vivienhuang.mavenwebdemo.linebot.member.MemberStatus;


@Service
public class LineMemberCacheService implements ILineMemberCacheService {

	@Override
	@Cacheable(cacheNames= {"LineMemberStatus"}, key="#lineId")
	public MemberStatus getMemberStatus(String lineId) {
		
		return MemberStatus.NORMAL;
	}
	
	@Override
	@CachePut(cacheNames= {"LineMemberStatus"}, key="#lineId")
	public MemberStatus getMemberStatus(String lineId, MemberStatus memberStatus) {
		
		return memberStatus;
	}

	@Override
	public void delete(String lineId) {
		// TODO Auto-generated method stub

	}

}
