/*
 * Copyright 2025 Dipper.
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
package com.airsaid.localization.utils;

import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.xml.XmlFile;

public class MyXmlPsiUtil {
    public static XmlFile createXmlFileFromString(Project project, String xmlContent) {
        return (XmlFile) PsiFileFactory.getInstance(project)
                .createFileFromText("temp.xml", XMLLanguage.INSTANCE, xmlContent);
    }
}