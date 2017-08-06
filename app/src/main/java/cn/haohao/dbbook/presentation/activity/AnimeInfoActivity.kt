package cn.haohao.dbbook.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import cn.haohao.dbbook.R
import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import cn.haohao.dbbook.di.ApplicationComponent
import cn.haohao.dbbook.di.subcomponent.anime.AnimeInfoActivityModule
import cn.haohao.dbbook.domain.entity.RequestAnimeBannerParams
import cn.haohao.dbbook.presentation.presenter.AnimeInfoPresenter
import cn.haohao.dbbook.presentation.util.showToast
import cn.haohao.dbbook.presentation.view.AnimeInfoView
import com.bumptech.glide.Glide
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
        showToast(error)
    }

    override fun showProgressView() {
    }

    override fun hideProgressView() {
    }

    var urls = ArrayList<String>()
    override fun showDetailData(body: List<AnimeBannerResponse.DataBean>?) {
        for (item in body!!) {
            urls.add(item.pic_url!!)
        }
        bannerView.setData(R.layout.item_banner_view, urls, null)
        bannerView.setAdapter { banner, itemView, model, position ->
            Glide.with(BaseActivity.instance)
                    .load(model)
                    .into(itemView.findViewById(R.id.iv_content) as ImageView)
        }
    }
}
