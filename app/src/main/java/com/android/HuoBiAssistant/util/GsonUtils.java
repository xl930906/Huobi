package com.android.HuoBiAssistant.util;

import com.android.HuoBiAssistant.model.ParamName;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class GsonUtils {

    public static Gson newInstance() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingStrategy(new AnnotateNaming());

        return builder.create();
    }

    private static class AnnotateNaming implements FieldNamingStrategy {

        @Override
        public String translateName(Field field) {
            ParamName a = field.getAnnotation(ParamName.class);
            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
        }
    }
}