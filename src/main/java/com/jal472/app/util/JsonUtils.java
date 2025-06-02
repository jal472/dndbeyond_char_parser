package com.jal472.app.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {
    public static String getSafeText(JsonNode root, String defaultValue, String... path) {
        JsonNode result = navigatePath(root, path);
        return result != null && result.isTextual() ? result.asText() : defaultValue;
    }

    public static int getSafeInt(JsonNode root, int defaultValue, String... path) {
        JsonNode result = navigatePath(root, path);
        return result != null && result.isNumber() ? result.asInt() : defaultValue;
    }

    public static boolean getSafeBoolean(JsonNode root, boolean defaultValue, String... path) {
        JsonNode result = navigatePath(root, path);
        return result != null && result.isBoolean() ? result.asBoolean() : defaultValue;
    }

    public static String getSafeTextFromArray(JsonNode rootArrayNode, int index, String defaultValue, String... path) {
        if (rootArrayNode != null && rootArrayNode.isArray() && rootArrayNode.size() > index) {
            return getSafeText(rootArrayNode.get(index), defaultValue, path);
        }
        return defaultValue;
    }

    public static int getSafeIntFromArray(JsonNode rootArrayNode, int index, int defaultValue, String... path) {
        if (rootArrayNode != null && rootArrayNode.isArray() && rootArrayNode.size() > index) {
            return getSafeInt(rootArrayNode.get(index), defaultValue, path);
        }
        return defaultValue;
    }

    private static JsonNode navigatePath(JsonNode root, String... path) {
        for (String p : path) {
            if (root == null || root.isMissingNode()) return null;
            root = root.path(p);
        }
        return root;
    }
}
