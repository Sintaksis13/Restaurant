package com.epam.action;

import com.epam.entity.Table;
import com.epam.service.impservice.TableService;

public class TableAction {
    public long reserveTable(Table table) {
        TableService tableService = new TableService();
        return tableService.reserve(table);
    }
}
