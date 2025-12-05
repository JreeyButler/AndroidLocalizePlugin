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

package com.airsaid.localization.translate.lang;

import java.util.*;

/**
 * @author airsaid
 */
// Some language codes and names cannot pass the compiler check
@SuppressWarnings(value = {"SpellCheckingInspection", "unused"})
public class Languages {
    public static final Lang AUTO = new Lang(0, "auto", "Auto", "Auto", "自动");
    public static final Lang ALBANIAN = new Lang(1, "sq", "Shqiptar", "Albanian", "阿尔巴尼亚语");
    public static final Lang ARABIC = new Lang(2, "ar", "العربية", "Arabic", "阿拉伯语");
    public static final Lang AMHARIC = new Lang(3, "am", "አማርኛ", "Amharic", "阿姆哈拉语");
    public static final Lang AZERBAIJANI = new Lang(4, "az", "азәрбајҹан", "Azerbaijani", "阿塞拜疆语");
    public static final Lang IRISH = new Lang(5, "ga", "Gaeilge", "Irish", "爱尔兰语");
    public static final Lang ESTONIAN = new Lang(6, "et", "Eesti", "Estonian", "爱沙尼亚语");
    public static final Lang BASQUE = new Lang(7, "eu", "Euskal", "Basque", "巴斯克语");
    public static final Lang BELARUSIAN = new Lang(8, "be", "беларускі", "Belarusian", "白俄罗斯语");
    public static final Lang BULGARIAN = new Lang(9, "bg", "Български", "Bulgarian", "保加利亚语");
    public static final Lang ICELANDIC = new Lang(10, "is", "Íslenska", "Icelandic", "冰岛语");
    public static final Lang POLISH = new Lang(11, "pl", "Polski", "Polish", "波兰语");
    public static final Lang BOSNIAN = new Lang(12, "bs", "Bosanski", "Bosnian", "波斯尼亚语");
    public static final Lang PERSIAN = new Lang(13, "fa", "Persian", "Persian", "波斯语");
    public static final Lang AFRIKAANS = new Lang(14, "af", "Afrikaans", "Afrikaans", "南非荷兰语");
    public static final Lang DANISH = new Lang(15, "da", "Dansk", "Danish", "丹麦语");
    public static final Lang GERMAN = new Lang(16, "de", "Deutsch", "German", "德语");
    public static final Lang RUSSIAN = new Lang(17, "ru", "Русский", "Russian", "俄语");
    public static final Lang FRENCH = new Lang(18, "fr", "Français", "French", "法语");
    public static final Lang FILIPINO = new Lang(19, "fil", "Filipino", "Filipino", "菲律宾语");
    public static final Lang FINNISH = new Lang(20, "fi", "Suomi", "Finnish", "芬兰语");
    public static final Lang FRISIAN = new Lang(21, "fy", "Frysk", "Frisian", "弗里斯兰语");
    public static final Lang KHMER = new Lang(22, "km", "ខ្មែរ", "Khmer", "高棉语");
    public static final Lang GEORGIAN = new Lang(23, "ka", "ქართული", "Georgian", "格鲁吉亚语");
    public static final Lang GUJARATI = new Lang(24, "gu", "ગુજરાતી", "Gujarati", "古吉拉特语");
    public static final Lang KAZAKH = new Lang(25, "kk", "Kazakh", "Kazakh", "哈萨克语");
    public static final Lang HAITIAN_CREOLE = new Lang(26, "ht", "Haitian Creole", "Haitian Creole", "海地克里奥尔语");
    public static final Lang KOREAN = new Lang(27, "ko", "한국어", "Korean", "韩语");
    public static final Lang HAUSA = new Lang(28, "ha", "Hausa", "Hausa", "豪萨语");
    public static final Lang DUTCH = new Lang(29, "nl", "Nederlands", "Dutch", "荷兰语");
    public static final Lang KYRGYZ = new Lang(30, "ky", "Кыргыз тили", "Kyrgyz", "吉尔吉斯语");
    public static final Lang GALICIAN = new Lang(31, "gl", "Galego", "Galician", "加利西亚语");
    public static final Lang CATALAN = new Lang(32, "ca", "Català", "Catalan", "加泰罗尼亚语");
    public static final Lang CZECH = new Lang(33, "cs", "Čeština", "Czech", "捷克语");
    public static final Lang KANNADA = new Lang(34, "kn", "ಕನ್ನಡ", "Kannada", "卡纳达语");
    public static final Lang CORSICAN = new Lang(35, "co", "Corsa", "Corsican", "科西嘉语");
    public static final Lang CROATIAN = new Lang(36, "hr", "Hrvatski", "Croatian", "克罗地亚语");
    public static final Lang KURDISH = new Lang(37, "ku", "Kurdî", "Kurdish", "库尔德语");
    public static final Lang LATIN = new Lang(38, "la", "Latina", "Latin", "拉丁语");
    public static final Lang LATVIAN = new Lang(39, "lv", "Latviešu", "Latvian", "拉脱维亚语");
    public static final Lang LAO = new Lang(40, "lo", "ລາວ", "Lao", "老挝语");
    public static final Lang LITHUANIAN = new Lang(41, "lt", "Lietuvių", "Lithuanian", "立陶宛语");
    public static final Lang LUXEMBOURGISH = new Lang(42, "lb", "Lëtzebuergesch", "Luxembourgish", "卢森堡语");
    public static final Lang ROMANIAN = new Lang(43, "ro", "Română", "Romanian", "罗马尼亚语");
    public static final Lang MALAGASY = new Lang(44, "mg", "Malagasy", "Malagasy", "马达加斯加语");
    public static final Lang MALTESE = new Lang(45, "mt", "Il-Malti", "Maltese", "马耳他语");
    public static final Lang MARATHI = new Lang(46, "mr", "मराठी", "Marathi", "马拉地语");
    public static final Lang MALAYALAM = new Lang(47, "ml", "മലയാളം", "Malayalam", "马拉雅拉姆语");
    public static final Lang MALAY = new Lang(48, "ms", "Melayu", "Malay", "马来语");
    public static final Lang MACEDONIAN = new Lang(49, "mk", "Македонски", "Macedonian", "马其顿语");
    public static final Lang MAORI = new Lang(50, "mi", "Māori", "Maori", "毛利语");
    public static final Lang MONGOLIAN = new Lang(51, "mn", "Монгол хэл", "Mongolian", "蒙古语");
    public static final Lang BANGLA = new Lang(52, "bn", "বাংল", "Bangla", "孟加拉语");
    public static final Lang BURMESE = new Lang(53, "my", "မြန်မာ", "Burmese", "缅甸语");
    public static final Lang HMONG = new Lang(54, "hmn", "Hmoob", "Hmong", "苗语");
    public static final Lang XHOSA = new Lang(55, "xh", "IsiXhosa", "Xhosa", "科萨语");
    public static final Lang ZULU = new Lang(56, "zu", "Zulu", "Zulu", "祖鲁语");
    public static final Lang NEPALI = new Lang(57, "ne", "नेपाली", "Nepali", "尼泊尔语");
    public static final Lang NORWEGIAN = new Lang(58, "no", "Norsk", "Norwegian", "挪威语");
    public static final Lang PUNJABI = new Lang(59, "pa", "ਪੰਜਾਬੀ", "Punjabi", "旁遮普语");
    public static final Lang PORTUGUESE = new Lang(60, "pt", "Português", "Portuguese", "葡萄牙语");
    public static final Lang PASHTO = new Lang(61, "ps", "Pashto", "Pashto", "普什图语");
    public static final Lang CHICHEWA = new Lang(62, "ny", "Chichewa", "Chichewa", "齐切瓦语");
    public static final Lang JAPANESE = new Lang(63, "ja", "日本語", "Japanese", "日语");
    public static final Lang SWEDISH = new Lang(64, "sv", "Svenska", "Swedish", "瑞典语");
    public static final Lang SAMOAN = new Lang(65, "sm", "Samoa", "Samoan", "萨摩亚语");
    public static final Lang SERBIAN = new Lang(66, "sr", "Српски", "Serbian", "塞尔维亚语");
    public static final Lang SOTHO = new Lang(67, "st", "Sesotho", "Sotho", "塞索托语");
    public static final Lang SINHALA = new Lang(68, "si", "සිංහල", "Sinhala", "僧伽罗语");
    public static final Lang ESPERANTO = new Lang(69, "eo", "Esperanta", "Esperanto", "世界语");
    public static final Lang SLOVAK = new Lang(70, "sk", "Slovenčina", "Slovak", "斯洛伐克语");
    public static final Lang SLOVENIAN = new Lang(71, "sl", "Slovenščina", "Slovenian", "斯洛文尼亚语");
    public static final Lang SWAHILI = new Lang(72, "sw", "Kiswahili", "Swahili", "斯瓦希里语");
    public static final Lang SCOTTISH_GAELIC = new Lang(73, "gd", "Gàidhlig na h-Alba", "Scottish Gaelic", "苏格兰盖尔语");
    public static final Lang CEBUANO = new Lang(74, "ceb", "Cebuano", "Cebuano", "宿务语");
    public static final Lang SOMALI = new Lang(75, "so", "Somali", "Somali", "索马里语");
    public static final Lang TAJIK = new Lang(76, "tg", "Тоҷикӣ", "Tajik", "塔吉克语");
    public static final Lang TELUGU = new Lang(77, "te", "తెలుగు", "Telugu", "泰卢固语");
    public static final Lang TAMIL = new Lang(78, "ta", "தமிழ்", "Tamil", "泰米尔语");
    public static final Lang THAI = new Lang(79, "th", "ไทย", "Thai", "泰语");
    public static final Lang TURKISH = new Lang(80, "tr", "Türkçe", "Turkish", "土耳其语");
    public static final Lang WELSH = new Lang(81, "cy", "Cymraeg", "Welsh", "威尔士语");
    public static final Lang URDU = new Lang(82, "ur", "اردو", "Urdu", "乌尔都语");
    public static final Lang UKRAINIAN = new Lang(83, "uk", "Українська", "Ukrainian", "乌克兰语");
    public static final Lang UZBEK = new Lang(84, "uz", "O'zbek", "Uzbek", "乌兹别克语");
    public static final Lang SPANISH = new Lang(85, "es", "Español", "Spanish", "西班牙语");
    public static final Lang HEBREW = new Lang(86, "iw", "עברית", "Hebrew", "希伯来语");
    public static final Lang GREEK = new Lang(87, "el", "Ελληνικά", "Greek", "希腊语");
    public static final Lang HAWAIIAN = new Lang(88, "haw", "Hawaiian", "Hawaiian", "夏威夷语");
    public static final Lang SINDHI = new Lang(89, "sd", "سنڌي", "Sindhi", "信德语");
    public static final Lang HUNGARIAN = new Lang(90, "hu", "Magyar", "Hungarian", "匈牙利语");
    public static final Lang SHONA = new Lang(91, "sn", "Shona", "Shona", "绍纳语");
    public static final Lang ARMENIAN = new Lang(92, "hy", "Հայերեն", "Armenian", "亚美尼亚语");
    public static final Lang IGBO = new Lang(93, "ig", "Igbo", "Igbo", "伊博语");
    public static final Lang ITALIAN = new Lang(94, "it", "Italiano", "Italian", "意大利语");
    public static final Lang YIDDISH = new Lang(95, "yi", "ייִדיש", "Yiddish", "意第绪语");
    public static final Lang HINDI = new Lang(96, "hi", "हिंदी", "Hindi", "印地语");
    public static final Lang SUNDANESE = new Lang(97, "su", "Sunda", "Sundanese", "巽他语");
    public static final Lang INDONESIAN = new Lang(98, "in-rID", "Indonesia", "Indonesian", "印尼语");
    public static final Lang JAVANESE = new Lang(99, "jv", "Wong Jawa", "Javanese", "爪哇语");
    public static final Lang ENGLISH = new Lang(100, "en", "English", "English", "英语");
    public static final Lang YORUBA = new Lang(101, "yo", "Yorùbá", "Yoruba", "约鲁巴语");
    public static final Lang VIETNAMESE = new Lang(102, "vi", "Tiếng Việt", "Vietnamese", "越南语");
    public static final Lang CHINESE_TRADITIONAL = new Lang(103, "zh-rTW", "正體中文", "Chinese Traditional", "繁体中文");
    public static final Lang CHINESE_SIMPLIFIED = new Lang(104, "zh-rCN", "简体中文", "Chinese Simplified", "简体中文");
    public static final Lang ASSAMESE = new Lang(105, "as", "Assamese", "Assamese", "阿萨姆语");
    public static final Lang DARI = new Lang(106, "prs", "Dari", "Dari", "达里语");
    public static final Lang FIJIAN = new Lang(107, "fj", "Fijian", "Fijian", "斐济语");
    public static final Lang HMONG_DAW = new Lang(108, "mww", "Hmong Daw", "Hmong Daw", "白苗语");
    public static final Lang INUKTITUT = new Lang(109, "iu", "ᐃᓄᒃᑎᑐᑦ", "Inuktitut", "因纽特语");
    public static final Lang KLINGON_LATIN = new Lang(110, "tlh-Latn", "Klingon (Latin)", "Klingon (Latin)", "克林贡语（拉丁文）");
    public static final Lang KLINGON_PIQAD = new Lang(111, "tlh-Piqd", "Klingon (pIqaD)", "Klingon (pIqaD)", "克林贡语（pIqaD）");
    public static final Lang ODIA = new Lang(112, "or", "Odia", "Odia", "奥里亚语");
    public static final Lang QUERETARO_OTOMI = new Lang(113, "otq", "Querétaro Otomi", "Querétaro Otomi", "克雷塔罗奥托米语");
    public static final Lang TAHITIAN = new Lang(114, "ty", "Tahitian", "Tahitian", "塔希提语");
    public static final Lang TIGRINYA = new Lang(115, "ti", "ትግርኛ", "Tigrinya", "提格利尼亚语");
    public static final Lang TONGAN = new Lang(116, "to", "lea fakatonga", "Tongan", "汤加语");
    public static final Lang YUCATEC_MAYA = new Lang(117, "yua", "Yucatec Maya", "Yucatec Maya", "尤卡坦玛雅语");
    public static final Lang SORANI_KURDISH = new Lang(118, "ckb", "کوردیی ناوەندی", "Sorani Kurdish", "中库尔德语");

