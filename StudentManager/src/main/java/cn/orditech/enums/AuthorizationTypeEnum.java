package cn.orditech.enums;

/**
 * Created by kimi on 2017/4/3.
 */
public enum AuthorizationTypeEnum {
    GENERAL_STAFF("staff",1,"普通员工"),
    MANAGER_STAFF("manager",2,"主管"),
    ADMINISTRATOR("administrator",3,"管理员");

    private String type;
    private Integer level;
    private String desc;


    AuthorizationTypeEnum(String type,Integer level,String desc){
        this.type = type;
        this.desc = desc;
        this.level = level;
    }

    public static AuthorizationTypeEnum getByLevel(Integer level){
        for(AuthorizationTypeEnum typeEnum:values ()){
            if(typeEnum.getLevel ().equals (level)){
                return typeEnum;
            }
        }
        return null;
    }

    public static AuthorizationTypeEnum getByType(String type){
        for(AuthorizationTypeEnum typeEnum:values ()){
            if(typeEnum.getType ().equals (type)){
                return typeEnum;
            }
        }
        return null;
    }

    public String getType () {
        return type;
    }

    public String getDesc () {
        return desc;
    }

    public Integer getLevel () {
        return level;
    }
}
