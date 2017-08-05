package cn.haohao.dbbook.presentation.view

import cn.haohao.dbbook.data.entity.http.AnimeBannerResponse

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:34 PM
 * @Version
 */
interface AnimeInfoView :PresentationView{
    fun showDetailData(body: List<AnimeBannerResponse.DataBean>?)
}