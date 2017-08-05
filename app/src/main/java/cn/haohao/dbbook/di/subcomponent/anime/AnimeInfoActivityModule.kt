package cn.haohao.dbbook.di.subcomponent.anime

import cn.haohao.dbbook.di.scope.ActivityScope
import cn.haohao.dbbook.domain.interactor.GetAnimeInteractor
import cn.haohao.dbbook.presentation.activity.AnimeInfoActivity
import cn.haohao.dbbook.presentation.presenter.AnimeInfoPresenter
import cn.haohao.dbbook.presentation.view.AnimeInfoView
import dagger.Module
import dagger.Provides

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:32 PM
 * @Version
 */
@Module
class AnimeInfoActivityModule(val activity: AnimeInfoActivity) {

    @Provides @ActivityScope
    fun provideAnimeInfoView(): AnimeInfoView = activity

    @Provides @ActivityScope
    fun provideAnimeInfoPresenter(view: AnimeInfoView,
                                   getAnimeInteractor: GetAnimeInteractor): AnimeInfoPresenter
            = AnimeInfoPresenter(view, getAnimeInteractor)
}