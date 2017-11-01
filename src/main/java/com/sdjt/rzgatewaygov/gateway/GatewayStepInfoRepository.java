package com.sdjt.rzgatewaygov.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatewayStepInfoRepository extends JpaRepository<GatewayStepInfo, String> {

//    @Query("select t from GatewayStepInfo t where t.ISSUCCESS = ?1")
//    List<GatewayStepInfo> findByIsSuccess(String issuccess);

    List<GatewayStepInfo> findByIsSuccessIsNull();

    List<GatewayStepInfo> findByIsSuccess(String isSuccess);
}

