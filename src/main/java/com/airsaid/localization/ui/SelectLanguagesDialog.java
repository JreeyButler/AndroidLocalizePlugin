/*
 * Copyright 2021 Airsaid. https://github.com/airsaid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.airsaid.localization.ui;

import com.airsaid.localization.config.SettingsState;
import com.airsaid.localization.constant.Constants;
import com.airsaid.localization.translate.AbstractTranslator;
import com.airsaid.localization.translate.lang.Lang;
import com.airsaid.localization.translate.services.TranslatorService;
import com.airsaid.localization.utils.LanguageUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Select the language dialog you want to Translate.
 *
 * @author airsaid
 */
public class SelectLanguagesDialog extends DialogWrapper {
    private static final Logger LOG = Logger.getInstance(SelectLanguagesDialog.class);

    private JPanel contentPanel;
    private JCheckBox overwriteExistingStringCheckBox;
    private JCheckBox selectAllCheckBox;
    private JPanel languagesPanel;
    private JCheckBox openTranslatedFileCheckBox;
    private JLabel powerTranslatorLabel;
    private JCheckBox showChineseLanguageName;
    private JCheckBox sortByLanguageCode;
    private List<Lang> mLangList;

    private final Project project;
    private OnClickListener onClickListener;
    private final List<Lang> selectedLanguages = new ArrayList<>();

    public interface OnClickListener {
        void onClickListener(List<Lang> selectedLanguage);
    }

    public SelectLanguagesDialog(@Nullable Project project) {
        super(project, false);
        this.project = project;
        doCreateCenterPanel();
        setTitle("Select Translated Languages");
        init();
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPanel;
    }

