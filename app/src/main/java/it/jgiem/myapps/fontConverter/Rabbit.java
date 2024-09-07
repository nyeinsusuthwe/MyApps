package it.jgiem.myapps.fontConverter;

/*
 * Copyright (c) 2015 org.rabbitconverter.rabbit.Rabbit Converter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by @saturngod on 23/1/15.
 *
 * Ported as a JAVA/Gradle library by @yelinaung on 10/07/15.
 */
public final class Rabbit {

    /**
     * Convert Unicode texts to Zawgyi texts
     *
     * @param input Unicode input strings
     * @return Converted Zawgyi strings
     */
    public static String uni2zg(String input) {

        String rule =
                "[ { \"from\": \"\u1004\u103a\u1039\", \"to\": \"\u1064\" }, { \"from\": \"\u1039\u1010\u103d\", \"to\": \"\u1096\" }, { \"from\": \"\u1014(?=[\u1030\u103d\u103e\u102f\u1039])\", \"to\": \"\u108f\" }, { \"from\": \"\u102b\u103a\", \"to\": \"\u105a\" }, { \"from\": \"\u100b\u1039\u100c\", \"to\": \"\u1092\" }, { \"from\": \"\u102d\u1036\", \"to\": \"\u108e\" }, { \"from\": \"\u104e\u1004\u103a\u1038\", \"to\": \"\u104e\" }, { \"from\": \"[\u1025\u1009](?=[\u1039\u102f\u1030])\", \"to\": \"\u106a\" }, { \"from\": \"[\u1025\u1009](?=[\u103a])\", \"to\": \"\u1025\" }, { \"from\": \"\u100a(?=[\u1039\u102f\u1030\u103d])\", \"to\": \"\u106b\" }, { \"from\": \"(\u1039[\u1000-\u1021])\u102f\", \"to\": \"$1\u1033\" }, { \"from\": \"(\u1039[\u1000-\u1021])\u1030\", \"to\": \"$1\u1034\" }, { \"from\": \"\u1039\u1000\", \"to\": \"\u1060\" }, { \"from\": \"\u1039\u1001\", \"to\": \"\u1061\" }, { \"from\": \"\u1039\u1002\", \"to\": \"\u1062\" }, { \"from\": \"\u1039\u1003\", \"to\": \"\u1063\" }, { \"from\": \"\u1039\u1005\", \"to\": \"\u1065\" }, { \"from\": \"\u1039\u1006\", \"to\": \"\u1066\" }, { \"from\": \"\u1039\u1007\", \"to\": \"\u1068\" }, { \"from\": \"\u1039\u1008\", \"to\": \"\u1069\" }, { \"from\": \"\u100a(?=[\u1039\u102f\u1030])\", \"to\": \"\u106b\" }, { \"from\": \"\u1039\u100b\", \"to\": \"\u106c\" }, { \"from\": \"\u1039\u100c\", \"to\": \"\u106d\" }, { \"from\": \"\u100d\u1039\u100d\", \"to\": \"\u106e\" }, { \"from\": \"\u100e\u1039\u100d\", \"to\": \"\u106f\" }, { \"from\": \"\u1039\u100f\", \"to\": \"\u1070\" }, { \"from\": \"\u1039\u1010\", \"to\": \"\u1071\" }, { \"from\": \"\u1039\u1011\", \"to\": \"\u1073\" }, { \"from\": \"\u1039\u1012\", \"to\": \"\u1075\" }, { \"from\": \"\u1039\u1013\", \"to\": \"\u1076\" }, { \"from\": \"\u1039\u1013\", \"to\": \"\u1076\" }, { \"from\": \"\u1039\u1014\", \"to\": \"\u1077\" }, { \"from\": \"\u1039\u1015\", \"to\": \"\u1078\" }, { \"from\": \"\u1039\u1016\", \"to\": \"\u1079\" }, { \"from\": \"\u1039\u1017\", \"to\": \"\u107a\" }, { \"from\": \"\u1039\u1018\", \"to\": \"\u107b\" }, { \"from\": \"\u1039\u1019\", \"to\": \"\u107c\" }, { \"from\": \"\u1039\u101c\", \"to\": \"\u1085\" }, { \"from\": \"\u103f\", \"to\": \"\u1086\" }, { \"from\": \"(\u103c)\u103e\", \"to\": \"$1\u1087\" }, { \"from\": \"\u103d\u103e\", \"to\": \"\u108a\" }, { \"from\": \"(\u1064)([\u1031]?)([\u103c]?)([\u1000-\u1021])\u102d\", \"to\": \"$2$3$4\u108b\" }, { \"from\": \"(\u1064)([\u1031]?)([\u103c]?)([\u1000-\u1021])\u102e\", \"to\": \"$2$3$4\u108c\" }, { \"from\": \"(\u1064)([\u1031]?)([\u103c]?)([\u1000-\u1021])\u1036\", \"to\": \"$2$3$4\u108d\" }, { \"from\": \"(\u1064)([\u1031]?)([\u103c]?)([\u1000-\u1021])\", \"to\": \"$2$3$4$1\" }, { \"from\": \"\u101b(?=[\u102f\u1030\u103d\u108a])\", \"to\": \"\u1090\" }, { \"from\": \"\u100f\u1039\u100d\", \"to\": \"\u1091\" }, { \"from\": \"\u100b\u1039\u100b\", \"to\": \"\u1097\" }, { \"from\": \"([\u1000-\u1021\u108f\u1029\u1090])([\u1060-\u1069\u106c\u106d\u1070-\u107c\u1085\u108a])?([\u103b-\u103e]*)?\u1031\", \"to\": \"\u1031$1$2$3\" }, { \"from\": \"([\u1000-\u1021\u1029])([\u1060-\u1069\u106c\u106d\u1070-\u107c\u1085])?(\u103c)\", \"to\": \"$3$1$2\" }, { \"from\": \"\u103a\", \"to\": \"\u1039\" }, { \"from\": \"\u103b\", \"to\": \"\u103a\" }, { \"from\": \"\u103c\", \"to\": \"\u103b\" }, { \"from\": \"\u103d\", \"to\": \"\u103c\" }, { \"from\": \"\u103e\", \"to\": \"\u103d\" }, { \"from\": \"\u103d\u102f\", \"to\": \"\u1088\" }, { \"from\": \"([\u102f\u1030\u1014\u101b\u103c\u108a\u103d\u1088])([\u1032\u1036]{0,1})\u1037\", \"to\": \"$1$2\u1095\" }, { \"from\": \"\u102f\u1095\", \"to\": \"\u102f\u1094\" }, { \"from\": \"([\u1014\u101b])([\u1032\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\u1037\", \"to\": \"$1$2\u1095\" }, { \"from\": \"\u1095\u1039\", \"to\": \"\u1094\u1039\" }, { \"from\": \"([\u103a\u103b])([\u1000-\u1021])([\u1036\u102d\u102e\u108b\u108c\u108d\u108e]?)\u102f\", \"to\": \"$1$2$3\u1033\" }, { \"from\": \"([\u103a\u103b])([\u1000-\u1021])([\u1036\u102d\u102e\u108b\u108c\u108d\u108e]?)\u1030\", \"to\": \"$1$2$3\u1034\" }, { \"from\": \"\u103a\u102f\", \"to\": \"\u103a\u1033\" }, { \"from\": \"\u103a([\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\u102f\", \"to\": \"\u103a$1\u1033\" }, { \"from\": \"([\u103a\u103b])([\u1000-\u1021])\u1030\", \"to\": \"$1$2\u1034\" }, { \"from\": \"\u103a\u1030\", \"to\": \"\u103a\u1034\" }, { \"from\": \"\u103a([\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\u1030\", \"to\": \"\u103a$1\u1034\" }, { \"from\": \"\u103d\u1030\", \"to\": \"\u1089\" }, { \"from\": \"\u103b([\u1000\u1003\u1006\u100f\u1010\u1011\u1018\u101a\u101c\u101a\u101e\u101f])\", \"to\": \"\u107e$1\" }, { \"from\": \"\u107e([\u1000\u1003\u1006\u100f\u1010\u1011\u1018\u101a\u101c\u101a\u101e\u101f])([\u103c\u108a])([\u1032\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\", \"to\": \"\u1084$1$2$3\" }, { \"from\": \"\u107e([\u1000\u1003\u1006\u100f\u1010\u1011\u1018\u101a\u101c\u101a\u101e\u101f])([\u103c\u108a])\", \"to\": \"\u1082$1$2\" }, { \"from\": \"\u107e([\u1000\u1003\u1006\u100f\u1010\u1011\u1018\u101a\u101c\u101a\u101e\u101f])([\u1033\u1034]?)([\u1032\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\", \"to\": \"\u1080$1$2$3\" }, { \"from\": \"\u103b([\u1000-\u1021])([\u103c\u108a])([\u1032\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\", \"to\": \"\u1083$1$2$3\" }, { \"from\": \"\u103b([\u1000-\u1021])([\u103c\u108a])\", \"to\": \"\u1081$1$2\" }, { \"from\": \"\u103b([\u1000-\u1021])([\u1033\u1034]?)([\u1032\u1036\u102d\u102e\u108b\u108c\u108d\u108e])\", \"to\": \"\u107f$1$2$3\" }, { \"from\": \"\u103a\u103d\", \"to\": \"\u103d\u103a\" }, { \"from\": \"\u103a([\u103c\u108a])\", \"to\": \"$1\u107d\" }, { \"from\": \"([\u1033\u1034])\u1094\", \"to\": \"$1\u1095\" }, {  \"from\": \"\u108F\u1071\",  \"to\" : \"\u108F\u1072\" }, {  \"from\": \"([\u1000-\u1021])([\u107B\u1066])\u102C\",  \"to\": \"$1\u102C$2\" }, {  \"from\": \"\u102C([\u107B\u1066])\u1037\",  \"to\": \"\u102C$1\u1094\" }]";

        return replaceWithRule(rule, input);
    }

