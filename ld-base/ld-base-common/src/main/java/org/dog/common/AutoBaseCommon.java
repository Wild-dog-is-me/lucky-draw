package org.dog.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Odin
 * @Date: 2023/4/29 00:28
 * @Description:自动装配
 */

@Configuration
@ComponentScan(basePackages = {"org.dog"})
public class AutoBaseCommon {
}
