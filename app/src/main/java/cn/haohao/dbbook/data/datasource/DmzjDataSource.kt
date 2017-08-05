package cn.haohao.dbbook.data.datasource

import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import retrofit2.Response
import rx.Observable

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 06/08/2017 12:10 AM
 * @Version
 */

interface DmzjDataSource{
    fun getAnimeBanner(): Observable<Response<AnimeBannerResponse>>
}