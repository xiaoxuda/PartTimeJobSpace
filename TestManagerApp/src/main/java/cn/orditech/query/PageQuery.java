package cn.orditech.query;

/**
 * Created by kimi on 2017/3/26.
 */
public class PageQuery {
    public static final int DEFAULT_PAGENUM = 1;
    public static final int DEFAULT_PAGESIZE = 20;

    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum () {
        return pageNum;
    }

    public void setPageNum (Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize () {
        return pageSize;
    }

    public void setPageSize (Integer pageSize) {
        this.pageSize = pageSize;
    }
}
