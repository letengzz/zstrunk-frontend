package com.hjc.utils;


import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Distinct;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL 工具
 */
public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField 排序字段
     * @return 是否合法
     */

    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }

    /**
     * 解析查询语句
     *
     * @param sql 查询sql
     * @return 查询结果
     */
    public static Map<String, Object> parseQuerySql(String sql) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            PlainSelect plainSelect = select.getPlainSelect();
            return new HashMap<>() {{
                put("distinct", plainSelect.getDistinct());
                put("select", plainSelect.getSelectItems());
                put("from", plainSelect.getFromItem());
                put("where", plainSelect.getWhere());
                put("joins", plainSelect.getJoins());
                put("limit", plainSelect.getLimit());
                put("offset", plainSelect.getOffset());
                put("orderBy", plainSelect.getOrderByElements());
            }};
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析插入语句
     *
     * @param sql 查询sql
     * @return 插入结果
     */
    public static Map<String, Object> parseInsertSql(String sql) {
        try {
            Insert insert = (Insert) CCJSqlParserUtil.parse(sql);
            return new HashMap<>() {{
                put("table", insert.getTable());
                put("columns", insert.getColumns());
                put("values", insert.getValues());
            }};
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析更新语句
     *
     * @param sql 查询sql
     * @return 更新结果
     */
    public static Map<String, Object> parseUpdateSql(String sql) {
        try {
            Update update = (Update) CCJSqlParserUtil.parse(sql);
            return new HashMap<String, Object>() {{
                put("table", update.getTable());
                List<UpdateSet> updateSets = update.getUpdateSets();
                for (UpdateSet updateSet : updateSets) {
                    put(updateSet.getColumns().toString(), updateSet.getValues());
                }
                put("where", update.getWhere());
            }};
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析删除语句
     *
     * @param sql 查询sql
     * @return 删除结果
     */
    public static Map<String, Object> parseDeleteSql(String sql) {
        try {
            Delete delete = (Delete) CCJSqlParserUtil.parse(sql);
            return new HashMap<String, Object>() {{
                put("table", delete.getTable());
                put("where", delete.getWhere());
            }};
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
