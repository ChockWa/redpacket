package com.hdh.redpacket.core.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    // 参数map
    private Map<String, String[]> params = new HashMap<>();

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParamsMap) {
        super(request);
        this.params = newParamsMap;
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 获取参数
     *
     * @param name
     * @return
     */
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 添加多个参数
     *
     * @param map
     */
    public void addParameters(Map<String, String[]> map) {
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 添加单个参数
     *
     * @param name
     * @param value
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    private void renewParameterMap(HttpServletRequest request) {

        String queryString = request.getQueryString();

        if (queryString != null && queryString.trim().length() > 0) {
            String[] params = queryString.split("&");

            for (int i = 0; i < params.length; i++) {
                int splitIndex = params[i].indexOf("=");
                if (splitIndex == -1) {
                    continue;
                }

                String key = params[i].substring(0, splitIndex);

                if (!this.params.containsKey(key)) {
                    if (splitIndex < params[i].length()) {
                        String value = params[i].substring(splitIndex + 1);
                        this.params.put(key, new String[]{value});
                    }
                }
            }
        }
    }
}
