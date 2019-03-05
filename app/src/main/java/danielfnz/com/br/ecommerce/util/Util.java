package danielfnz.com.br.ecommerce.util;

import android.content.Context;

public class Util {

    public static String CIFRA_DINHEIRO = "R$ ";


    public static int dpToPx(Context context, int dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    public static double convertToNumber(String value) {
        return Double.parseDouble(new StringBuilder(value).substring(3).replace("," , ".").toString());
    }

    public static String formataVirgula(double value) {
        String text = String.valueOf(value).replace(".", ",");
        int index = text.indexOf(",");
        if(index + 2 == text.length()) {
            text = text + "0";
        }
        return text;
    }

}
