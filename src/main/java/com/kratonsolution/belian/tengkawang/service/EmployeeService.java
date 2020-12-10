package com.kratonsolution.belian.tengkawang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeService {

}
