package com.reringuy.stockmanager.utils;

import java.util.Collections;
import java.util.List;

public class Pagination<T> {
    private final List<T> items;
    private final int pageSize;
    private final int currentPage;
    private final int totalPages;
    private final int totalItems;

    public Pagination(List<T> items, int pageSize, int currentPage, int totalPages, int totalItems) {
        this.items = items == null ? Collections.emptyList() : Collections.unmodifiableList(items);
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public boolean hasNext() {
        return currentPage < totalPages;
    }

    public boolean hasPrevious() {
        return currentPage > 1 && totalPages > 0;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "items=" + items.size() +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                '}';
    }
}
