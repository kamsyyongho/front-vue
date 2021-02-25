package com.skt.doss.portal.port_infra.persistent.redis.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.skt.doss.portal.port_infra.persistent.redis.vo.SampleRedisVo;

import lombok.*;

@RedisHash(value="sample")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleRedis {

    private static final long serialVersionUID = 1370692830319429806L;

    @Id
    private String groupId;
    private List<SampleRedisVo> commCodeVoList;
    private Date regDate;
    private Date updDate;

    @Builder
    SampleRedis(String groupId, List<SampleRedisVo> commCodeRedisList){
        this.groupId = groupId;
        this.commCodeVoList = commCodeRedisList;
        this.regDate = new Date();
        this.updDate = new Date();
    }
}
