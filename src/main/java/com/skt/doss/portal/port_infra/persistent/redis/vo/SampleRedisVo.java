package com.skt.doss.portal.port_infra.persistent.redis.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SampleRedisVo {
    private String langCd;
    private String groupId;
    private String codeId;
    private String codeName;

    @Builder
    public SampleRedisVo(String langCd, String groupId, String codeId, String codeName) {
        this.groupId = groupId;
        this.codeId = codeId;
        this.codeName = codeName;
        this.langCd = langCd;
    }
}
