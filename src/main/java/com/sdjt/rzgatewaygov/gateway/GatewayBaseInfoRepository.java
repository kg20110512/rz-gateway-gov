package com.sdjt.rzgatewaygov.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GatewayBaseInfoRepository extends JpaRepository<GatewayBaseInfo, String> {

//    @Query("select t from GatewayBaseInfo t where t.ISSUCCESS = ?1")
//    List<GatewayBaseInfo> findByIsSuccess(String isSuccess);

    @Transactional(timeout = 3)
    List<GatewayBaseInfo> findByIsSuccess(String isSuccess);

    @Override
    @Transactional(timeout = 3)
    GatewayBaseInfo save(GatewayBaseInfo baseInfo);

    List<GatewayBaseInfo> findByIsSuccessNotOrIsSuccessIsNull(String isSuccess);

}
