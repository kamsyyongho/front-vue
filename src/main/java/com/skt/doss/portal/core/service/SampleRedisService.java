package com.skt.doss.portal.core.service;

import com.skt.doss.portal.port_infra.persistent.redis.entity.SampleRedis;
import com.skt.doss.portal.port_infra.persistent.redis.repository.SampleRedisRepository;
import com.skt.doss.portal.port_infra.persistent.redis.vo.SampleRedisVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SampleRedisService {

    @Autowired
    private SampleRedisRepository commCodeRedisRepository;
//    private final CommCodeService commCodeService;
    private final ModelMapper modelmapper;

    public List<SampleRedisVo> getCommCodeRedis(String groupId, String langCd) {
        String redisGroupId = groupId + "_" + langCd;
        Optional<SampleRedis> optionalCommCodeRedis = commCodeRedisRepository.findById(redisGroupId);
        if (optionalCommCodeRedis.isPresent()) {
//            return optionalCommCodeRedis.get().getCommCodeVoList();
        }
        return null;
    }

    public boolean isExistCommCodeRedis(String groupId, String langCd){
        String redisGroupId = groupId + "_" + langCd;
        Optional<SampleRedis> optionalCommCodeRedis = commCodeRedisRepository.findById(redisGroupId);
        if(optionalCommCodeRedis.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public void addCommCodeRedis(String groupId, String langCd, List<SampleRedisVo> commCodeVoList) {
        String redisGroupId = groupId + "_" + langCd;
        SampleRedis commCodeRedis = SampleRedis.builder().groupId(redisGroupId).commCodeRedisList(commCodeVoList).build();
        commCodeRedisRepository.save(commCodeRedis);
    }

    public void updateCommCodeRedis(String groupId, String langCd, List<SampleRedisVo> commCodeVoList) {
        String redisGroupId = groupId + "_" + langCd;
        Optional<SampleRedis> optionalCommCodeRedis = commCodeRedisRepository.findById(redisGroupId);
        SampleRedis targetCommCodeRedis = optionalCommCodeRedis.get();
        targetCommCodeRedis.setCommCodeVoList(commCodeVoList);
        if (targetCommCodeRedis != null) {
            commCodeRedisRepository.save(targetCommCodeRedis);
        }
    }

    public void deleteCommCodeRedis(String groupId, String langCd) {
        String redisGroupId = groupId + "_" + langCd;
        Optional<SampleRedis> optionalCommCodeRedis = commCodeRedisRepository.findById(redisGroupId);
        SampleRedis targetCommCodeRedis = optionalCommCodeRedis.get();
        if (targetCommCodeRedis != null) {
            commCodeRedisRepository.delete(targetCommCodeRedis);
        }
    }

    public void InsertCommCodeToRedis(SampleRedisVo commCodeVo){
        //redis cache 업데이트 (이미 있으면 업데이트, 없으면 추가)
        List<SampleRedisVo> commCodeRedisVoList = null;
        if(isExistCommCodeRedis(commCodeVo.getGroupId(),commCodeVo.getLangCd())){
            commCodeRedisVoList = getCommCodeRedis(commCodeVo.getGroupId(),commCodeVo.getLangCd());
            commCodeRedisVoList.add(SampleRedisVo.builder().codeName(commCodeVo.getCodeName()).codeId(commCodeVo.getCodeId()).groupId(commCodeVo.getGroupId()).langCd(commCodeVo.getLangCd()).build());
            updateCommCodeRedis(commCodeVo.getGroupId(),commCodeVo.getLangCd(),commCodeRedisVoList);
        }else{
            commCodeRedisVoList.add(SampleRedisVo.builder().codeName(commCodeVo.getCodeName()).codeId(commCodeVo.getCodeId()).groupId(commCodeVo.getGroupId()).langCd(commCodeVo.getLangCd()).build());
            addCommCodeRedis(commCodeVo.getGroupId(),commCodeVo.getLangCd(),commCodeRedisVoList);
        }
    }

}
