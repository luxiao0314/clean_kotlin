package cn.haohao.dbbook.presentation.presenter

import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import cn.haohao.dbbook.domain.entity.RequestAnimeBannerParams
import cn.haohao.dbbook.domain.interactor.GetAnimeInteractor
import cn.haohao.dbbook.presentation.view.AnimeInfoView
import org.jetbrains.anko.getStackTraceString
import retrofit2.Response
import rx.Subscriber

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:35 PM
 * @Version
 */
class AnimeInfoPresenter(override var view: AnimeInfoView, var animeInteractor: GetAnimeInteractor) : Presenter<AnimeInfoView, RequestAnimeBannerParams> {
    override fun execute(params: RequestAnimeBannerParams) {
        view.showProgressView()
        animeInteractor.execute(object : Subscriber<Response<AnimeBannerResponse>>() {
            override fun onNext(t: Response<AnimeBannerResponse>?) {
                t?.let {
                    //请求无异常,请求code == 200
                    if (t.isSuccessful) {
                        //请求无服务器错误  服务器code == 0
                        if (t.body().code == 0) {
                            view.showDetailData(t.body().getData())
                        } else {
                            view.onError(t.body().msg)
                        }
                    }
                }
                view.hideProgressView()
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
                e?.let {
                    view.onError(e.getStackTraceString())
                }
                view.hideProgressView()
            }

        }, params)

    }

    override fun cancel() {
        animeInteractor.cancel()
    }
}