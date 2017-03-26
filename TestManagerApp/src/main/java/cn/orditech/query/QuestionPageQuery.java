package cn.orditech.query;

/**
 * Created by kimi on 2017/3/26.
 */
public class QuestionPageQuery extends PageQuery{
    private Integer type;
    private String keyword;

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    public String getKeyword () {
        return keyword;
    }

    public void setKeyword (String keyword) {
        this.keyword = keyword;
    }
}