    private void doCreateCenterPanel() {
        // add languages
        selectedLanguages.clear();
        List<Lang> supportedLanguages = Objects.requireNonNull(TranslatorService.getInstance().getSelectedTranslator()).getSupportedLanguages();

        // 创建副本以避免影响原始列表
        List<Lang> languagesToSort = new ArrayList<>(supportedLanguages);

        final boolean isSortByLanguageCode = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_SORT_BY_LANGUAGE_CODE);
        languagesToSort.sort(isSortByLanguageCode ?
                new LanguageCodeComparator() :
                new EnglishNameComparator());
        boolean isShowChinese = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_SHOW_CN_LANGUAGE_NAME);
        mLangList = languagesToSort;
        addLanguageList(mLangList, isShowChinese);

        // add options
        initOverwriteExistingStringOption();
        initOpenTranslatedFileCheckBox();
        initSelectAllOption();
        initShowChineseLanguageOption();
        initSortByLanguageCodeOption();

        // set power ui
        AbstractTranslator translator = TranslatorService.getInstance().getSelectedTranslator();
        powerTranslatorLabel.setText("Powered by " + translator.getName());
        powerTranslatorLabel.setIcon(translator.getIcon());
    }

    private void initSortByLanguageCodeOption() {
        boolean isSortByLanguageCode = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_SORT_BY_LANGUAGE_CODE);
        sortByLanguageCode.setSelected(isSortByLanguageCode);
        sortByLanguageCode.addItemListener(e -> {
            final int state = e.getStateChange();
            final boolean isSelected = state == ItemEvent.SELECTED;
            boolean isShowChinese = PropertiesComponent.getInstance(project)
                    .getBoolean(Constants.KEY_IS_SHOW_CN_LANGUAGE_NAME);
            mLangList.sort(isSelected ?
                    new LanguageCodeComparator() :
                    new EnglishNameComparator());
            addLanguageList(mLangList, isShowChinese);
            PropertiesComponent.getInstance(project)
                    .setValue(Constants.KEY_IS_SORT_BY_LANGUAGE_CODE, isSelected);
        });
    }

    private void initShowChineseLanguageOption() {
        boolean isShowChineseLanguageName = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_SHOW_CN_LANGUAGE_NAME);
        showChineseLanguageName.setSelected(isShowChineseLanguageName);
        showChineseLanguageName.addItemListener(e -> {
            final int state = e.getStateChange();
            final boolean isSelected = state == ItemEvent.SELECTED;
            addLanguageList(mLangList, isSelected);
            PropertiesComponent.getInstance(project)
                    .setValue(Constants.KEY_IS_SHOW_CN_LANGUAGE_NAME, isSelected);
        });
    }

    private void addLanguageList(List<Lang> supportedLanguages, boolean isShowChinese) {
        if (supportedLanguages == null || supportedLanguages.isEmpty()) {
            return;
        }
        List<String> selectedLanguageIds = LanguageUtil.getSelectedLanguageIds(project);
        LOG.info("supportedLanguages list:" + supportedLanguages.size());
        languagesPanel.removeAll();
        selectedLanguages.clear();
        int rows = (int) Math.ceil(supportedLanguages.size() / 4.0);
        languagesPanel.setLayout(new GridLayout(rows, 4));
        for (Lang language : supportedLanguages) {
            String code = language.getCode();
            JBCheckBox checkBoxLanguage = new JBCheckBox(
                    (isShowChinese ? language.getChineseName() : language.getEnglishName())
                            + "(" + code + ")");
            checkBoxLanguage.addItemListener(e -> {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    selectedLanguages.add(language);
                } else {
                    selectedLanguages.remove(language);
                }
                // Update the OK button UI
                getOKAction().setEnabled(!selectedLanguages.isEmpty());
            });
            if (selectedLanguageIds != null &&
                    selectedLanguageIds.contains(String.valueOf(language.getId()))) {
                checkBoxLanguage.setSelected(true);
            }
            languagesPanel.add(checkBoxLanguage);
        }
        languagesPanel.revalidate();
        languagesPanel.repaint();
    }

    private void initOverwriteExistingStringOption() {
        boolean isOverwriteExistingString = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_OVERWRITE_EXISTING_STRING);
        overwriteExistingStringCheckBox.setSelected(isOverwriteExistingString);
        overwriteExistingStringCheckBox.addItemListener(e -> {
            int state = e.getStateChange();
            PropertiesComponent.getInstance(project)
                    .setValue(Constants.KEY_IS_OVERWRITE_EXISTING_STRING, state == ItemEvent.SELECTED);
        });
    }

    private void initOpenTranslatedFileCheckBox() {
        boolean isOpenTranslatedFile = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_OPEN_TRANSLATED_FILE);
        openTranslatedFileCheckBox.setSelected(isOpenTranslatedFile);
        openTranslatedFileCheckBox.addItemListener(e -> {
            int state = e.getStateChange();
            PropertiesComponent.getInstance(project)
                    .setValue(Constants.KEY_IS_OPEN_TRANSLATED_FILE, state == ItemEvent.SELECTED);
        });
    }

    private void initSelectAllOption() {
        boolean isSelectAll = PropertiesComponent.getInstance(project)
                .getBoolean(Constants.KEY_IS_SELECT_ALL);
        selectAllCheckBox.setSelected(isSelectAll);
        selectAllCheckBox.addItemListener(e -> {
            int state = e.getStateChange();
            selectAll(state == ItemEvent.SELECTED);
            PropertiesComponent.getInstance(project)
                    .setValue(Constants.KEY_IS_SELECT_ALL, state == ItemEvent.SELECTED);
        });
    }

    private void selectAll(boolean selectAll) {
        for (Component component : languagesPanel.getComponents()) {
            if (component instanceof JBCheckBox) {
                JBCheckBox checkBox = (JBCheckBox) component;
                checkBox.setSelected(selectAll);
            }
        }
    }

    @Override
    protected @Nullable String getDimensionServiceKey() {
        String key = SettingsState.getInstance().getSelectedTranslator().getKey();
        return "#com.airsaid.localization.ui.SelectLanguagesDialog#".concat(key);
    }

    @Override
    protected void doOKAction() {
        LanguageUtil.saveSelectedLanguage(project, selectedLanguages);
        if (onClickListener != null) {
            onClickListener.onClickListener(selectedLanguages);
        }
        super.doOKAction();
    }

    static class EnglishNameComparator implements Comparator<Lang> {
        @Override
        public int compare(Lang o1, Lang o2) {
            return o1.getEnglishName().compareTo(o2.getEnglishName());
        }
    }

    static class LanguageCodeComparator implements Comparator<Lang> {

        @Override
        public int compare(Lang o1, Lang o2) {
            return o1.getCode().compareTo(o2.getCode());
        }
    }
}
