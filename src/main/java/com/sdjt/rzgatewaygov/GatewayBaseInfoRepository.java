package com.sdjt.rzgatewaygov;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GatewayBaseInfoRepository extends JpaRepository<GatewayBaseInfo, String> {

//    @Query("select t from GatewayBaseInfo t where t.ISSUCCESS = ?1")
    List<GatewayBaseInfo> findByIsSuccess(String isSuccess);

    List<GatewayBaseInfo> findByIsSuccessIsNull();

}
