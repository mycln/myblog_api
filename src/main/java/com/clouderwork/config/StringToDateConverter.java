package com.clouderwork.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    private static final String dateFormat      = "yyyy-MM-dd HH:mm:ss";
    private static final String notSecondDateFormat = "yyyy-MM-dd HH:mm";
    private static final String shortDateFormat = "yyyy-MM-dd";

    /** 
     * @see Converter#convert(Object)
     */
    @Override
    public Date convert(String source) {
        if (!StringUtils.hasText(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                SimpleDateFormat formatter;
                if (source.contains(":")) {
                    if(source.length() == 16){
                        formatter = new SimpleDateFormat(notSecondDateFormat);
                    }else{
                        formatter = new SimpleDateFormat(dateFormat);
                    }
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}
