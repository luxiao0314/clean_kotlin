package cn.haohao.dbbook.di.subcomponent.anime

import cn.haohao.dbbook.di.scope.ActivityScope
import cn.haohao.dbbook.presentation.activity.AnimeInfoActivity
import dagger.Subcomponent

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:31 PM
 * @Version
 */
@ActivityScope
@Subcomponent(modules = arrayOf(AnimeInfoActivityModule::class))
interface AnimeInfoActivityComponent {
    fun injectTo(activity: AnimeInfoActivity)
}