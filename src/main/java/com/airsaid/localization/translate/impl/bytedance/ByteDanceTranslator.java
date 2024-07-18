package com.airsaid.localization.translate.impl.bytedance;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.airsaid.localization.translate.AbstractTranslator;
import com.airsaid.localization.translate.TranslationException;
import com.airsaid.localization.translate.lang.Lang;
import com.airsaid.localization.translate.lang.Languages;
import com.airsaid.localization.translate.util.GsonUtil;
import com.esotericsoftware.minlog.Log;
import com.google.auto.service.AutoService;
import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.updateSettings.impl.pluginsAdvertisement.PluginsAdvertiser.Plugin;
import com.intellij.util.io.RequestBuilder;
import com.volcengine.service.translate.ITranslateService;
import com.volcengine.service.translate.impl.TranslateServiceImpl;
import com.volcengine.model.request.translate.TranslateTextRequest;
import com.volcengine.model.response.translate.TranslateTextResponse;
import com.volcengine.model.response.translate.TranslateTextResponse.Translation;
import com.volcengine.model.response.ResponseMetadata;
import com.volcengine.model.response.ResponseMetadata.Error;

import icons.PluginIcons;

import java.util.Arrays;

@AutoService(AbstractTranslator.class)
public class ByteDanceTranslator extends AbstractTranslator {
    private static final Logger LOG = Logger.getInstance(ByteDanceTranslator.class);
    private static final String KEY = "ByteDance";

    private static final String APPLY_APP_ID_URL = "https://www.volcengine.com/docs/4640/130872";
    private List<Lang> supportedLanguages;
    private ITranslateService mService;

    @Override
    public @NotNull String getKey() {
        return KEY;
    }

    @Override
    public @NotNull String getName() {
        return "ByteDance";
    }

    @Override
    public @Nullable Icon getIcon() {
        return PluginIcons.BYTEDANCE_ICON;
    }

    @Override
    public String getAppIdDisplay() {
        return "AccessKey ID";
    }

    @Override
    public String getAppKeyDisplay() {
        return "AccessKey Secret";
    }

    @Override
    public @NotNull List<Lang> getSupportedLanguages() {
        if (supportedLanguages == null) {
            supportedLanguages = new ArrayList<>();
            supportedLanguages.add(Languages.SORANI_KURDISH);
        }
        return supportedLanguages;
    }

    @Override
    public @Nullable String getApplyAppIdUrl() {
        return APPLY_APP_ID_URL;
    }

    @Override
    public String doTranslate(@NotNull Lang fromLang, @NotNull Lang toLang, @NotNull String text)
            throws TranslationException {
        if (mService == null) {
            mService = TranslateServiceImpl.getInstance();
            mService.setAccessKey(getAppId());
            mService.setSecretKey(getAppKey());
        }
        try {
            TranslateTextRequest request = new TranslateTextRequest();
            request.setTargetLanguage(toLang.getCode());
            request.setTextList(Arrays.asList(text));
            TranslateTextResponse response = mService.translateText(request);
            ResponseMetadata.Error error = response.getResponseMetadata().getError();
            if (error != null) {
                throw new TranslationException(fromLang, toLang, text,
                        error.getMessage() + "(" + error.getCode() + ")");
            }
            List<Translation> list = response.getTranslationList();
            if (list == null || list.size() < 1) {
                throw new TranslationException(fromLang, toLang, text, "Empty translation list!");
            }
            return list.get(0).getTranslation();
        } catch (Exception e) {
            throw new TranslationException(fromLang, toLang, text, e);
        }
    }
}
