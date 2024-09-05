package com.mangotestworkchat.app.network

import com.granch.network.models.request.UpgradeIpBodyData
import com.granch.network.models.response.ResultResponsePhoneServer
import com.granch.network.models.response.ResultResponseUpgradeIp
import retrofit2.Response
import retrofit2.http.*

interface ApiServer {

    @GET("/api/IpManagement/")
    suspend fun getPhoneList(
    ): Response<List<ResultResponsePhoneServer>>

    @POST("api/IpManagement")
    suspend fun upgradeIp(
    @Body upgradeIpBodyData: UpgradeIpBodyData
    ): Response<ResultResponseUpgradeIp>

    @POST("api/PhoneManagement/attach/phone={phoneId}/employee={employeeId}")
    suspend fun givePhone(
        @Path ("phoneId") phoneId: Int?,
        @Path ("employeeId") employeeId: Int?
    ): Response<List<ResultResponsePhoneServer>>



//
//    @DELETE("/api/v1/Visit/{id}")
//    suspend fun closeVisit (
//        @Path("id") id: Int
//    ): Response<Void>

}