    private static final Map<Integer, Lang> sLanguages;

    static {
        sLanguages = new HashMap<>();
        sLanguages.put(0, AUTO);
        sLanguages.put(1, ALBANIAN);
        sLanguages.put(2, ARABIC);
        sLanguages.put(3, AMHARIC);
        sLanguages.put(4, AZERBAIJANI);
        sLanguages.put(5, IRISH);
        sLanguages.put(6, ESTONIAN);
        sLanguages.put(7, BASQUE);
        sLanguages.put(8, BELARUSIAN);
        sLanguages.put(9, BULGARIAN);
        sLanguages.put(10, ICELANDIC);
        sLanguages.put(11, POLISH);
        sLanguages.put(12, BOSNIAN);
        sLanguages.put(13, PERSIAN);
        sLanguages.put(14, AFRIKAANS);
        sLanguages.put(15, DANISH);
        sLanguages.put(16, GERMAN);
        sLanguages.put(17, RUSSIAN);
        sLanguages.put(18, FRENCH);
        sLanguages.put(19, FILIPINO);
        sLanguages.put(20, FINNISH);
        sLanguages.put(21, FRISIAN);
        sLanguages.put(22, KHMER);
        sLanguages.put(23, GEORGIAN);
        sLanguages.put(24, GUJARATI);
        sLanguages.put(25, KAZAKH);
        sLanguages.put(26, HAITIAN_CREOLE);
        sLanguages.put(27, KOREAN);
        sLanguages.put(28, HAUSA);
        sLanguages.put(29, DUTCH);
        sLanguages.put(30, KYRGYZ);
        sLanguages.put(31, GALICIAN);
        sLanguages.put(32, CATALAN);
        sLanguages.put(33, CZECH);
        sLanguages.put(34, KANNADA);
        sLanguages.put(35, CORSICAN);
        sLanguages.put(36, CROATIAN);
        sLanguages.put(37, KURDISH);
        sLanguages.put(38, LATIN);
        sLanguages.put(39, LATVIAN);
        sLanguages.put(40, LAO);
        sLanguages.put(41, LITHUANIAN);
        sLanguages.put(42, LUXEMBOURGISH);
        sLanguages.put(43, ROMANIAN);
        sLanguages.put(44, MALAGASY);
        sLanguages.put(45, MALTESE);
        sLanguages.put(46, MARATHI);
        sLanguages.put(47, MALAYALAM);
        sLanguages.put(48, MALAY);
        sLanguages.put(49, MACEDONIAN);
        sLanguages.put(50, MAORI);
        sLanguages.put(51, MONGOLIAN);
        sLanguages.put(52, BANGLA);
        sLanguages.put(53, BURMESE);
        sLanguages.put(54, HMONG);
        sLanguages.put(55, XHOSA);
        sLanguages.put(56, ZULU);
        sLanguages.put(57, NEPALI);
        sLanguages.put(58, NORWEGIAN);
        sLanguages.put(59, PUNJABI);
        sLanguages.put(60, PORTUGUESE);
        sLanguages.put(61, PASHTO);
        sLanguages.put(62, CHICHEWA);
        sLanguages.put(63, JAPANESE);
        sLanguages.put(64, SWEDISH);
        sLanguages.put(65, SAMOAN);
        sLanguages.put(66, SERBIAN);
        sLanguages.put(67, SOTHO);
        sLanguages.put(68, SINHALA);
        sLanguages.put(69, ESPERANTO);
        sLanguages.put(70, SLOVAK);
        sLanguages.put(71, SLOVENIAN);
        sLanguages.put(72, SWAHILI);
        sLanguages.put(73, SCOTTISH_GAELIC);
        sLanguages.put(74, CEBUANO);
        sLanguages.put(75, SOMALI);
        sLanguages.put(76, TAJIK);
        sLanguages.put(77, TELUGU);
        sLanguages.put(78, TAMIL);
        sLanguages.put(79, THAI);
        sLanguages.put(80, TURKISH);
        sLanguages.put(81, WELSH);
        sLanguages.put(82, URDU);
        sLanguages.put(83, UKRAINIAN);
        sLanguages.put(84, UZBEK);
        sLanguages.put(85, SPANISH);
        sLanguages.put(86, HEBREW);
        sLanguages.put(87, GREEK);
        sLanguages.put(88, HAWAIIAN);
        sLanguages.put(89, SINDHI);
        sLanguages.put(90, HUNGARIAN);
        sLanguages.put(91, SHONA);
        sLanguages.put(92, ARMENIAN);
        sLanguages.put(93, IGBO);
        sLanguages.put(94, ITALIAN);
        sLanguages.put(95, YIDDISH);
        sLanguages.put(96, HINDI);
        sLanguages.put(97, SUNDANESE);
        sLanguages.put(98, INDONESIAN);
        sLanguages.put(99, JAVANESE);
        sLanguages.put(100, ENGLISH);
        sLanguages.put(101, YORUBA);
        sLanguages.put(102, VIETNAMESE);
        sLanguages.put(103, CHINESE_TRADITIONAL);
        sLanguages.put(104, CHINESE_SIMPLIFIED);
        sLanguages.put(105, ASSAMESE);
        sLanguages.put(106, DARI);
        sLanguages.put(107, FIJIAN);
        sLanguages.put(108, HMONG_DAW);
        sLanguages.put(109, INUKTITUT);
        sLanguages.put(110, KLINGON_LATIN);
        sLanguages.put(111, KLINGON_PIQAD);
        sLanguages.put(112, ODIA);
        sLanguages.put(113, QUERETARO_OTOMI);
        sLanguages.put(114, TAHITIAN);
        sLanguages.put(115, TIGRINYA);
        sLanguages.put(116, TONGAN);
        sLanguages.put(117, YUCATEC_MAYA);
        sLanguages.put(118, SORANI_KURDISH);
    }

    public static List<Lang> getLanguages() {
        return new ArrayList<>(sLanguages.values());
    }

}
