package cn.haohao.dbbook.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import cn.haohao.dbbook.R
import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import cn.haohao.dbbook.di.ApplicationComponent
import cn.haohao.dbbook.di.subcomponent.anime.AnimeInfoActivityModule
import cn.haohao.dbbook.domain.entity.RequestAnimeBannerParams
import cn.haohao.dbbook.presentation.presenter.AnimeInfoPresenter
import cn.haohao.dbbook.presentation.view.AnimeInfoView
import kotlinx.android.synthetic.main.activity_anime_info.*
import javax.inject.Inject

class AnimeInfoActivity : BaseActivity(), AnimeInfoView {

    @Inject
    lateinit var animeInfoPresenter: AnimeInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_info)
        injectToThis()
        initTitle()
        initData()
    }

    private fun initData() {
        animeInfoPresenter.execute(RequestAnimeBannerParams())
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AnimeInfoActivityModule(this))
                .injectTo(this)
    }

    private fun initTitle() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "动漫资讯"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(error: String) {
    }

    override fun showProgressView() {
    }

    override fun hideProgressView() {
    }

    override fun showDetailData(body: List<AnimeBannerResponse.DataBean>?) {
    }
}
