package com.skt.doss.portal.port_infra.persistent.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.skt.doss.portal.port_infra.persistent.redis.entity.SampleRedis;

public interface SampleRedisRepository extends CrudRepository<SampleRedis,String> {
}
