package cn.haohao.dbbook.data

import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import retrofit2.Response
import retrofit2.http.GET
import rx.Observable

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 06/08/2017 12:08 AM
 * @Version
 */
interface DmzjService {
    companion object {
        val BASE_URL = "http://v2.api.dmzj.com/"
    }

    @GET("article/recommend/header.json")
    fun getAnimeBanner(): Observable<Response<AnimeBannerResponse>>
}