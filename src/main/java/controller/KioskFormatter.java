package controller;

import java.util.List;

record KioskFormatter() {
    private enum Color {
        BLACK("\u001B[30m"),
        BLUE("\u001B[34m"),
        CYAN("\u001B[36m"),
        GREEN("\u001B[32m"),
        PURPLE("\u001B[35m"),
        RED("\u001B[31m"),
        RESET("\u001B[0m"),
        WHITE("\u001B[37m"),
        YELLOW("\u001B[33m");

        private final String code;

        Color(String code) {
            this.code = code;
        }
    }

    public static String formatList(List<?> list, String zeroPrompt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String coloredNumber = applyColor(String.format("%2d. ", i + 1), Color.YELLOW);
            String coloredStr = applyColor(list.get(i).toString(), Color.BLUE);
            sb.append(String.format("%s%s\n", coloredNumber, coloredStr));
        }
        sb.append(applyColor(" 0. ", Color.YELLOW)).append(applyColor(zeroPrompt, Color.PURPLE));
        return sb.toString();
    }

    public static String formatMessage(String prompt) {
        return applyColor(prompt, Color.CYAN);
    }

    public static String formatError(String prompt) {
        return applyColor(prompt, Color.RED);
    }

    private static String applyColor(String text, Color color) {
        return String.format("%s%s%s ", color.code, text, Color.RESET.code);
    }
}