    /**
     * Convert Zawgyi texts to Unicode texts
     *
     * @param input Zawgyi input strings
     * @return Converted Unicode strings
     */
    public static String zg2uni(String input) {

        String rule =
                "[ { \"from\": \"(\u103d|\u1087)\", \"to\": \"\u103e\" }, { \"from\": \"\u103c\", \"to\": \"\u103d\" }, { \"from\": \"(\u103b|\u107e|\u107f|\u1080|\u1081|\u1082|\u1083|\u1084)\", \"to\": \"\u103c\" }, { \"from\": \"(\u103a|\u107d)\", \"to\": \"\u103b\" }, { \"from\": \"\u1039\", \"to\": \"\u103a\" }, { \"from\": \"(\u1066|\u1067)\", \"to\": \"\u1039\u1006\" }, { \"from\": \"\u106a\", \"to\": \"\u1009\" }, { \"from\": \"\u106b\", \"to\": \"\u100a\" }, { \"from\": \"\u106c\", \"to\": \"\u1039\u100b\" }, { \"from\": \"\u106d\", \"to\": \"\u1039\u100c\" }, { \"from\": \"\u106e\", \"to\": \"\u100d\u1039\u100d\" }, { \"from\": \"\u106f\", \"to\": \"\u100d\u1039\u100e\" }, { \"from\": \"\u1070\", \"to\": \"\u1039\u100f\" }, { \"from\": \"(\u1071|\u1072)\", \"to\": \"\u1039\u1010\" }, { \"from\": \"\u1060\", \"to\": \"\u1039\u1000\" }, { \"from\": \"\u1061\", \"to\": \"\u1039\u1001\" }, { \"from\": \"\u1062\", \"to\": \"\u1039\u1002\" }, { \"from\": \"\u1063\", \"to\": \"\u1039\u1003\" }, { \"from\": \"\u1065\", \"to\": \"\u1039\u1005\" }, { \"from\": \"\u1068\", \"to\": \"\u1039\u1007\" }, { \"from\": \"\u1069\", \"to\": \"\u1039\u1008\" }, { \"from\": \"(\u1073|\u1074)\", \"to\": \"\u1039\u1011\" }, { \"from\": \"\u1075\", \"to\": \"\u1039\u1012\" }, { \"from\": \"\u1076\", \"to\": \"\u1039\u1013\" }, { \"from\": \"\u1077\", \"to\": \"\u1039\u1014\" }, { \"from\": \"\u1078\", \"to\": \"\u1039\u1015\" }, { \"from\": \"\u1079\", \"to\": \"\u1039\u1016\" }, { \"from\": \"\u107a\", \"to\": \"\u1039\u1017\" }, { \"from\": \"\u107c\", \"to\": \"\u1039\u1019\" }, { \"from\": \"\u1085\", \"to\": \"\u1039\u101c\" }, { \"from\": \"\u1033\", \"to\": \"\u102f\" }, { \"from\": \"\u1034\", \"to\": \"\u1030\" }, { \"from\": \"\u103f\", \"to\": \"\u1030\" }, { \"from\": \"\u1086\", \"to\": \"\u103f\" }, { \"from\": \"\u1036\u1088\", \"to\": \"\u1088\u1036\" }, { \"from\": \"\u1088\", \"to\": \"\u103e\u102f\" }, { \"from\": \"\u1089\", \"to\": \"\u103e\u1030\" }, { \"from\": \"\u108a\", \"to\": \"\u103d\u103e\" }, { \"from\": \"([\u1000-\u1021])\u1064\", \"to\": \"\u1004\u103a\u1039$1\" }, { \"from\": \"([\u1000-\u1021])\u108b\", \"to\": \"\u1004\u103a\u1039$1\u102d\" }, { \"from\": \"([\u1000-\u1021])\u108c\", \"to\": \"\u1004\u103a\u1039$1\u102e\" }, { \"from\": \"([\u1000-\u1021])\u108d\", \"to\": \"\u1004\u103a\u1039$1\u1036\" }, { \"from\": \"\u108e\", \"to\": \"\u102d\u1036\" }, { \"from\": \"\u108f\", \"to\": \"\u1014\" }, { \"from\": \"\u1090\", \"to\": \"\u101b\" }, { \"from\": \"\u1091\", \"to\": \"\u100f\u1039\u100d\" }, { \"from\": \"\u1019\u102c(\u107b|\u1093)\", \"to\": \"\u1019\u1039\u1018\u102c\" }, { \"from\": \"(\u107b|\u1093)\", \"to\": \"\u1039\u1018\" }, { \"from\": \"(\u1094|\u1095)\", \"to\": \"\u1037\" }, { \"from\": \"\u1096\", \"to\": \"\u1039\u1010\u103d\" }, { \"from\": \"\u1097\", \"to\": \"\u100b\u1039\u100b\" }, { \"from\": \"\u103c([\u1000-\u1021])([\u1000-\u1021])?\", \"to\": \"$1\u103c$2\" }, { \"from\": \"([\u1000-\u1021])\u103c\u103a\", \"to\": \"\u103c$1\u103a\" }, { \"from\": \"\u1031([\u1000-\u1021])(\u103e)?(\u103b)?\", \"to\": \"$1$2$3\u1031\" }, { \"from\": \"([\u1000-\u1021])\u1031([\u103b\u103c\u103d\u103e]+)\", \"to\": \"$1$2\u1031\" }, { \"from\": \"\u1032\u103d\", \"to\": \"\u103d\u1032\" }, { \"from\": \"\u103d\u103b\", \"to\": \"\u103b\u103d\" }, { \"from\": \"\u103a\u1037\", \"to\": \"\u1037\u103a\" }, { \"from\": \"\u102f(\u102d|\u102e|\u1036|\u1037)\u102f\", \"to\": \"\u102f$1\" }, { \"from\": \"\u102f\u102f\", \"to\": \"\u102f\" }, { \"from\": \"(\u102f|\u1030)(\u102d|\u102e)\", \"to\": \"$2$1\" }, { \"from\": \"(\u103e)(\u103b|\u1037)\", \"to\": \"$2$1\" }, { \"from\": \"\u1025(\u103a|\u102c)\", \"to\": \"\u1009$1\" }, { \"from\": \"\u1025\u102e\", \"to\": \"\u1026\" }, { \"from\": \"\u1005\u103b\", \"to\": \"\u1008\" }, { \"from\": \"\u1036(\u102f|\u1030)\", \"to\": \"$1\u1036\" }, { \"from\": \"\u1031\u1037\u103e\", \"to\": \"\u103e\u1031\u1037\" }, { \"from\": \"\u1031\u103e\u102c\", \"to\": \"\u103e\u1031\u102c\" }, { \"from\": \"\u105a\", \"to\": \"\u102b\u103a\" }, { \"from\": \"\u1031\u103b\u103e\", \"to\": \"\u103b\u103e\u1031\" }, { \"from\": \"(\u102d|\u102e)(\u103d|\u103e)\", \"to\": \"$2$1\" }, { \"from\": \"\u102c\u1039([\u1000-\u1021])\", \"to\": \"\u1039$1\u102c\" }, { \"from\": \"\u103c\u1004\u103a\u1039([\u1000-\u1021])\", \"to\": \"\u1004\u103a\u1039$1\u103c\" }, { \"from\": \"\u1039\u103c\u103a\u1039([\u1000-\u1021])\", \"to\": \"\u103a\u1039$1\u103c\" }, { \"from\": \"\u103c\u1039([\u1000-\u1021])\", \"to\": \"\u1039$1\u103c\" }, { \"from\": \"\u1036\u1039([\u1000-\u1021])\", \"to\": \"\u1039$1\u1036\" }, { \"from\": \"\u1092\", \"to\": \"\u100b\u1039\u100c\" }, { \"from\": \"\u104e\", \"to\": \"\u104e\u1004\u103a\u1038\" }, { \"from\": \"\u1040(\u102b|\u102c|\u1036)\", \"to\": \"\u101d$1\" }, { \"from\": \"\u1025\u1039\", \"to\": \"\u1009\u1039\" }, { \"from\": \"([\u1000-\u1021])\u103c\u1031\u103d\", \"to\": \"$1\u103c\u103d\u1031\" }, { \"from\": \"([\u1000-\u1021])\u103d\u1031\u103b\", \"to\": \"$1\u103b\u103d\u1031\" }, { \"from\": \"([\u1000-\u1021])\u1031(\u1039[\u1000-\u1021])\", \"to\": \"$1$2\u1031\" }, {  \"from\" : \" \u1037\",  \"to\": \"\u1037\" }]";

        return replaceWithRule(rule, input);
    }

    private static String replaceWithRule(String rule, String output) {

        try {
            JSONArray rule_array = new JSONArray(rule);
            int max_loop = rule_array.length();

            //because of JDK 7 bugs in Android
            output = output.replace("null", "\uFFFF\uFFFF");

            for (int i = 0; i < max_loop; i++) {

                JSONObject obj = rule_array.getJSONObject(i);
                String from = obj.getString("from");
                String to = obj.getString("to");

                output = output.replaceAll(from, to);
                output = output.replace("null", "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        output = output.replace("\uFFFF\uFFFF", "null");
        return output;
    }
}