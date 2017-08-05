package cn.haohao.dbbook.domain.interactor

import cn.haohao.dbbook.data.datasource.DmzjDataSource
import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import cn.haohao.dbbook.domain.entity.RequestAnimeBannerParams
import cn.haohao.dbbook.domain.executor.PostExecutionThread
import cn.haohao.dbbook.domain.executor.ThreadExecutor
import retrofit2.Response
import rx.Observable

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:36 PM
 * @Version
 */
class GetAnimeInteractor(val dmzjDataSource: DmzjDataSource,
                         threadExecutor: ThreadExecutor,
                         postExecutionThread: PostExecutionThread)
    : Interactor<Response<AnimeBannerResponse>, RequestAnimeBannerParams>(threadExecutor, postExecutionThread) {

    override fun buildObservable(params: RequestAnimeBannerParams): Observable<Response<AnimeBannerResponse>> =
            dmzjDataSource.getAnimeBanner()
}