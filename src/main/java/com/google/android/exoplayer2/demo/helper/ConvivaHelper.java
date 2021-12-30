package com.google.android.exoplayer2.demo.helper;

import android.content.Context;
import android.content.Intent;

import com.conviva.sdk.ConvivaAnalytics;
import com.conviva.sdk.ConvivaVideoAnalytics;
import com.conviva.sdk.ConvivaAdAnalytics;
import com.conviva.sdk.ConvivaSdkConstants;
import com.google.ads.interactivemedia.v3.api.AdsLoader;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import static com.conviva.sdk.ConvivaSdkConstants.GATEWAY_URL;
import static com.conviva.sdk.ConvivaSdkConstants.LOG_LEVEL;

public class ConvivaHelper {

    private static final String PLAYER_APPLICATION_NAME = "ExoPlayerSample";
    //TODO: Replace with Gateway URL provided by Conviva
    private static final String GATEWAY_URL_VALUE = "GATEWAY_URL_VALUE";
    //TODO: Replace with Customer Key provided by Conviva
    private static final String CUSTOMER_KEY_VALUE = "CUSTOMER_KEY_VALUE";
    private static final String VIEWER_ID_VALUE = "123456789";
    private static final String RESOURCE_VALUE = "resource";
    private static final int ENCODED_FRAMERATE_VALUE = 27;

    private static final String TAG_1_KEY = "tag1";
    private static final String TAG_1_VALUE = "val1";
    private static final String TAG_2_KEY = "tag2";
    private static final String TAG_2_VALUE = "val2";

    private static Context context;
    private static ConvivaVideoAnalytics videoAnalytics;
    private static ConvivaAdAnalytics adAnalytics;

    //region ConvivaAnalytics initialisation and cleanup
    /**
     * Demonstration of ConvivaAnalytics initialisation
     * @param _context
     */
    public static void initConviva(Context _context) {
        context = _context;

        HashMap<String, Object> settings = new HashMap<>();

        settings.put(GATEWAY_URL, GATEWAY_URL_VALUE);

        // In Production the log level need not be set and will be taken as NONE.
        settings.put(LOG_LEVEL, ConvivaSdkConstants.LogLevel.INFO);

        ConvivaAnalytics.init(_context, CUSTOMER_KEY_VALUE, settings, null);
    }

    /**
     * Demonstration of ConvivaAnalytics cleanup
     */
    public static void release() {
        ConvivaAnalytics.release();
    }
    //endregion

    //region Video Experience

    /**
     * Demonstration of ConvivaVideoAnalytics object creation
     * @param context
     */
    public static ConvivaVideoAnalytics getInstance(Context context){
        return ConvivaAnalytics.buildVideoAnalytics(context);
    }

    /**
     * Demonstration of content playback start & report predefined video metadata & exoplayer module integration
     * @param videoAnalytics
     * @param player
     * @param intent
     * @param asset
     */
    public static void onContentPlaybackStart(ConvivaVideoAnalytics videoAnalytics, Object player, Intent intent, String asset, Boolean isLive) {

        //Initialize and set video content metadata.
        HashMap<String, Object> contentInfo = new HashMap<>();
        contentInfo.put(ConvivaSdkConstants.STREAM_URL, intent.getData());
        contentInfo.put(ConvivaSdkConstants.ASSET_NAME, asset);
        // Based on the content stream type, isLive can be configured here.
        contentInfo.put(ConvivaSdkConstants.IS_LIVE, isLive);
        // player application name is not likely to change.
        contentInfo.put(ConvivaSdkConstants.PLAYER_NAME, PLAYER_APPLICATION_NAME);
        //viewerId is typically unique id that distinguishes a user.
        contentInfo.put(ConvivaSdkConstants.VIEWER_ID, VIEWER_ID_VALUE);
        contentInfo.put(ConvivaSdkConstants.DEFAULT_RESOURCE, RESOURCE_VALUE);
        contentInfo.put(ConvivaSdkConstants.ENCODED_FRAMERATE, ENCODED_FRAMERATE_VALUE);
        contentInfo.put(TAG_1_KEY, TAG_1_VALUE);
        contentInfo.put(TAG_2_KEY, TAG_2_VALUE);

        //Initializes exoplayer conviva module.make sure to pass the correct simpleExo player
        //instance that is used to play the video content.
        videoAnalytics.setPlayer(player);

        //Notify playback has started.
        videoAnalytics.reportPlaybackRequested(contentInfo);
    }

    /**
     * Demonstration of content playback stop
     * @param videoAnalytics
     */
    public static void onContentPlaybackEnded(ConvivaVideoAnalytics videoAnalytics){
        videoAnalytics.reportPlaybackEnded();
    }

    /**
     * Demonstration of ConvivaVideoAnalytics cleanup
     * @param videoAnalytics
     */
    public static void release(ConvivaVideoAnalytics videoAnalytics){
        videoAnalytics.release();
    }
    //endregion

    //region Ad Experience

    /**
     * Demonstration of ConvivaAdAnalytics creation & Ad module integration.
     * @param adsLoader
     * @param videoAnalytics
     * @param url
     */
    public static void initAdSession(AdsLoader adsLoader, ConvivaVideoAnalytics videoAnalytics, String url) {

        adAnalytics = ConvivaAnalytics.buildAdAnalytics(context, videoAnalytics);
        Map<String, Object> tags = new HashMap<>();
        tags.put(ConvivaSdkConstants.IS_LIVE, "false");
        tags.put(ConvivaSdkConstants.ENCODED_FRAMERATE, "30");
        tags.put("c3.ad.adManagerVersion", "3.18.1");
        adAnalytics.setAdInfo(tags);

        Map<String, Object> adMetadata = new HashMap<>();
        adMetadata.put(ConvivaSdkConstants.AD_TAG_URL, url);
        adMetadata.put(ConvivaSdkConstants.AD_PLAYER, ConvivaSdkConstants.AdPlayer.SEPARATE.toString());
        adAnalytics.setAdListener(adsLoader, adMetadata);
    }

    /**
     * Demonstration of ConvivaAdAnalytics cleanup.
     */
    public static void releaseAdSession(){
        if (adAnalytics != null) {
            adAnalytics.release();
        }
    }
    //endregion
}
