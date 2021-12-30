/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.demo.utils;

import android.content.Context;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.ext.cronet.CronetDataSourceFactory;
import com.google.android.exoplayer2.ext.cronet.CronetEngineWrapper;
import com.google.android.exoplayer2.offline.ActionFileUpgradeUtil;
import com.google.android.exoplayer2.offline.DefaultDownloadIndex;
import com.google.android.exoplayer2.offline.DownloadManager;
import com.google.android.exoplayer2.ui.DownloadNotificationHelper;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Placeholder application to facilitate overriding Application methods for debugging and testing.
 */
public class DemoApplication extends android.app.Application {


  public static final String DOWNLOAD_NOTIFICATION_CHANNEL_ID = "download_channel";

  private static final String TAG = "DemoApplication";
  private static final String DOWNLOAD_ACTION_FILE = "actions";
  private static final String DOWNLOAD_TRACKER_ACTION_FILE = "tracked_actions";
  private static final String DOWNLOAD_CONTENT_DIRECTORY = "downloads";

  protected static String userAgent;

  private static DatabaseProvider databaseProvider;
  private static File downloadDirectory;
  private static Cache downloadCache;
  private static DownloadManager downloadManager;
  private static DownloadTracker downloadTracker;
  private static HttpDataSource.Factory httpDataSourceFactory;
  private static DownloadNotificationHelper downloadNotificationHelper;

  /** Returns a {@link DataSource.Factory}. */
  public static DataSource.Factory buildDataSourceFactory(Context context) {
    DefaultDataSourceFactory upstreamFactory =
            new DefaultDataSourceFactory(context, buildHttpDataSourceFactory(context));
    return buildReadOnlyCacheDataSource(upstreamFactory, getDownloadCache(context));
  }

  /** Returns a {@link HttpDataSource.Factory}. */
  public static HttpDataSource.Factory buildHttpDataSourceFactory(Context context) {
    userAgent = Util.getUserAgent(context, "ExoPlayerDemo");
    return new DefaultHttpDataSource.Factory();
  }

  /** Returns whether extension renderers should be used. */
  public static boolean useExtensionRenderers() {
    return true;//BuildConfig.USE_DECODER_EXTENSIONS;
  }

  public static RenderersFactory buildRenderersFactory(boolean preferExtensionRenderer, Context context) {
    @DefaultRenderersFactory.ExtensionRendererMode
    int extensionRendererMode =
            useExtensionRenderers()
                    ? (preferExtensionRenderer
                    ? DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
                    : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                    : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF;
    return new DefaultRenderersFactory(/* context= */ context)
            .setExtensionRendererMode(extensionRendererMode);
  }

  public static synchronized HttpDataSource.Factory getHttpDataSourceFactory(Context context) {
    if (httpDataSourceFactory == null) {
      context = context.getApplicationContext();
      CronetEngineWrapper cronetEngineWrapper = new CronetEngineWrapper(context);
      httpDataSourceFactory =
              new CronetDataSourceFactory(cronetEngineWrapper, Executors.newSingleThreadExecutor());
    }
    return httpDataSourceFactory;
  }

  public static DownloadManager getDownloadManager(Context context) {
    ensureDownloadManagerInitialized(context);
    return downloadManager;
  }

  public static DownloadTracker getDownloadTracker(Context context) {
    ensureDownloadManagerInitialized(context);
    return downloadTracker;
  }

  protected static synchronized Cache getDownloadCache(Context context) {
    if (downloadCache == null) {
      File downloadContentDirectory = new File(getDownloadDirectory(context), DOWNLOAD_CONTENT_DIRECTORY);
      downloadCache =
              new SimpleCache(downloadContentDirectory, new NoOpCacheEvictor(), getDatabaseProvider(context));
    }
    return downloadCache;
  }

  private static synchronized void ensureDownloadManagerInitialized(Context context) {
    if (downloadManager == null) {
      DefaultDownloadIndex downloadIndex = new DefaultDownloadIndex(getDatabaseProvider(context));
      upgradeActionFile(
              context, DOWNLOAD_ACTION_FILE, downloadIndex, /* addNewDownloadsAsCompleted= */ false);
      upgradeActionFile(
              context,
              DOWNLOAD_TRACKER_ACTION_FILE,
              downloadIndex,
              /* addNewDownloadsAsCompleted= */ true);
      downloadManager =
              new DownloadManager(
                      context,
                      getDatabaseProvider(context),
                      getDownloadCache(context),
                      getHttpDataSourceFactory(context),
                      Executors.newFixedThreadPool(/* nThreads= */ 6));
      downloadTracker =
              new DownloadTracker(context, getHttpDataSourceFactory(context), downloadManager);
    }
  }

  private static synchronized void upgradeActionFile(
          Context context,
          String fileName,
          DefaultDownloadIndex downloadIndex,
          boolean addNewDownloadsAsCompleted) {
    try {
      ActionFileUpgradeUtil.upgradeAndDelete(
              new File(getDownloadDirectory(context), fileName),
              /* downloadIdProvider= */ null,
              downloadIndex,
              /* deleteOnFailure= */ true,
              addNewDownloadsAsCompleted);
    } catch (IOException e) {
      Log.e(TAG, "Failed to upgrade action file: " + fileName, e);
    }
  }

  private static DatabaseProvider getDatabaseProvider(Context context) {
    if (databaseProvider == null) {
      databaseProvider = new ExoDatabaseProvider(context);
    }
    return databaseProvider;
  }

  private static File getDownloadDirectory(Context context) {
    if (downloadDirectory == null) {
      downloadDirectory = context.getExternalFilesDir(null);
      if (downloadDirectory == null) {
        downloadDirectory = context.getFilesDir();
      }
    }
    return downloadDirectory;
  }

  private static CacheDataSource.Factory buildReadOnlyCacheDataSource(
          DataSource.Factory upstreamFactory, Cache cache) {
    return new CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setCacheWriteDataSinkFactory(null)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);
  }

  public static synchronized DownloadNotificationHelper getDownloadNotificationHelper(
          Context context) {
    if (downloadNotificationHelper == null) {
      downloadNotificationHelper =
              new DownloadNotificationHelper(context, DOWNLOAD_NOTIFICATION_CHANNEL_ID);
    }
    return downloadNotificationHelper;
  }
}
