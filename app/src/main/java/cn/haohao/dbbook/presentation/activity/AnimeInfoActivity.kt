package cn.haohao.dbbook.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import cn.haohao.dbbook.R
import kotlinx.android.synthetic.main.activity_anime_info.*

class AnimeInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_info)
        initTitle()
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
}
