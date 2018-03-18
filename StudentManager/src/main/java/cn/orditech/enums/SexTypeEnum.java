package cn.orditech.enums;

/**
 * Created by kimi on 2017/3/25.
 */
public enum SexTypeEnum {
    MAN(1,"男"),
    WOMAN(2,"女");

    private Integer type;
    private String desc;

    SexTypeEnum(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public static SexTypeEnum getTypeEnum(Integer type){
        for(SexTypeEnum typeEnum: SexTypeEnum.values ()){
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
