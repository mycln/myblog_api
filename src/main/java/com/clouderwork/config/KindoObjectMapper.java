package com.clouderwork.config;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author : xuqiang
 * @Description : null 转空字符串
 * @Date: 2017/8/4
 */
public class KindoObjectMapper extends ObjectMapper {

    public KindoObjectMapper() {
        super();
//        // 允许单引号
//        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        // 字段和值都加引号
//        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        // 数字也加引号
//        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
//        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
        // 空值处理为空串
//        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
//        {
//
//            @Override
//            public void serialize(
//                    Object value,
//                    JsonGenerator jg,
//                    SerializerProvider sp) throws IOException, JsonProcessingException
//            {
//                jg.writeString("");
//            }
//        });

    }
}
