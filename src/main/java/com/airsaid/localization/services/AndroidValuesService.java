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

package com.airsaid.localization.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.airsaid.localization.translate.lang.Lang;
import com.airsaid.localization.utils.MyXmlPsiUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.Consumer;

/**
 * Operation service for the android value files. eg: strings.xml (or any string resource from values directory).
 *
 * @author airsaid
 */
@Service
public final class AndroidValuesService {

  private static final Logger LOG = Logger.getInstance(AndroidValuesService.class);

  private static final Pattern STRINGS_FILE_NAME_PATTERN = Pattern.compile(".+\\.xml");
  private static final String RESOURCES_START_TAG = "<resources>";
  private static final String RESOURCES_END_TAG = "</resources>";

  /**
   * Returns the {@link AndroidValuesService} object instance.
   *
   * @return the {@link AndroidValuesService} object instance.
   */
  public static AndroidValuesService getInstance() {
    return ServiceManager.getService(AndroidValuesService.class);
  }

  /**
   * Asynchronous loading the value file as the {@link PsiElement} collection.
   *
   * @param valueFile the value file.
   * @param consumer  load result. called in the event dispatch thread.
   */
  public void loadValuesByAsync(@NotNull PsiFile valueFile, @NotNull Consumer<List<PsiElement>> consumer) {
    ApplicationManager.getApplication().executeOnPooledThread(() -> {
          List<PsiElement> values = loadValues(valueFile);
          ApplicationManager.getApplication().invokeLater(() ->
              consumer.consume(values));
        }
    );
  }

  public void loadValuesByAsync(String xmlContent, @NotNull Project project,
      @NotNull Consumer<List<PsiElement>> consumer) {
    ApplicationManager.getApplication().executeOnPooledThread(() -> {
      List<PsiElement> values;
      if (xmlContent == null || xmlContent.isEmpty()) {
        values = new ArrayList<>();
      } else {
        StringBuilder sb = new StringBuilder();
        sb.append(RESOURCES_START_TAG).append(System.lineSeparator())
            .append("    ").append(xmlContent.trim()).append(System.lineSeparator())
            .append(RESOURCES_END_TAG);
        XmlFile xmlFile = MyXmlPsiUtil.createXmlFileFromString(project, sb.toString());
        if (xmlFile == null) {
          values = new ArrayList<>();
        } else {
          values = loadValues(xmlFile);
        }
      }
      ApplicationManager.getApplication().invokeLater(() -> consumer.consume(values));
    });
  }

  /**
   * Loading the value file as the {@link PsiElement} collection.
   *
   * @param valueFile the value file.
   * @return {@link PsiElement} collection.
   */
  public List<PsiElement> loadValues(@NotNull PsiFile valueFile) {
    return ApplicationManager.getApplication().runReadAction((Computable<List<PsiElement>>) () -> {
      LOG.info("loadValues valueFile: " + valueFile.getName());
      List<PsiElement> values = parseValuesXml(valueFile);
      LOG.info("loadValues parsed " + valueFile.getName() + " result: " + values);
      return values;
    });
  }

  public List<PsiElement> loadValues(@NotNull XmlFile valueFile){
    return ApplicationManager.getApplication().runReadAction((Computable<List<PsiElement>>) () -> {
      LOG.info("loadValues valueFile: " + valueFile.getName());
      List<PsiElement> values = parseValuesXml(valueFile);
      LOG.info("loadValues parsed " + valueFile.getName() + " result: " + values);
      return values;
    });
  }

  private List<PsiElement> parseValuesXml(@NotNull PsiFile valueFile) {
    return parseValuesXml((XmlFile) valueFile);
  }

  private List<PsiElement> parseValuesXml(@NotNull XmlFile xmlFile) {
    final List<PsiElement> values = new ArrayList<>();

    final XmlDocument document = xmlFile.getDocument();
    if (document == null) return values;

    final XmlTag rootTag = document.getRootTag();
    if (rootTag == null) return values;

    PsiElement[] subTags = rootTag.getChildren();
    values.addAll(Arrays.asList(subTags));

    return values;
  }

