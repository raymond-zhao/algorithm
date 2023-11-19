package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.OperationType;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.factory.DemoFactory;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoParam;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.IValidator;

import lombok.Data;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 13:59
 * Description:
 */
@Service
public class DemoService implements IService<DemoParam> {

    @Autowired
    private DemoFactory demoFactory;

    @Autowired
    private IValidator<DemoDTO> iValidator;

    @Override
    public void createMaterial(DemoParam param) {
        DemoDTO demoDTO = demoFactory.build(param, OperationType.CREATE);
        iValidator.validate(demoDTO);
    }

    @Override
    public void updateMaterial(DemoParam param) {
        DemoDTO demoDTO = demoFactory.build(param, OperationType.UPDATE);

    }

    @Data
    public static class DemoParam {

        private Long id;

    }

    @Data
    public static class DemoDTO {
        private Long id;
    }
}
