package io.shardingsphere.sharding.unit.util;

import lombok.Getter;
import lombok.Setter;

/**
 * weifeng.jiang 2018-03-18 18:06
 */
@Getter
@Setter
public class UnitException extends RuntimeException{

    private String code;


    public UnitException(){
        super();
    }

    public UnitException(String msg){
        super(msg);
    }

}
