package com.onnv.household.entity.identifier;

import com.onnv.household.utils.RandomUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class IdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return RandomUtils.randomTimeId();
    }
}