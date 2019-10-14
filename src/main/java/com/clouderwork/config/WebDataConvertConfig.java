package com.clouderwork.config;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 序列换成json时,将所有的long变成string
 * 因为js中得数字类型不能包含所有的java long值
 * 不使用此转换器 long转json可能有精度问题
 */

@Configuration
public class WebDataConvertConfig extends WebMvcConfigurerAdapter {
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter longToStringConverter = new MappingJackson2HttpMessageConverter();
        KindoObjectMapper objectMapper = new KindoObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        longToStringConverter.setObjectMapper(objectMapper);

        converters.add(longToStringConverter);
    }
}