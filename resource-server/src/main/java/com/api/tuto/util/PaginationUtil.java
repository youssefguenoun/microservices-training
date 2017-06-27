package com.api.tuto.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringJoiner;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">Github API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public class PaginationUtil {

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl)
        throws URISyntaxException {

        int totalPages = page.getTotalPages();
        int pageSize = page.getSize();
        int currentPage = page.getNumber();
        int nextPageNumber = currentPage + 1;

        StringJoiner joiner = new StringJoiner(",");
        if (nextPageNumber < totalPages) {
            joiner.add(createLink(baseUrl, nextPageNumber, pageSize, "next"));
        }
        // prev link
        if (currentPage > 0) {
            joiner.add(createLink(baseUrl, currentPage - 1, pageSize, "prev"));
        }
        // last and first link
        int lastPage = 0;
        if (totalPages > 0) {
            lastPage = totalPages - 1;
        }

        joiner.add(createLink(baseUrl, lastPage, pageSize, "last"));
        joiner.add(createLink(baseUrl, 0, pageSize, "first"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        headers.add(HttpHeaders.LINK, joiner.toString());
        return headers;
    }

    private static String createLink(String baseUrl, int nextPageNumber, int pageSize, String relValue) throws URISyntaxException {
        URI uri = new URI(String.format("%s?page=%s&size=%s", baseUrl, nextPageNumber, pageSize));
        String link = String.format("<%s>; rel\"%s\"", uri.toString(), relValue);
        return link;
    }
}
