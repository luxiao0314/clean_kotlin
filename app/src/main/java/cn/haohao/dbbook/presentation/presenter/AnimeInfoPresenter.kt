package cn.haohao.dbbook.presentation.presenter

import cn.haohao.dbbook.data.entity.http.BookListResponse
import cn.haohao.dbbook.domain.entity.RequestListParams
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
class AnimeInfoPresenter(override var view: AnimeInfoView, var animeInteractor: GetAnimeInteractor):Presenter<AnimeInfoView, RequestListParams> {
    override fun execute(params: RequestListParams) {
        view.showProgressView()
        animeInteractor.execute(object : Subscriber<Response<BookListResponse>>(){
            override fun onNext(t: Response<BookListResponse>?) {
                t?.let {
                    if (t.isSuccessful) {
                        view.showDetailData(t.body())
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