package dev.vivienhuang.mavenwebdemo.service.cache;

import dev.vivienhuang.mavenwebdemo.linebot.member.MemberStatus;

public interface ILineMemberCacheService {
	public MemberStatus getMemberStatus(String lineId);
    public void delete(String lineId);
    public MemberStatus getMemberStatus(String lineId, MemberStatus memberStatus);
}