  /**
   * Write {@link PsiElement} collection data to the specified file.
   *
   * @param values    specified {@link PsiElement} collection data.
   * @param valueFile specified file.
   */
  public void writeValueFile(@NotNull List<PsiElement> values, @NotNull File valueFile, final boolean appendMode) {
    boolean isCreateSuccess = FileUtil.createIfDoesntExist(valueFile);
    if (!isCreateSuccess) {
      LOG.error("Failed to write to " + valueFile.getPath() + " file: create failed!");
      return;
    }
    ApplicationManager.getApplication().invokeLater(() -> ApplicationManager.getApplication().runWriteAction(() -> {
      func: {
        if (appendMode) {
          long index = -1;
          try {
            index = findResourceIndex(valueFile, RESOURCES_END_TAG);
            if (index < 0) {
              break func;
            }
            appendWrite(valueFile, index, values);
          } catch(IOException e) {
            e.printStackTrace();
          }
          return;
        }
      }
      try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(valueFile, false), StandardCharsets.UTF_8))) {
        for (PsiElement value : values) {
          bw.write(value.getText());
        }
        bw.flush();
      } catch (IOException e) {
        e.printStackTrace();
        LOG.error("Failed to write to " + valueFile.getPath() + " file.", e);
      }
    }));
  }

  private void appendWrite(@NotNull File valueFile, long index, @NotNull List<PsiElement> values) throws IOException {
    if (index < 0) {
      return;
    }
    final long fileLength = valueFile.length();
    if (index > fileLength) {
      return;
    }
    if (!valueFile.exists()) {
      return;
    }
    try (RandomAccessFile raf = new RandomAccessFile(valueFile, "rw")) {
      raf.seek(index);
      StringBuilder sb = new StringBuilder();
      boolean verified = false;
      for (int i = 0; i < values.size(); i++) {
        final String text = values.get(i).getText();
        if (i < 3) {
          sb.append(text);
          continue;
        } else {
          if (!verified && !sb.toString().trim().equals(RESOURCES_START_TAG)) {
            raf.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            verified = true;
          } else {
            // no write <resource> tag
            verified = true;
          }
        }
        raf.write(text.getBytes(StandardCharsets.UTF_8));
      }
    }
  }

  private long findResourceIndex(File file, String target) throws IOException {
    byte[] targetBytes = target.getBytes(StandardCharsets.UTF_8);
    int targetLen = targetBytes.length;

    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
        long fileLength = raf.length();
        LOG.info("fileLength = " + fileLength);
        int bufferSize = 4096;
        // 多留 targetLen 防止跨段
        byte[] buffer = new byte[bufferSize + targetLen];
        long pointer = fileLength;

        while (pointer > 0) {
            int readSize = (int) Math.min(bufferSize, pointer);
            pointer -= readSize;
            raf.seek(pointer);
            raf.readFully(buffer, 0, readSize);

            // 向前拼接前一段的数据以防止匹配跨段
            if (readSize < bufferSize) {
                System.arraycopy(buffer, 0, buffer, targetLen, readSize);
            } else {
                raf.seek(pointer - targetLen);
                raf.readFully(buffer, 0, targetLen + readSize);
            }

            // 从后往前匹配
            for (int i = buffer.length - targetLen; i >= 0; i--) {
                boolean match = true;
                for (int j = 0; j < targetLen; j++) {
                    if (buffer[i + j] != targetBytes[j]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                  long index = pointer + i;
                  index -= targetLen;
                  return index;
                }
            }
        }
    }
    return -1;
  }

  /**
   * Verify that the specified file is a string resource file in the values directory.
   *
   * @param file the verify file.
   * @return true: the file is a string resource file in the values directory.
   */
  public boolean isValueFile(@Nullable PsiFile file) {
    if (file == null) return false;

    PsiDirectory parent = file.getParent();
    if (parent == null) return false;

    String parentName = parent.getName();
    if ("values".equals(parentName) || "values-zh-rCN".equals(parentName)) {
      String fileName = file.getName();
      return STRINGS_FILE_NAME_PATTERN.matcher(fileName).matches();
    }
    return false;
  }

  /**
   * Get the value file of the specified language in the specified project resource directory.
   *
   * @param project     current project.
   * @param resourceDir specified resource directory.
   * @param lang        specified language.
   * @param fileName    the name of value file.
   * @return null if not exist, otherwise return the value file.
   */
  @Nullable
  public PsiFile getValuePsiFile(@NotNull Project project,
                                 @NotNull VirtualFile resourceDir,
                                 @NotNull Lang lang,
                                 @NotNull String fileName) {
    return ApplicationManager.getApplication().runReadAction((Computable<PsiFile>) () -> {
      VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(getValueFile(resourceDir, lang, fileName));
      if (virtualFile == null) {
        return null;
      }
      return PsiManager.getInstance(project).findFile(virtualFile);
    });
  }

  /**
   * Get the value file in the {@code values} directory of the specified language in the resource directory.
   *
   * @param resourceDir specified resource directory.
   * @param lang        specified language.
   * @param fileName    the name of value file.
   * @return the value file.
   */
  @NotNull
  public File getValueFile(@NotNull VirtualFile resourceDir, @NotNull Lang lang, String fileName) {
    return new File(resourceDir.getPath().concat(File.separator).concat(getValuesDirectoryName(lang)), fileName);
  }

  private String getValuesDirectoryName(@NotNull Lang lang) {
    return "values-".concat(lang.getCode());
  }

  /**
   * Returns whether the specified xml tag (string entry) needs to be translated.
   *
   * @param xmlTag the specified xml tag of string entry.
   * @return true: need translation. false: no translation is needed.
   */
  public boolean isTranslatable(@NotNull XmlTag xmlTag) {
    return ApplicationManager.getApplication().runReadAction((Computable<Boolean>) () -> {
      String translatableStr = xmlTag.getAttributeValue("translatable");
      return Boolean.parseBoolean(translatableStr == null ? "true" : translatableStr);
    });
  }
}
