package dcn.spbstu.mysticalcards;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Людмила on 15.11.2016.
 */

public class Forms {
    volatile static Map<String, String[]> forms_ = new TreeMap<>(); // <слово, формы>

    public Forms() {
    }

    public Map<String, String[]> getMap() {
        return forms_;
    }

    public void readForms(Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                context.openFileInput("forms")));
        String line;
        List<String> list = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        Map<String, String> map = new TreeMap<>();
        for (int j = 0; j < list.size(); j++) {
            String[] arrayMessage = list.get(j).split(" : ");
            map.put(arrayMessage[0], arrayMessage[1]);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String[] forms = entry.getValue().split("; ");
            forms_.put(entry.getKey(), forms);
        }
    }

    public void writeForms(Context context) throws IOException {
        BufferedWriter pw;
        pw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("forms", MODE_PRIVATE)));
        for (Map.Entry<String, String[]> entry : forms_.entrySet()) {
            String[] forms = entry.getValue();
            if (forms.length != 1) {
                pw.write(entry.getKey() + " : ");
                for (int i = 0; i < forms.length - 1; i++) {
                    pw.write(forms[i] + "; ");
                }
                pw.write(forms[forms.length - 1]);
                pw.write('\n');
            } else {
                pw.write(entry.getKey() + " : " + entry.getValue());
                pw.write('\n');
            }
        }
        pw.close();
    }
    public void setForms_(String word) {
        List<String> str_1 = new ArrayList();
        // окончание -s
        if (word.endsWith("x") || word.endsWith("s") || word.endsWith("z") || word.endsWith("ch") || word.endsWith("sh") || word.endsWith("o")) {
            str_1.add(word + "es");
        } else {
            if (word.endsWith("y")) {
                if (!word.endsWith("ey") && !word.endsWith("uy") && !word.endsWith("iy") && !word.endsWith("oy") && !word.endsWith("ay")) {
                    str_1.add(word.substring(0, word.length() - 2) + "ies");
                }
            } else {
                if ((word.endsWith("f") || word.endsWith("fe"))) {
                    str_1.add(word.substring(0, word.length() - 2) + "ves");
                } else {
                    if (word.endsWith("man")) {
                        str_1.add(word.substring(0, word.length() - 3) + "en");
                    } else {
                            str_1.add(word + "s");
                        }
                    }
                }
            }
        // окончание ed и ing
        if (word.endsWith("e")) {
            str_1.add(str_1 + "; " + word + "d");
            if (word.endsWith("ie")) {
                str_1.add(word.substring(0, word.length() - 3) + "ying");
            } else {
                if (word.endsWith("ee")) {
                    str_1.add(word.substring(0, word.length() - 3) + "ing");
                } else {
                    str_1.add(word.substring(0, word.length() - 2) + "ing");
                }
            }
        } else {
            if (word.endsWith("d") || word.endsWith("p") || word.endsWith("t") || word.endsWith("r") || word.endsWith("l") || word.endsWith("b") || word.endsWith("g")) {
                str_1.add(word + "ed");
                str_1.add(word + word.toCharArray()[word.length() - 1] + "ed");
                str_1.add(word + word.toCharArray()[word.length() - 1] + "ing");
                str_1.add(word + "ing");
            } else {
                if (word.endsWith("n")) {
                    str_1.add(word + word.toCharArray()[word.length() - 1] + "ing");
                }
                str_1.add(word + "ing");
                if (word.endsWith("y")) {
                    str_1.add(word.substring(0, word.length() - 2) + "ied");
                    str_1.add(word + "ed");
                } else {
                    str_1.add(word + "ed");
                }
            }
        }
        // 's
        str_1.add(word + "'s");
        str_1.add(word + "'");
        str_1.add(word + "n't");
        String[] strings = new String[str_1.size()];
        for(int i = 0; i < str_1.size(); i++){
            strings[i] = str_1.get(i);
        }
        forms_.put(word, strings);
    }

}
