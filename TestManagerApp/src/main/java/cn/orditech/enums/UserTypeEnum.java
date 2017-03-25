package cn.orditech.enums;

/**
 * Created by kimi on 2017/3/25.
 */
public enum UserTypeEnum {
    GENERAL(1,"普通用户"),
    ADMINISTRATOR(2,"管理员");

    private Integer type;
    private String desc;

    UserTypeEnum(Integer type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public static UserTypeEnum getTypeEnum(Integer type){
        for(UserTypeEnum typeEnum:UserTypeEnum.values ()){
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
