package com.example.aistudymachine.Acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aistudymachine.AdapterAndBean.PlayerAdapter;
import com.example.aistudymachine.AdapterAndBean.PlayerBean;
import com.example.aistudymachine.AdapterAndBean.VideoCommentAdapter;
import com.example.aistudymachine.AdapterAndBean.VideoCommentBean;
import com.example.aistudymachine.Dialogs.AwardDialog;
import com.example.aistudymachine.R;
import com.example.aistudymachine.widget.MyJzvdStd;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoPlayActivity extends AppCompatActivity implements View.OnClickListener{

    //侧边menu栏+标题+作者
    private ImageView videoplay_iv_like, videoplay_iv_comment,
            videoplay_iv_award, videoplay_iv_more;
    private TextView videoplay_tv_like, videoplay_tv_comment;
    //上下滑动视频
    private RecyclerView videoplay_rv_video;
    private PlayerAdapter playerAdapter;
    private List<PlayerBean> urlList = new ArrayList<>();
    private PlayerBean[] urls = {
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
    //评论区dialog
    private BottomSheetDialog dialog;
    private boolean isLike = false;
    private VideoCommentAdapter comment_adapter;
    private List<VideoCommentBean> comList = new ArrayList<>();
    private VideoCommentBean[] coms = {
            new VideoCommentBean(R.mipmap.icon, "你也在学习吗", "18分钟前",
                    "之前去上海的时候特地跑去参观了一下~", "24"),
            new VideoCommentBean(R.mipmap.icon, "悠悠", "5小时前",
                    "我们的目标是星辰大海。", "13"),
            new VideoCommentBean(R.mipmap.icon, "名字不能引人注意", "3天前",
                    "好好学习！", "5"),
            new VideoCommentBean(R.mipmap.icon, "宁波小学卢孟", "8天前",
                    "历史不会把你们忘记，那飘扬着的五星红旗，就是用你们热血浸染的。那高矗的胜利丰碑，正是用你们的头颅堆砌的。立足今天，放眼未来，让我们一同来学习革命先烈的爱国精神和英雄气概，在人生道路上，克服重重艰难险阻，迈向光明，迈向未来。", "7"),
            new VideoCommentBean(R.mipmap.icon, "红星小学德华", "17天前",
                    "够意思，你似干介个的！我就知道你是个爱国up，一定和爱国网站在一起！当年前几期站里就掏过钱，新一季您老安排下？", "5"),
            new VideoCommentBean(R.mipmap.icon, "针不戳", "1个月前",
                    "回首党史，缅怀先烈，我们看到在革命先烈用鲜血打下的江山里，一代代新生的中国共产党人牢记初心，开拓奋进，带领中国人民进行社会主义建设、实施改革开放，一点一滴地把先烈的理想变为现实。", "13"),
            new VideoCommentBean(R.mipmap.icon, "pol1", "2个月前",
                    "想想当年，想想当年！先烈那么困难都打败了卖国代理人。兄弟们都支楞起来！一定要打败日本资本代理人！", "8"),
            new VideoCommentBean(R.mipmap.icon, "为乐当及时の朱裕民", "3个月前",
                    "强烈推荐《觉醒年代》电视剧，好看好哭", "0")};


    /**
     * 改变Recycler的滑动速度
     * @param recyclerView
     * @param velocity    滑动速度默认是8000dp
     */
    public static void setMaxFlingVelocity(RecyclerView recyclerView, int velocity){
        try{
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);               //设置无titleBar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //设置屏幕为横屏, 设置后会锁定方向
        setContentView(R.layout.activity_video_play);

        initView();
        initVideoPlayer();
    }

    /**
     * 控件绑定和初始化
     */
    private void initView(){
        videoplay_iv_like = findViewById(R.id.videoplay_iv_like);
        videoplay_iv_like.setOnClickListener(this);
        videoplay_iv_comment = findViewById(R.id.videoplay_iv_comment);
        videoplay_iv_comment.setOnClickListener(this);
        videoplay_iv_award = findViewById(R.id.videoplay_iv_award);
        videoplay_iv_award.setOnClickListener(this);
        videoplay_iv_more = findViewById(R.id.videoplay_iv_more);
        videoplay_iv_more.setOnClickListener(this);
        videoplay_tv_like = findViewById(R.id.videoplay_tv_like);
        videoplay_tv_comment = findViewById(R.id.videoplay_tv_comment);

        videoplay_iv_like.setColorFilter(Color.parseColor("#FFFFFF"));
        videoplay_iv_comment.setColorFilter(Color.parseColor("#FFFFFF"));
        videoplay_iv_award.setColorFilter(Color.parseColor("#FFFFFF"));
        videoplay_iv_more.setColorFilter(Color.parseColor("#FFFFFF"));
    }

    /**
     * 初始化播放器
     */
    private void initVideoPlayer(){
        // 设置滑动速度
        setMaxFlingVelocity(videoplay_rv_video, 500);
        // 初始化数据
        videoplay_rv_video = findViewById(R.id.videoplay_rv_video);
        for(int i  = 0; i < urls.length; i++){
            urlList.add(urls[i]);
        }
        // 上下滑动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VideoPlayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoplay_rv_video.setLayoutManager(linearLayoutManager);
        // SnapHelper滑动对准
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(videoplay_rv_video);
        // adapter应用
        playerAdapter = new PlayerAdapter(VideoPlayActivity.this, urlList);
        videoplay_rv_video.setAdapter(playerAdapter);

        videoplay_rv_video.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {}
            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.itemplay_videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });
        videoplay_rv_video.addOnScrollListener(new AutoPlayScrollListener(this));
    }

    /**
     * 重写点击监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.videoplay_iv_like:
                int likenum1 = Integer.parseInt(videoplay_tv_like.getText().toString()) - 1, likenum2 = likenum1 + 2;
                if(!isLike) {
                    videoplay_iv_like.setColorFilter(Color.parseColor("#2196F3"));
                    videoplay_tv_like.setTextColor(Color.parseColor("#2196F3"));
                    videoplay_tv_like.setText("" + likenum2);
                    isLike = true;
                }else{
                    videoplay_iv_like.setColorFilter(Color.parseColor("#FFFFFF"));
                    videoplay_tv_like.setTextColor(Color.parseColor("#FFFFFF"));
                    videoplay_tv_like.setText("" + likenum1);
                    isLike = false;
                }
                break;
            case R.id.videoplay_iv_comment:
                showCommentDialog();
                break;
            case R.id.videoplay_iv_award:
                new AwardDialog(VideoPlayActivity.this).show();
                break;
            case R.id.videoplay_iv_more:
                startActivity(new Intent(VideoPlayActivity.this, VideoActivity.class));
                break;
        }
    }


    /**
     * 弹出评论区（底部dialog
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.dialog_comment_list,null);
        dialog.setContentView(commentView);

        final ImageView dialog_comment_list_cansel = commentView.findViewById(R.id.dialog_comment_list_cansel);
        dialog_comment_list_cansel.setOnClickListener(v->{
            dialog.dismiss();
        });

        final RecyclerView dialog_comment_list_rv = commentView.findViewById(R.id.dialog_comment_list_rv);
        for(int i = 0; i < 8; i++){
            comList.add(coms[i]);
        }

        //解决显示不全的情况
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VideoPlayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dialog_comment_list_rv.setLayoutManager(linearLayoutManager);
        comment_adapter = new VideoCommentAdapter(VideoPlayActivity.this, comList);
        dialog_comment_list_rv.setAdapter(comment_adapter);

        final EditText commentText = commentView.findViewById(R.id.dialog_comment_et_com);
        final Button bt_comment = commentView.findViewById(R.id.dialog_comment_btn_send);
        final TextView dialog_comment_list_account = commentView.findViewById(R.id.dialog_comment_list_account);
        int account = Integer.parseInt(videoplay_tv_comment.getText().toString());
        dialog_comment_list_account.setText("" + account);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    commentText.setText("");
                    Toast.makeText(VideoPlayActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                    VideoCommentBean comment = new VideoCommentBean(R.mipmap.icon,
                            "蜡笔小新", "刚刚", commentContent, "0");
                    //comment_adapter.addTheCommentData(comment);
                    comList.add(0, comment);
                    comment_adapter.notifyItemInserted(0);
                    dialog_comment_list_rv.getLayoutManager().scrollToPosition(0);

                    int comnum = Integer.parseInt(videoplay_tv_comment.getText().toString()) + 1;
                    videoplay_tv_comment.setText("" + comnum);
                    dialog_comment_list_account.setText("" + comnum);
                }else {
                    Toast.makeText(VideoPlayActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length() > 2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        dialog.show();
    }


    /**
     * 监听recycleView滑动状态，
     * 自动播放可见区域内的第一个视频
     */
    private static class AutoPlayScrollListener extends RecyclerView.OnScrollListener {
        private int firstVisibleItem = 0;
        private int lastVisibleItem = 0;
        private int visibleCount = 0;
        private Context context;

        public AutoPlayScrollListener(Context context) {
            this.context = context;
        }

        //被处理的视频状态标签
        private enum VideoTagEnum {
            TAG_AUTO_PLAY_VIDEO, //自动播放视频
            TAG_PAUSE_VIDEO //暂停视频
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:    //停止滚动
                    autoPlayVideo(recyclerView, VideoTagEnum.TAG_AUTO_PLAY_VIDEO);
                default:
                    //autoPlayVideo(recyclerView, VideoTagEnum.TAG_PAUSE_VIDEO);//滑动时暂停视频 需要可以加上
                    break;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                firstVisibleItem = linearManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearManager.findLastVisibleItemPosition();
                visibleCount = lastVisibleItem - firstVisibleItem;
            }
        }

        /**
         * 循环遍历 可见区域的播放器
         * 然后通过 getLocalVisibleRect(rect)方法计算出哪个播放器完全显示出来
         * getLocalVisibleRect 相关链接：http://www.cnblogs.com/ai-developers/p/4413585.html
         * @param recyclerView
         * @param handleVideoTag 视频需要进行状态
         */
        private void autoPlayVideo(RecyclerView recyclerView, VideoTagEnum handleVideoTag) {
            for (int i = 0; i < visibleCount; i++) {
                if (recyclerView != null && recyclerView.getChildAt(i) != null
                        && recyclerView.getChildAt(i).findViewById(R.id.itemplay_videoplayer) != null) {
                    MyJzvdStd homeGSYVideoPlayer = recyclerView.getChildAt(i)
                            .findViewById(R.id.itemplay_videoplayer);
                    Rect rect = new Rect();
                    homeGSYVideoPlayer.getLocalVisibleRect(rect);
                    int videoheight = homeGSYVideoPlayer.getHeight();

                    if (rect.top == 0 && rect.bottom == videoheight) {
                        handleVideo(handleVideoTag, homeGSYVideoPlayer);
                        // 跳出循环，只处理可见区域内的第一个播放器
                        break;
                    }
                }
            }
        }

        /**
         * 视频状态处理
         * @param handleVideoTag     视频需要进行状态
         * @param homeGSYVideoPlayer JZVideoPlayer播放器
         */
        private void handleVideo(VideoTagEnum handleVideoTag, MyJzvdStd homeGSYVideoPlayer) {
            switch (handleVideoTag) {
                case TAG_AUTO_PLAY_VIDEO:
                    //if ((homeGSYVideoPlayer.state != STATE_PLAYING)) {
                        // 进行播放
                        homeGSYVideoPlayer.startButton.performClick();
                    //}
                    break;
                case TAG_PAUSE_VIDEO:
                    //if ((homeGSYVideoPlayer.state != STATE_PAUSE)) {
                        // 模拟点击,暂停视频
                        homeGSYVideoPlayer.startButton.performClick();
                    //}
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 拦截返回键 返回不退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Jzvd.backPress()) {
            return true;
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                moveTaskToBack(true);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}