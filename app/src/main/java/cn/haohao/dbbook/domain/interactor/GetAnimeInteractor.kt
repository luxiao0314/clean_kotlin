package cn.haohao.dbbook.domain.interactor

import cn.haohao.dbbook.data.datasource.BookDataSource
import cn.haohao.dbbook.data.entity.http.BookListResponse
import cn.haohao.dbbook.domain.entity.RequestListParams
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
class GetAnimeInteractor(val bookDataSource: BookDataSource,
                         threadExecutor: ThreadExecutor,
                         postExecutionThread: PostExecutionThread)
    : Interactor<Response<BookListResponse>, RequestListParams>(threadExecutor, postExecutionThread) {

    override fun buildObservable(params: RequestListParams): Observable<Response<BookListResponse>> =
            bookDataSource.requestBookList(
                    params.queryKey,
                    params.tag,
                    params.start,
                    params.count,
                    params.fields
            )
}