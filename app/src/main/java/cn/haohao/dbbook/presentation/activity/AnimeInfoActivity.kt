package cn.haohao.dbbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.MenuItem
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.haohao.dbbook.R
import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse
import cn.haohao.dbbook.di.ApplicationComponent
import cn.haohao.dbbook.di.subcomponent.anime.AnimeInfoActivityModule
import cn.haohao.dbbook.domain.entity.RequestAnimeBannerParams
import cn.haohao.dbbook.presentation.presenter.AnimeInfoPresenter
import cn.haohao.dbbook.presentation.util.showToast
import cn.haohao.dbbook.presentation.view.AnimeInfoView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
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

    override fun showDetailData(body: List<AnimeBannerResponse.DataBean>?) {
        val urls = ArrayList<String>()
        body!!.mapTo(urls) { it -> it.pic_url!! }
        bannerView.setData(R.layout.item_banner_view, urls, null)
        bannerView.setAdapter(object : BGABanner.Adapter<CardView, String> {
            override fun fillBannerItem(banner: BGABanner, itemView: CardView, model: String, position: Int) {
                val glideUrl = GlideUrl(model, Headers {
                    val header = HashMap<String, String>()
                    header.put("Referer", "http://v2.api.dmzj.com")
                    header
                })
                Glide.with(BaseActivity.instance)
                        .load(glideUrl)
                        .placeholder(R.drawable.app_logo)
                        .into(itemView.findViewById(R.id.iv_content) as ImageView)
            }
        })
    }
}
