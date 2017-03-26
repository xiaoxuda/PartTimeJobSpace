package cn.orditech.enums;

/**
 * Created by kimi on 2017/3/25.
 */
public enum QuestionTypeEnum {
    SINGLE_SELECT(1,"单项选择"),
    JUDGE(2,"判断"),
    MULTI_SELECT(3,"多项选择");

    private Integer type;
    private String desc;

    QuestionTypeEnum(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public static QuestionTypeEnum getTypeEnum(Integer type){
        for(QuestionTypeEnum typeEnum:QuestionTypeEnum.values ()){
            if(typeEnum.type.equals (type)){
                return typeEnum;
            }
        }
        return null;
    }

    public Integer getType () {
        return type;
    }

    public String getDesc () {
        return desc;
    }
}
