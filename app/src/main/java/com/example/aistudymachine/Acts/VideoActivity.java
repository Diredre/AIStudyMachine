package com.example.aistudymachine.Acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.example.aistudymachine.AdapterAndBean.PlayerBean;
import com.example.aistudymachine.R;
import com.example.aistudymachine.AdapterAndBean.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView video_more_rv;
    private List<PlayerBean> videoBeanList = new ArrayList<>();
    private PlayerBean[] videoResourde = {
            new PlayerBean("https://vd2.bdstatic.com/mda-jfcccmya6ncp30mi/sc/mda-jfcccmya6ncp30mi.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633445749-0-0-5fd084fd1f354e82109ac6d501b9f650&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse3-mm.cn.bing.net/th/id/OIP-C.ryTg4R0EJviJb7AqN8S07gHaE8?pid=ImgDet&rs=1",
                    "@七彩小画笔", "#幼儿 #捕捉身边的美好 简笔画一学就会系列，孩子一看就会！\uD83D\uDC7C"),
            new PlayerBean("https://vd2.bdstatic.com/mda-mhsdyqpsjmzuiy3f/sc/cae_h264/1630058168278505997/mda-mhsdyqpsjmzuiy3f.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446161-0-0-b8a8acdce5c7c031365f5e2c46b5f4cf&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://pic2.zhimg.com/v2-ef046746ef59fb030904cb8a1f91a0d9_r.jpg?source=12a79843",
                    "@厚大法考", "张三名场面来了，累了累了不想审了 #法外狂徒张三 #法考 #罗翔说刑法"),
            new PlayerBean("https://vd4.bdstatic.com/mda-jguwy5r78jvxf9xe/sc/mda-jguwy5r78jvxf9xe.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446250-0-0-f391701ec6310ffce33b3fb6a708c785&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse2-mm.cn.bing.net/th/id/OIP-C.Vf7O5BwpkuAHSgDriefNXgHaEK?pid=ImgDet&rs=1",
                    "@宝宝巴士", "#宝宝巴士 #儿童安全 出行除了坐安全座椅，还有这些安全知识要知道！"),
            new PlayerBean("https://vd3.bdstatic.com/mda-jdgcjrv69tvpu5hw/sc/mda-jdgcjrv69tvpu5hw.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446358-0-0-26919d110fd861453690a9f54a79d2a5&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse1-mm.cn.bing.net/th/id/R-C.4a8328e87a901ced674ccf32f991cd7a?rik=1xYq3PLIdq7bAw&riu=http%3a%2f%2fp2.qhimg.com%2ft011038ee5b5f7e20f8.jpg%3fsize%3d1280x718&ehk=RRvqX%2b%2fOXT34Dd%2b32KeQJHhic4OmtRn1njtdMkbdsW8%3d&risl=&pid=ImgRaw&r=0",
                    "@兔小贝Beckybunny", "《滁州西涧》兔小贝少儿学前教育课堂 益智早教古诗 唐诗三百首"),
            new PlayerBean("https://vd4.bdstatic.com/mda-ic8ev9xwe9t14068/sc/mda-ic8ev9xwe9t14068.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446547-0-0-7e06ecbbebefc302560ca6f743105d70&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://img.zcool.cn/community/013d1b57787e260000012e7e6e264d.jpg@1280w_1l_2o_100sh.jpg",
                    "@贝乐虎儿歌", "儿童经典故事《龟兔赛跑》宝宝睡前故事"),
            new PlayerBean("https://vd2.bdstatic.com/mda-jjhcjrfgv8zkzdvr/sc/mda-jjhcjrfgv8zkzdvr.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633504485-0-0-9582e29139b9cccd8db2be7f9e4dc60a&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/1/1jf57lsksqjsvuw.jpg",
                    "@嘟拉手工绘画", "幼儿故事宝宝睡前故事 会说话，说好话"),
            new PlayerBean("https://vd2.bdstatic.com/mda-jfcccmya6ncp30mi/sc/mda-jfcccmya6ncp30mi.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633445749-0-0-5fd084fd1f354e82109ac6d501b9f650&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse3-mm.cn.bing.net/th/id/OIP-C.ryTg4R0EJviJb7AqN8S07gHaE8?pid=ImgDet&rs=1",
                    "@七彩小画笔", "#幼儿 #捕捉身边的美好 简笔画一学就会系列，孩子一看就会！\uD83D\uDC7C"),
            new PlayerBean("https://vd2.bdstatic.com/mda-mhsdyqpsjmzuiy3f/sc/cae_h264/1630058168278505997/mda-mhsdyqpsjmzuiy3f.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446161-0-0-b8a8acdce5c7c031365f5e2c46b5f4cf&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://pic2.zhimg.com/v2-ef046746ef59fb030904cb8a1f91a0d9_r.jpg?source=12a79843",
                    "@厚大法考", "张三名场面来了，累了累了不想审了 #法外狂徒张三 #法考 #罗翔说刑法"),
            new PlayerBean("https://vd4.bdstatic.com/mda-jguwy5r78jvxf9xe/sc/mda-jguwy5r78jvxf9xe.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446250-0-0-f391701ec6310ffce33b3fb6a708c785&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse2-mm.cn.bing.net/th/id/OIP-C.Vf7O5BwpkuAHSgDriefNXgHaEK?pid=ImgDet&rs=1",
                    "@宝宝巴士", "#宝宝巴士 #儿童安全 出行除了坐安全座椅，还有这些安全知识要知道！"),
            new PlayerBean("https://vd3.bdstatic.com/mda-jdgcjrv69tvpu5hw/sc/mda-jdgcjrv69tvpu5hw.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446358-0-0-26919d110fd861453690a9f54a79d2a5&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://tse1-mm.cn.bing.net/th/id/R-C.4a8328e87a901ced674ccf32f991cd7a?rik=1xYq3PLIdq7bAw&riu=http%3a%2f%2fp2.qhimg.com%2ft011038ee5b5f7e20f8.jpg%3fsize%3d1280x718&ehk=RRvqX%2b%2fOXT34Dd%2b32KeQJHhic4OmtRn1njtdMkbdsW8%3d&risl=&pid=ImgRaw&r=0",
                    "@兔小贝Beckybunny", "《滁州西涧》兔小贝少儿学前教育课堂 益智早教古诗 唐诗三百首"),
            new PlayerBean("https://vd4.bdstatic.com/mda-ic8ev9xwe9t14068/sc/mda-ic8ev9xwe9t14068.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633446547-0-0-7e06ecbbebefc302560ca6f743105d70&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://img.zcool.cn/community/013d1b57787e260000012e7e6e264d.jpg@1280w_1l_2o_100sh.jpg",
                    "@贝乐虎儿歌", "儿童经典故事《龟兔赛跑》宝宝睡前故事"),
            new PlayerBean("https://vd2.bdstatic.com/mda-jjhcjrfgv8zkzdvr/sc/mda-jjhcjrfgv8zkzdvr.mp4?v_from_s=hkapp-haokan-nanjing&amp;auth_key=1633504485-0-0-9582e29139b9cccd8db2be7f9e4dc60a&amp;bcevod_channel=searchbox_feed&amp;pd=1&amp;pt=3&amp;abtest=3000187_1",
                    "https://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/hori/1/1jf57lsksqjsvuw.jpg",
                    "@嘟拉手工绘画", "幼儿故事宝宝睡前故事 会说话，说好话")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置屏幕为横屏, 设置后会锁定方向
        setContentView(R.layout.activity_video);

        initView();
    }

    private void initView(){
        for(int i = 0; i < videoResourde.length; i++){
            videoBeanList.add(videoResourde[i]);
        }

        video_more_rv = findViewById(R.id.video_rv);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        video_more_rv.setLayoutManager(layoutManager);
        VideoAdapter videoAdapter = new VideoAdapter(VideoActivity.this, videoBeanList);
        video_more_rv.setAdapter(videoAdapter);
    }
}