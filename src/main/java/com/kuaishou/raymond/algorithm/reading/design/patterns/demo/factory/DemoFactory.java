package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.factory;

import org.springframework.stereotype.Component;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoDTO;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoParam;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.OperationType;
import com.kuaishou.raymond.algorithm.utils.BeanMapperUtils;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 14:12
 * Description:
 */
@Component
public class DemoFactory implements IFactory<DemoParam, DemoDTO> {

    @Override
    public DemoDTO build(DemoParam item, OperationType operationType) {
        return BeanMapperUtils.map(item, DemoDTO.class);
    }

    @Override
    public DemoParam render(DemoDTO item, OperationType operationType) {
        return null;
    }

}
