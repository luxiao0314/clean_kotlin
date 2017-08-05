package cn.haohao.dbbook.di


import cn.haohao.dbbook.data.datasource.BookDataSource
import cn.haohao.dbbook.data.datasource.DmzjDataSource
import cn.haohao.dbbook.di.qualifier.CloudDataQualifier
import cn.haohao.dbbook.domain.executor.PostExecutionThread
import cn.haohao.dbbook.domain.executor.ThreadExecutor
import cn.haohao.dbbook.domain.interactor.GetAnimeInteractor
import cn.haohao.dbbook.domain.interactor.GetBookDetailInteractor
import cn.haohao.dbbook.domain.interactor.GetBookListInteractor
import cn.haohao.dbbook.domain.interactor.GetRecommendedBooksInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by HaohaoChang on 2017/6/10.
 */
@Module
class DomainModule {

    @Provides
    fun provideBookListInteractor(@CloudDataQualifier bookDataSource: BookDataSource,
                                  threadExecutor: ThreadExecutor,
                                  postExecutionThread: PostExecutionThread) =
            GetBookListInteractor(bookDataSource, threadExecutor, postExecutionThread)

    @Provides
    fun provideBookDetailInteractor(@CloudDataQualifier bookDataSource: BookDataSource,
                                    threadExecutor: ThreadExecutor,
                                    postExecutionThread: PostExecutionThread) =
            GetBookDetailInteractor(bookDataSource, threadExecutor, postExecutionThread)

    @Provides
    fun provideRecommendedBooksInteractor(@CloudDataQualifier bookDataSource: BookDataSource,
                                          threadExecutor: ThreadExecutor,
                                          postExecutionThread: PostExecutionThread) =
            GetRecommendedBooksInteractor(bookDataSource, threadExecutor, postExecutionThread)

    //通过dagger提供的对象,都可以在dagger中获取,见dataModule
    @Provides
    fun provideAnimeInfoInteractor(@CloudDataQualifier dmzjDataSource: DmzjDataSource,
                                   threadExecutor: ThreadExecutor,
                                   postExecutionThread: PostExecutionThread) =
            GetAnimeInteractor(dmzjDataSource, threadExecutor, postExecutionThread)

}