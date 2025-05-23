package com.example.try_3_7_sonnet.common.util;

import com.example.try_3_7_sonnet.common.dto.PageResponse;
import com.example.try_3_7_sonnet.common.dto.PaginationResponse;

import java.util.List;

public class PaginationUtil {

    private PaginationUtil() {
        // ユーティリティクラスのため、インスタンス化を防止
    }

    public static <T, R> PageResponse<R> createPageResponse(List<R> content, int page, int size, long totalElements,
            int totalPages) {
        PaginationResponse pagination = PaginationResponse.builder()
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();

        return PageResponse.<R>builder()
                .content(content)
                .pagination(pagination)
                .build();
    }
}
