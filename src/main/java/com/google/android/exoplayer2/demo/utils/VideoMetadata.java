package com.google.android.exoplayer2.demo.utils;

import com.google.android.exoplayer2.demo.R;

import java.util.Arrays;
import java.util.List;

/**
 * An enumeration of video metadata.
 */
public enum VideoMetadata {
    HLS_VOD_VIDEO(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "HLS VoD",
            null,
            R.drawable.thumbnail1,
            false,
            false),
    HLS_AVG_BR_VOD_VIDEO(
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",
            "HLS VoD Avgbr",
            null,
            R.drawable.thumbnail1,
            false,
            false),
    HLS_LIVE_VIDEO(
            "https://www.bloomberg.com/media-manifest/streams/phoenix-us.m3u8",
            "HLS Live",
            null,
            R.drawable.thumbnail1,
            false,
            true),
    MP4_VIDEO(
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "MP4",
            null,
            R.drawable.thumbnail1,
            false,
            false),
    DASH_VOD_VIDEO(
            "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd",
            "DASH VoD",
            null,
            R.drawable.thumbnail1,
            false,
            false),
    DASH_LIVE_VIDEO(
            "http://livesim.dashif.org/livesim/segtimeline_1/testpic_2s/Manifest.mpd",
            "DASH Live",
            null,
            R.drawable.thumbnail1,
            false,
            true),
    SS_VOD_VIDEO(
            "http://playready.directtaps.net/smoothstreaming/SSWSS720H264/SuperSpeedway_720.ism/Manifest",
            "SS VoD",
            null,
            R.drawable.thumbnail1,
            false,
            false),
    SS_LIVE_VIDEO(
            "http://livecdnh2.tvanywhere.ae/ss/mbc1.isml/Manifest?v=1391545906",
            "SS Live",
            null,
            R.drawable.thumbnail1,
            false,
            true),
    PRE_ROLL_NO_SKIP(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Inline Linear - not skippable",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/" +
                    "single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast" +
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct" +
                    "%3Dlinear&correlator=",
            R.drawable.thumbnail1,
            false,
            false),
    PRE_ROLL_SKIP(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Skippable Inline",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/" +
                    "single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast" +
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct" +
                    "%3Dskippablelinear&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_Redirect_Linear(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Redirect Linear",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"+
                    "single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dredirectlinear&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_Redirect_Error(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Redirect Error",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dredirecterror&nofb=1&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_Redirect_Broken_Fallback(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Redirect Broken (Fallback)",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dredirecterror&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_VPAID_2_0_Linear(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single VPAID_2_0_Linear",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dlinearvpaid2js&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_VPAID_2_0_Non_Linear(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single VPAID 2.0 Non-Linear",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dnonlinearvpaid2js&correlator=",
            R.drawable.thumbnail1,
            false,
            false),

    Single_Non_linear_Inline(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, Single Nonlinear Inline",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external"+
                    "/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct"+
                    "%3Dnonlinear&correlator=",
            R.drawable.thumbnail1,
            false,
            false),


    VMAP_Pre_roll(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, VMAP Pre-roll",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpreonly&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),


    VMAP_Pre_roll_Bumper(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Pre-roll, VMAP Pre-roll + Bumper",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpreonlybumper&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Post_roll(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Post-roll, VMAP Post-roll",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpostonly&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Post_roll_Bumper(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "Post-roll, VMAP Post-roll + Bumper",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpostonlybumper&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Mid_Post_Single_Ads(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP Pre-, Mid-, and Post-rolls, Single Ads",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpost&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Single_Mid_Standard_Pod_3_ads_Post_Single_Ad(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP - Pre-roll Single Ad, Mid-roll Standard Pod with 3 ads, Post-roll Single Ad",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpostpod&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Single_Ad_Mid_Optimized_Pod_3_Ads_Post_Single_Ad(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP - Pre-roll Single Ad, Mid-roll Optimized Pod with 3 Ads, Post-roll Single Ad",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpostoptimizedpod&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Single_Mid_Standard_Pod_3_ads_Post_Single_Ad_with_bumpers(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP - Pre-roll Single Ad, Mid-roll Standard Pod with 3 ads, Post-roll Single Ad With Bumpers",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpostpodbumper&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Single_Ad_Mid_Optimized_Pod_3_Ads_Post_Single_Ad_with_bumpers(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP - Pre-roll Single Ad, Mid-roll Optimized Pod with 3 Ads, Post-roll Single Ad with Bumpers",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpostoptimizedpodbumper&cmsid=496&vid=short_onecue&correlator=",
            R.drawable.thumbnail1,
            true,
            false),

    VMAP_Pre_Single_Ad_Mid_Standard_Pods_5_Ads_Every_10_Seconds_Post_Single_Ad(
            "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8",
            "VMAP- Pre-roll Single Ad, Mid-roll Standard Pods with 5 Ads Every 10 Seconds for 1:40, Post-roll Single Ad",
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external"+
                    "/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap"+
                    "&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar"+
                    "%3Dpremidpostlongpod&cmsid=496&vid=short_tencue&correlator=",
            R.drawable.thumbnail1,
            true,
            false);

    public static final List<VideoMetadata> APP_VIDEOS =
            Arrays.asList(HLS_VOD_VIDEO, HLS_AVG_BR_VOD_VIDEO, HLS_LIVE_VIDEO, MP4_VIDEO, DASH_VOD_VIDEO, DASH_LIVE_VIDEO, SS_VOD_VIDEO, SS_LIVE_VIDEO, PRE_ROLL_NO_SKIP, PRE_ROLL_SKIP,
                    Single_Redirect_Linear,
                    Single_Redirect_Error,
                    Single_Redirect_Broken_Fallback,
                    Single_VPAID_2_0_Linear,
                    Single_VPAID_2_0_Non_Linear,
                    Single_Non_linear_Inline,
                    VMAP_Pre_roll,
                    VMAP_Pre_roll_Bumper,
                    VMAP_Post_roll,
                    VMAP_Post_roll_Bumper,
                    VMAP_Pre_Mid_Post_Single_Ads,
                    VMAP_Pre_Single_Mid_Standard_Pod_3_ads_Post_Single_Ad,
                    VMAP_Pre_Single_Ad_Mid_Optimized_Pod_3_Ads_Post_Single_Ad,
                    VMAP_Pre_Single_Mid_Standard_Pod_3_ads_Post_Single_Ad_with_bumpers,
                    VMAP_Pre_Single_Ad_Mid_Optimized_Pod_3_Ads_Post_Single_Ad_with_bumpers,
                    VMAP_Pre_Single_Ad_Mid_Standard_Pods_5_Ads_Every_10_Seconds_Post_Single_Ad);

    /**
     * The thumbnail image for the video.
     **/
    public final int thumbnail;

    /**
     * The title of the video.
     **/
    public final String title;

    /**
     * The URL for the video.
     **/
    public final String videoUrl;

    /**
     * The ad tag for the video
     **/
    public final String adTagUrl;

    /**
     * If the ad is VMAP.
     **/
    public final boolean isVmap;

    /**
     * If the content is live or vod.
     **/
    public final boolean isLive;


    private VideoMetadata(String videoUrl, String title, String adTagUrl, int thumbnail,
                          boolean isVmap, boolean isLive) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.adTagUrl = adTagUrl;
        this.thumbnail = thumbnail;
        this.isVmap = isVmap;
        this.isLive = isLive;
    }
}
