package com.airsaid.localization.translate.impl.bytedance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collection;

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



@AutoService(AbstractTranslator.class)
public class ByteDanceTranslator extends AbstractTranslator {
    private static final Logger LOG = Logger.getInstance(ByteDanceTranslator.class);
    private static final String KEY = "ByteDance";

    private static final String APPLY_APP_ID_URL = "https://www.volcengine.com/docs/4640/130872";
    private static final String[] SOURCE_SUPPORT_LANG_CODE_ARRAY={
        "zh","zh-Hant","zh-Hant-hk","zh-Hant-tw","tn","vi","iu","it",
        "id","hi","en","ho","he","es","el","uk","ur","tk","tr","ti",
        "ty","tl","to","th","ta","te","sl","sk","ss","eo","sm","sg",
        "st","sv","ja","tw","qu","pt","pa","no","nb","nr","my","bn",
        "mn","mh","mk","ml","mr","ms","lu","ro","lt","lv","lo","kj",
        "hr","kn","ki","cs","ca","nl","ko","ht","gu","ka","kl","km",
        "lg","kg","fi","fj","fr","ru","ng","de","tt","da","ts","cv",
        "fa","bs","pl","bi","nd","ba","bg","az","ar","af","sq","ab",
        "os","ee","et","ay","lzh","am","ckb","cy","gl","ha","hy","ig",
        "kmr","ln","nso","ny","om","sn","so","sr","sw","xh","yo","zu",
        "bo","nan","wuu","yue","cmn","ug","fuv","hu","kam","luo","rw",
        "umb","wo"
    };

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
            Set<Lang> langSet = new HashSet<>();
            List<Lang> allSuportedLangedList = Languages.getLanguages();
            for (String code : SOURCE_SUPPORT_LANG_CODE_ARRAY) {
                Lang lang = getLanguageByCode(code,allSuportedLangedList);
                if (lang != null) {
                    langSet.add(lang);
                }
            }
            langSet.add(Languages.CHINESE_SIMPLIFIED.setTranslationCode("zh"));
            langSet.add(Languages.CHINESE_TRADITIONAL.setTranslationCode("zh-Hant"));
            langSet.add(Languages.INDONESIAN.setTranslationCode("id"));
            langSet.add(Languages.HEBREW.setTranslationCode("he"));
            supportedLanguages = new ArrayList<>(langSet);
        }
        return supportedLanguages;
    }

    private Lang getLanguageByCode(String code, List<Lang> list) {
        if (code == null || "".equals(code)) {
            return null;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (Lang lang : list) {
            if (lang.getCode().equals(code)) {
                return lang;
            }
        }
        return null;
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
