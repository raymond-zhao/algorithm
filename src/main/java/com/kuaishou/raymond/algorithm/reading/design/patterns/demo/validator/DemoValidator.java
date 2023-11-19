package com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoDTO;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.validator.checkfilter.CheckFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 15:44
 * Description:
 */
@Slf4j
@Component
public class DemoValidator implements IValidator<DemoDTO> {

    @Autowired
    private List<CheckFilter<DemoDTO>> checkFilters;

    @Override
    public List<CheckFilter<DemoDTO>> listCheckFilter() {
        return checkFilters;
    }
}
