package cn.haohao.dbbook.data.entity.http

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 05/08/2017 11:50 PM
 * @Version
 */
class AnimeBannerResponse :BaseResponse(){
    /**
     * code : 0
     * msg : 成功
     * data : [{"id":1250,"title":"P站美图推荐\u2014\u2014哭泣特辑（二）","pic_url":"http://images.dmzj.com/news/recommend/15017449536758.jpg","object_id":11140,"object_url":"http://v2.api.dmzj.com/article/show/v2/11140.html"},{"id":1247,"title":"P站美图推荐\u2014\u2014王冠特辑","pic_url":"http://images.dmzj.com/news/recommend/15016629786149.jpg","object_id":11118,"object_url":"http://v2.api.dmzj.com/article/show/v2/11118.html"},{"id":1244,"title":"P站美图推荐\u2014\u2014萤火虫特辑","pic_url":"http://images.dmzj.com/news/recommend/15009698313703.jpg","object_id":11030,"object_url":"http://v2.api.dmzj.com/article/show/v2/11030.html"},{"id":1237,"title":"P站美图推荐\u2014\u2014裸足特辑","pic_url":"http://images.dmzj.com/news/recommend/15004453321641.jpg","object_id":10930,"object_url":"http://v2.api.dmzj.com/article/show/v2/10930.html"},{"id":1233,"title":"P站美图推荐\u2014\u2014星空特辑（二）","pic_url":"http://images.dmzj.com/news/recommend/15002749128182.jpg","object_id":10899,"object_url":"http://v2.api.dmzj.com/article/show/v2/10899.html"}]
     */

    private var data: List<DataBean>? = null

    fun getData(): List<DataBean>? {
        return data
    }

    fun setData(data: List<DataBean>) {
        this.data = data
    }

    class DataBean {
        /**
         * id : 1250
         * title : P站美图推荐——哭泣特辑（二）
         * pic_url : http://images.dmzj.com/news/recommend/15017449536758.jpg
         * object_id : 11140
         * object_url : http://v2.api.dmzj.com/article/show/v2/11140.html
         */

        var id: Int = 0
        var title: String? = null
        var pic_url: String? = null
        var object_id: Int = 0
        var object_url: String? = null
    }
}