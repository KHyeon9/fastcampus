package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {
    private List<QueryString> queryStrings = new ArrayList<>();

    // operand1=11  operator=*  operand2=55
    public QueryStrings(String queryStringLine) {
        String[] queryStringTokkens = queryStringLine.split("&"); // 키밸류값으로 되어있는 문자열로 나눈다.
        Arrays.stream(queryStringTokkens)
                .forEach(queryString -> {
                    String[] values = queryString.split("="); // 키밸류값으로 되어있는 문자열로을 키와 벨류값을 각각 나눈다.
                    if (values.length != 2) {
                        throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
                    }
                    queryStrings.add(new QueryString(values[0], values[1]));
                });
    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }
}
