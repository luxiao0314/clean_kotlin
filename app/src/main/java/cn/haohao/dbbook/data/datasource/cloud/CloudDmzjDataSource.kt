package cn.haohao.dbbook.data.datasource.cloud

import cn.haohao.dbbook.data.DmzjService
import cn.haohao.dbbook.data.datasource.DmzjDataSource
import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import retrofit2.Response
import rx.Observable

/**
 * Created by HaohaoChang on 2017/6/9.
 */
class CloudDmzjDataSource(val dmzjService: DmzjService) : DmzjDataSource {

    override fun getAnimeBanner(): Observable<Response<AnimeBannerResponse>> {
        return dmzjService.getAnimeBanner()
    }

}